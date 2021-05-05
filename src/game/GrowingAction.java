package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class GrowingAction extends Action {

    /**
     * The Actor that is to grow
     */
    protected Actor target;

    public GrowingAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        switch (actor.toString()) {
            case "Stegosaur" -> {
                map.locationOf(actor).addActor(new Stegosaur("Stegosaur", true));
                map.removeActor(actor);
            }
            case "Brachiosaur" -> {
                map.locationOf(actor).addActor(new Brachiosaur("Brachiosaur", true));
                map.removeActor(actor);
            }
            case "Allosaur" -> {
                map.locationOf(actor).addActor(new Allosaur("Allosaur", true));
                map.removeActor(actor);
            }
        }
        return actor.toString() + "has grown up!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
