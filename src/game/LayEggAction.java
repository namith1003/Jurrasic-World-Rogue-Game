package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class for the dinosaurs to lay eggs.
 */
public class LayEggAction extends Action {
    /**
     * A method for the dinosaurs to lay an egg if the conditions are met.
     * Creates an egg on the current location of the mother dinosaur.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string to indicate that an egg has been laid.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(BreedingStatus.IS_PREGNANT);
        actor.setPregnantCounter(0);

        char egg;
        switch (actor.toString()) {
            case "Allosaur" -> egg = '0';
            case "Brachiosaur" -> egg = 'O';
            case "Stegosaur" -> egg = 'o';
            default -> throw new IllegalStateException("Unexpected value: " + actor.toString());
        }

        map.locationOf(actor).addItem(new Egg(actor.toString(), egg));
        return actor.toString() + " laid an Egg";
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
