package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

public class PickFruitAction extends Action {
    protected Ground treeOrBush;

    public PickFruitAction(Ground treeOrBush) {
        this.treeOrBush = treeOrBush;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        String groundType = "";
        if (treeOrBush.getDisplayChar() == '*'){
            groundType = "Bush";
        } else if (treeOrBush.getDisplayChar() == '+' || treeOrBush.getDisplayChar() == 't'|| treeOrBush.getDisplayChar() == 'T'){
            groundType = "Tree";
        }

        return actor + " picks fruit from " + groundType;
    }
}

