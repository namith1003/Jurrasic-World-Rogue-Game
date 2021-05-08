package game;

/**
 * the status of the fruits that are created, whether they are in players inventory or on a bush or tree or even
 * on the floor.
 */
public enum FruitStatus {
    /**
     * the enum value to state the fruit with this in its capabilities is on the floor
     */
    ON_FLOOR,
    /**
     * the enum value to state the fruit with this in its capabilities is on the tree
     */
    ON_TREE,
    /**
     * the enum value to state the fruit with this in its capabilities is on the bush
     */
    ON_BUSH,
    /**
     * the enum value to state the fruit with this in its capabilities is in the players inventory
     */
    IN_INVENTORY
}
