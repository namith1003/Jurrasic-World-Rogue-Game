package edu.monash.fit2099.interfaces;

import game.Fruit;

import java.util.ArrayList;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {

    default ArrayList<Fruit> getFruits() {
        return null;
    }

    default Fruit removeFruit(){
        return null;
    }

}
