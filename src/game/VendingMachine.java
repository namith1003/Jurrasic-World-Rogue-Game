package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A vending machine class.
 */
public class VendingMachine extends Ground {

    /**
     * A final string list to store the name of the items. Doesn't seem to be used tho.
     */
    private final String[] vendingItems = {"Fruit", "VegeMealKit", "MeatMealKit", "Egg", "LazerGun"};

    /**
     * The constructor for the vending machine.
     */
    public VendingMachine() {
        super('V');
    }

    /**
     * A tick function for the vending machine
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    /**
     * A method that calls an instance of VendingAction for the player to access the vending machine.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A return string from VendingAction to indicate the actor has accessed the vending machine.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new VendingAction(this));
    }

    /**
     * A method for the actor to know that the vending machine counts as impassable terrain
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Seems like a method only used in the mars demo. Unused for dino farmville it seems.
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
