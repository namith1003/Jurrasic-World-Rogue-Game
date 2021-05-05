package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DeathAction extends Action {
    public DeathAction() {

    }

    @Override
    public String execute(Actor actor, GameMap map) {

        String dinosaurName = actor.toString();
        map.locationOf(actor).addItem(new DinoCorpse(actor.toString()+ " Corpse"));
        map.removeActor(actor);
        return dinosaurName + " is dead";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
