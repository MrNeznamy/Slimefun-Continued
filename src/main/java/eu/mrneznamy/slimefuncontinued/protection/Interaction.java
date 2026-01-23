package eu.mrneznamy.slimefuncontinued.protection;

/**
 * Represents different types of interactions for protection systems
 * Replaces io.github.bakedlibs.dough.protection.Interaction
 */
public enum Interaction {
    
    /**
     * Interaction for breaking blocks
     */
    BREAK_BLOCK,
    
    /**
     * Interaction for placing blocks
     */
    PLACE_BLOCK,
    
    /**
     * Interaction for interacting with blocks (right-click)
     */
    INTERACT_BLOCK,
    
    /**
     * Interaction for attacking entities
     */
    ATTACK_ENTITY,
    
    /**
     * Interaction for attacking players specifically
     */
    ATTACK_PLAYER,
    
    /**
     * Interaction for interacting with entities
     */
    INTERACT_ENTITY,
    
    /**
     * Interaction for accessing containers/inventories
     */
    ACCESS_INVENTORIES
}
