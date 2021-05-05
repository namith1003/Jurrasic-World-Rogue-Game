package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BreedingAction extends Action {
    private Dinosaur target;
    public BreedingAction(Dinosaur dinosaur) {
        target = dinosaur;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.getGender().equals("female")){
            target.addCapability(BreedingStatus.IS_PREGNANT);
            target.removeCapability(BreedingStatus.CAN_BREED);
        }else {
            actor.addCapability(BreedingStatus.IS_PREGNANT);
            actor.removeCapability(BreedingStatus.CAN_BREED);
        }
        return "Dinosaur breeds";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}