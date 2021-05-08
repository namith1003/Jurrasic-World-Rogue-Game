package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {

    /**
     * return the amount that the item will heal for
     * @return the amount that the item will heal for
     */
    default int getHealValue() {
        return 0;
    }

    /**
     * returns the price of the food item
     * @return the price of the food item
     */
    default int getPrices(){return 0;}

}
