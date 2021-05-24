package edu.monash.fit2099.interfaces;

import game.Fish;
import game.Fruit;

import java.util.ArrayList;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {

    /**
     * gets the list of fruits that are in the bush
     * @return the array list of all the fruits on this bush
     */
    default ArrayList<Fruit> getFruits() {
        return null;
    }

    /**
     * removes a fruit from the bush once it may be plucked or eaten from the bush
     * @return the fruit that was removed from the list of fruits on the bush
     */
    default Fruit removeFruit(){return null;}

    default int getSips() {return 0;}

    default void removeSip(){}

    default ArrayList<Fish> getFish() {
        return null;
    }

    default Fish removeFish(){return null;}
}
