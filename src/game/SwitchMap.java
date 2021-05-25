package game;

import edu.monash.fit2099.engine.*;

/**
 * The class for the ground item at the top of them map that acts like the border for the player
 * to move between the maps.
 */
public class SwitchMap extends Ground {

    private Location nextMapLocation;

    /**
     * Constructor.
     *
     * @param location  location to be set
     */
    public SwitchMap(Location location) {
        super('`');
        this.nextMapLocation = location;

    }

    /**
     * player cannot enter the border of the map.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * the action for the player to move between the maps.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(new MoveActorAction(nextMapLocation, direction));
        return actions;
    }
}
