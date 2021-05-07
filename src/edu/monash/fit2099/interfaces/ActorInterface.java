package edu.monash.fit2099.interfaces;

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

    default void setHitPoints(int hitPoints){}

    default void setDisplayChar(char displayChar){}

    default void setIsAdult(boolean isAdult) {}

    default boolean getIsAdult() {
        return false;
    }
}
