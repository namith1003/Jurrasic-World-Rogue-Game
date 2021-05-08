package game;

/**
 * the enum that adds the capability of the breeding status of the dinosaur, whether or not it can breed or if it
 * is pregnant or not.
 */
public enum BreedingStatus {
    /**
     * the enum value to state the dinosaur with this in its capabilities is pregnant
     */
    IS_PREGNANT,
    /**
     * the enum value to state the dinosaur with this in its capabilities is not hungry and can breed
     */
    CAN_BREED,
    /**
     * the enum value to state the dinosaur with this in its capabilities is hungry and cannot breed
     */
    CANNOT_BREED
}
