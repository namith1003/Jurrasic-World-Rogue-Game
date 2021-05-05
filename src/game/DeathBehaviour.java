package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class DeathBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        int unconsciousCounter = dinosaur.getUnconsciousCounter();
        boolean isConscious = dinosaur.isConscious();
        if (!isConscious){
            dinosaur.increaseUnconsciousCounter();
            switch (dinosaur.toString()){
                case "Stegosaur", "Allosaur" ->{
                    if (unconsciousCounter >= 20){
                        return new DeathAction();
                    }
                }
                case "Brachiosaur" ->{
                    if (unconsciousCounter >= 15){
                        return new DeathAction();
                    }
                }
            }
            return new DoNothingAction();
        }
        dinosaur.setUnconsciousCounter(0);
        return null;
    }
}
