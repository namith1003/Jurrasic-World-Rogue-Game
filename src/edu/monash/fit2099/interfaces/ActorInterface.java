package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {

    /**
     * gets the number of turns the dinosaur has been pregnant for.
     * @return the number of turns the dinosaur has been pregnant for.
     */
    default int getPregnantCounter(){
        return 0;
    }

    /**
     * sets the new number of turns the dinosaur has been pregnant for to a new value.
     * @param pregnantCounter the new number of turns the dinosaur has been pregnant for.
     */
    default void setPregnantCounter(int pregnantCounter) {}

    /**
     * increments the number of turns the dinosaur has been pregnant for, by incrementing the pregnantCounter.
     */
    default void updatePregnantCounter(){}

    /**
     * gets the number of turns the dinosaur should be pregnant for before laying her egg
     * @return the number of turns the dinosaur should be pregnant for before laying her egg
     */
    default int getPregnantTime(){
        return 0;
    }

    /**
     * sets the number of turns the dinosaur should be pregnant for before laying her egg to a new value
     * @param pregnantTime the new number of turns the dinosaur should be pregnant for before laying her egg
     */
    default void setPregnantTime(int pregnantTime){}

    /**
     * gets the gender of the dinosaur
     * @return the gender of the dinosaur
     */
    default String getGender(){
        return null;
    }

    /**
     * gets the number of turns the dinosaur has been at a stretch unconscious for.
     * @return the number of turns the dinosaur has been at a stretch unconscious for.
     */
    default int getUnconsciousCounter(){
        return 0;
    }

    /**
     * increments the number of turns the dinosaur has been at a stretch unconscious for.
     */
    default void increaseUnconsciousCounter(){
    }

    /**
     * sets the new number of turns the dinosaur has been at a stretch unconscious for to a new value.
     * @param unconsciousCounter the new number of turns the dinosaur has been at a stretch unconscious for the dinosaur.
     */
    default void setUnconsciousCounter(int unconsciousCounter){}

    /**
     * gets the current age of the dinosaur
     * @return the current age of the dinosaur
     */
    default int getAge() {
        return 0;
    }

    /**
     * sets the current age of the dinosaur with a new value
     * @param newAge the new value to set the current age of the dinosaur to
     */
    default void setAge(int newAge) {}

    /**
     * set the hit points / food level of the dinosaur
     * @param hitPoints the new hit points of the dinosaur to be set to
     */
    default void setHitPoints(int hitPoints){}

    /**
     * sets the new display character that represents the dinosaur on the map
     * @param displayChar the new display character that represents the dinosaur on the map
     */
    default void setDisplayChar(char displayChar){}

    /**
     * sets the boolean representing whether or not the dinosaur is an adult
     * @param isAdult the boolean representing whether or not the dinosaur is an adult
     */
    default void setIsAdult(boolean isAdult) {}

    /**
     * returns the boolean value representing whether or not the dinosaur is an adult
     * @return the boolean value representing whether or not the dinosaur is an adult
     */
    default boolean getIsAdult() {
        return false;
    }

    /**
     * returns the diet of the Actor which usually is a Dinosaur
     * @return the diet of the Actor
     */
    default String[] getDiet() {
        return null;
    }

    /**
     * return the maximum hit points of the Actor.
     * @return the maximum hit points of the actor.
     */
    default int getMaximumHitPoints(){
        return 0;
    }

    default boolean isConsciousThirst() {
        return false;
    }

    default boolean isThirsty(GameMap map){return false;}

    default Action findLake(GameMap map, Location targetLocation){
        return null;
    }
}
