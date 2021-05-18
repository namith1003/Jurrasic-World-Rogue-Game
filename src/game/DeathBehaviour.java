package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * The behaviour class that checks if the conditions for the actor to die has been met, here its whether the actor
 * has been unconscious for over the required number of turns for it to die
 */
public class DeathBehaviour implements Behaviour {
    /**
     * he behaviour class that checks if the conditions for the actor to die has been met, here its whether the actor
     *  has been unconscious for over the required number of turns for it to die and returns the action to die if the
     *  actor is accepted to die.
     * @param actor the Actor that's going to perform the death action
     * @param map the GameMap containing the Actor
     * @return the death action for the actor and if nothing is possible returns null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        int unconsciousCounter = actor.getUnconsciousCounter();
        // checks if the actor is conscious
        boolean isConscious = actor.isConscious();
        boolean isConsciousThirst = actor.isConsciousThirst();
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
        } else if (!isConsciousThirst){
            actor.increaseUnconsciousCounter();
            if (unconsciousCounter >= 15){
                return new DeathAction();
            }
        }
        actor.setUnconsciousCounter(0);
        return null;
    }
}
