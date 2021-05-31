package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * The class that "grows" the input dinosaur.
 */
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
     * The function to "grow" the actor by calling the actor here all actors passed in
     * will be dinosaurs and will set their properties to that of an adult and set their
     * is adult varaible to true.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string to indicate that the dinosaur has "grown up"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.setIsAdult(true);
        switch (actor.toString()) {
            case "Stegosaur" -> {
                actor.setHitPoints(50);
                actor.setDisplayChar('S');
                actor.setPregnantCounter(0);
            }
            case "Brachiosaur" -> {
                actor.setHitPoints(100);
                actor.setDisplayChar('B');
                actor.setPregnantCounter(0);
            }
            case "Allosaur" -> {
                actor.setHitPoints(50);
                actor.setDisplayChar('A');
                actor.setPregnantCounter(0);
            }
            case "Pterodactyl" -> {
                actor.setHitPoints(100);
                actor.setDisplayChar('P');
                actor.setPregnantCounter(0);
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
