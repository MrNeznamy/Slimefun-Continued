package eu.mrneznamy.slimefuncontinued.scheduling;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * A queue for executing tasks sequentially with delays
 * Replaces io.github.bakedlibs.dough.scheduling.TaskQueue
 */
public class TaskQueue {

    private final Plugin plugin;
    private final Queue<QueuedTask> tasks;
    private final long ticksPerTask;
    private BukkitTask currentTask;
    private boolean running;

    /**
     * Creates a new TaskQueue
     * @param plugin The plugin instance
     */
    public TaskQueue(@Nonnull Plugin plugin) {
        this(plugin, 1);
    }

    /**
     * Creates a new TaskQueue with custom delay
     * @param plugin The plugin instance
     * @param ticksPerTask Ticks to wait between tasks
     */
    public TaskQueue(@Nonnull Plugin plugin, long ticksPerTask) {
        this.plugin = plugin;
        this.tasks = new ConcurrentLinkedQueue<>();
        this.ticksPerTask = Math.max(1, ticksPerTask);
        this.running = false;
    }

    /**
     * Adds a task to the queue
     * @param task The task to add
     */
    public void enqueue(@Nonnull Runnable task) {
        tasks.offer(new QueuedTask(task, null));
        startIfNeeded();
    }

    /**
     * Adds a task to the queue with a completion callback
     * @param task The task to add
     * @param onComplete Callback to run when task completes
     */
    public void enqueue(@Nonnull Runnable task, @Nonnull Consumer<Boolean> onComplete) {
        tasks.offer(new QueuedTask(task, onComplete));
        startIfNeeded();
    }

    /**
     * Starts the queue if not already running
     */
    private void startIfNeeded() {
        if (!running && !tasks.isEmpty()) {
            running = true;
            scheduleNext();
        }
    }

    /**
     * Schedules the next task
     */
    private void scheduleNext() {
        if (tasks.isEmpty()) {
            running = false;
            return;
        }

        currentTask = new BukkitRunnable() {
            @Override
            public void run() {
                QueuedTask queuedTask = tasks.poll();
                if (queuedTask != null) {
                    boolean success = true;
                    try {
                        queuedTask.task.run();
                    } catch (Exception e) {
                        success = false;
                        plugin.getLogger().warning("Task in TaskQueue failed: " + e.getMessage());
                        e.printStackTrace();
                    }

                    if (queuedTask.onComplete != null) {
                        try {
                            queuedTask.onComplete.accept(success);
                        } catch (Exception e) {
                            plugin.getLogger().warning("Task completion callback failed: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }

                // Schedule next task
                if (!tasks.isEmpty()) {
                    scheduleNext();
                } else {
                    running = false;
                }
            }
        }.runTaskLater(plugin, ticksPerTask);
    }

    /**
     * Gets the number of pending tasks
     * @return The number of pending tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the queue is empty
     * @return True if the queue is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks if the queue is currently running
     * @return True if the queue is running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Clears all pending tasks
     */
    public void clear() {
        tasks.clear();
        if (currentTask != null && !currentTask.isCancelled()) {
            currentTask.cancel();
        }
        running = false;
    }

    /**
     * Stops the queue and clears all tasks
     */
    public void stop() {
        clear();
    }

    /**
     * Gets the ticks per task
     * @return The ticks per task
     */
    public long getTicksPerTask() {
        return ticksPerTask;
    }

    /**
     * Adds a repeating task to the queue
     * @param delay Initial delay in ticks
     * @param repetitions Number of repetitions
     * @param task The task to repeat
     * @return This TaskQueue for chaining
     */
    public TaskQueue thenRepeatEvery(long delay, int repetitions, @Nonnull Runnable task) {
        enqueue(() -> {
            new BukkitRunnable() {
                private int count = 0;
                
                @Override
                public void run() {
                    if (count >= repetitions) {
                        cancel();
                        return;
                    }
                    
                    try {
                        task.run();
                    } catch (Exception e) {
                        plugin.getLogger().warning("Repeating task failed: " + e.getMessage());
                        e.printStackTrace();
                    }
                    
                    count++;
                }
            }.runTaskTimer(plugin, delay, delay);
        });
        return this;
    }

    /**
     * Adds a delayed task to the queue
     * @param delay Delay in ticks
     * @param task The task to run
     * @return This TaskQueue for chaining
     */
    public TaskQueue thenRun(long delay, @Nonnull Runnable task) {
        enqueue(() -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        plugin.getLogger().warning("Delayed task failed: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }.runTaskLater(plugin, delay);
        });
        return this;
    }

    /**
     * Executes the queue (for compatibility with Dough API)
     * @param plugin The plugin instance (ignored, uses constructor plugin)
     */
    public void execute(@Nonnull Plugin plugin) {
        // Queue automatically starts when tasks are added
        // This method exists for API compatibility
    }

    /**
     * Internal class to hold queued tasks
     */
    private static class QueuedTask {
        final Runnable task;
        final Consumer<Boolean> onComplete;

        QueuedTask(Runnable task, Consumer<Boolean> onComplete) {
            this.task = task;
            this.onComplete = onComplete;
        }
    }
}
