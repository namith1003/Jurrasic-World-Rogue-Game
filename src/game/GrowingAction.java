package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class GrowingAction extends Action {

    /**
     * The Actor that is to grow
     */
    protected Actor target;

    /**
     * THe constructor for GrowingAction.
     * @param target THe actor to grow.
     */
    public GrowingAction(Actor target) {
        this.target = target;
    }

    /**
     * The function to "grow" the actor by removing the current instance of the actor and
     * creating a new instance of the same actor type with higher health at the location
     * of the previous actor.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string to indicate that the dinosaur has "grown up"
     */
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

    /**
     * A function to return a string for the player to do something.
     * But does nothing here since this class isn't supposed to be accessible to players.
     * @param actor The actor performing the action.
     * @return null.
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
