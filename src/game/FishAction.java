package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class FishAction extends Action {
    private Lake lake;
    public FishAction(Lake lake) {
        this.lake = lake;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (lake.getFish().size() != 0){
            Fish removedFish = lake.removeFish();
            actor.addItemToInventory(removedFish);

            return actor+ " has caught a fish";
        }
        return actor + " has not found any fish";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches fish";
    }
}
