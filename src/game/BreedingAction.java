package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * The action class for the activity of breeding between the dinosaurs
 */
public class BreedingAction extends Action {
    /**
     * The target actor that this actor has found the closest to breed with
     */
    private Actor target;

    /**
     * constructor for the breeding action
     * @param dinosaur the closest target actor that this actor can breed with
     */
    public BreedingAction(Actor dinosaur) {
        target = dinosaur;
    }

    /**
     * performs the breeding action between the two dinosaurs
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened during the breeding that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.getGender().equals("female")){
            target.addCapability(BreedingStatus.IS_PREGNANT);
            target.removeCapability(BreedingStatus.CAN_BREED);
        }else {
            actor.addCapability(BreedingStatus.IS_PREGNANT);
            actor.removeCapability(BreedingStatus.CAN_BREED);
        }
        return actor.toString() + " breeds";
    }

    /**
     * no menu will be displayed to the user while this action is occurring
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
