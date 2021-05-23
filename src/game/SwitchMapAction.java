package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class SwitchMapAction extends Action {

    protected GameMap targetMap;
    public SwitchMapAction(GameMap map2) { this.targetMap = map2; }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.locationOf(actor).x() == 0) {
            map.removeActor(actor);
            return "Player has crossed to the other map!";
        }
        return "not used";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player moves north";
    }
}
