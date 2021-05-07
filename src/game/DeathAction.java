package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * action for when the actor dies, it will remove the actor off of the map and add a corpse for the dinosaur of its
 * species on the location that the actor died on. and returns a message that states what has occured.
 */
public class DeathAction extends Action {

    /**
     * performs the action of death on the actor dinosaur
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the dinosaur that has died
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String dinosaurName = actor.toString();
        map.locationOf(actor).addItem(new DinoCorpse(actor.toString() + " Corpse"));
        System.out.println(actor.toString() + " Corpse");
        map.removeActor(actor);
        return dinosaurName + " is dead";
    }

    /**
     * no menu will be displayed to the user while this action is occurring
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
