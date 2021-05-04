package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BreedingAction extends Action {
    public BreedingAction(Dinosaur dinosaur) {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return "Brachiosaur breeds";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
