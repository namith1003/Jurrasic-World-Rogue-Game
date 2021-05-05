package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class LayEggBehaviour implements Behaviour{
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
