package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SwitchMapAction extends Action {

    static int x;
    private GameMap playerMap;

    public SwitchMapAction(GameMap map) {
        playerMap = map;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.locationOf(actor).y() == 0) {
            x = map.locationOf(actor).x();
            map.removeActor(actor);
            return "Player has crossed to the other map!";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        if (Application.mapNum == 2 && playerMap.locationOf(actor).y() == 24){
            return "Player moves south";
        }
        else if (Application.mapNum == 1 && playerMap.locationOf(actor).y() == 0){
            return "Player moves north";
        }

        return "";
    }
}
