package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * the action for the user fishing on the lake
 */
public class FishAction extends Action {
    /**
     * the lake that the player will fish on
     */
    private Lake lake;

    /**
     * constructor for the fish Action Class
     * @param lake the lake that the player will fish on
     */
    public FishAction(Lake lake) {
        this.lake = lake;
    }

    /**
     * executing the action for the player to fish on the selected lake, this removes a fish from the lake
     * and adds it to the players inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string with details on the result of the players fishing attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (lake.getFish().size() != 0){
            Fish removedFish = lake.removeFish();
            actor.addItemToInventory(removedFish);

            return actor+ " has caught a fish";
        }
        return actor + " has not found any fish";
    }

    /**
     * the menu option for the user to perform a fishing action once hes on a lake
     * @param actor The actor performing the action.
     * @return the menu option user sees to select to perform the fishing action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches fish";
    }
}
