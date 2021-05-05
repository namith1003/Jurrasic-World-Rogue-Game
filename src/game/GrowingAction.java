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
        Dinosaur dinosaur = (Dinosaur) actor;
        switch (dinosaur.toString()) {
            case "Stegosaur" -> {
                map.locationOf(dinosaur).addActor(new Stegosaur("Stegosaur", true));
                map.removeActor(dinosaur);
            }
            case "Brachiosaur" -> {
                map.locationOf(dinosaur).addActor(new Brachiosaur("Brachiosaur", true));
                map.removeActor(dinosaur);
            }
            case "Allosaur" -> {
                map.locationOf(dinosaur).addActor(new Allosaur("Allosaur", true));
                map.removeActor(dinosaur);
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
