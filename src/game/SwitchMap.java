package game;

import edu.monash.fit2099.engine.*;

/**
 * Terrain type to allow user to move from one map to another.
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

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(new MoveActorAction(nextMapLocation, direction));
        return actions;
    }
}
