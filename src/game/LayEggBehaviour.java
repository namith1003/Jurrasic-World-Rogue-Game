package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class to determine if a female dinosaur can lay an egg.
 */
public class LayEggBehaviour implements Behaviour{
    /**
     * A method to determine if the female dinosaur is pregnant
     * AND the amount of turns to carry the egg has reached its max.
     * Calls LayEggAction if that's the case, or else return nothing.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return A return string from LayEggAction to indicate that an egg has been laid.
     * Or else return nothing.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(BreedingStatus.IS_PREGNANT)) {
            actor.updatePregnantCounter();
            if (actor.getPregnantCounter() >= actor.getPregnantTime()) {
                return new LayEggAction();
            }
        }
        return null;
    }
}
