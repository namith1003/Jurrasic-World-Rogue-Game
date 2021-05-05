package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {


    default int getPregnantCounter(){
        return 0;
    }

    default void setPregnantCounter(int pregnantCounter) {
    }

    default void updatePregnantCounter(){

    }

    default int getPregnantTime(){
        return 0;
    }

    default void setPregnantTime(int pregnantTime){
    }

    default String getGender(){
        return null;
    }

    default int getUnconsciousCounter(){
        return 0;
    }

    default void increaseUnconsciousCounter(){
    }
    default void setUnconsciousCounter(int unconsciousCounter){
    }
}
