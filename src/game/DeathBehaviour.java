package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class DeathBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        int unconsciousCounter = actor.getUnconsciousCounter();
        boolean isConscious = actor.isConscious();
        if (!isConscious){
            actor.increaseUnconsciousCounter();
            switch (actor.toString()){
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
        actor.setUnconsciousCounter(0);
        return null;
    }
}
