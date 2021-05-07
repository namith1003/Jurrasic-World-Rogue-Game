package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * A class for a vegetarian meal kit.
 * The player should be able to purchase a said meal kit from the vending machine
 * to feed a Brachiosaur or a Stegosaur.
 */
public class VegeMealKit extends MealKit{
    /**
     * The constructor for the VegeMealKit
     * @param name name of the vegetarian meal kit
     * @param displayChar the display character for the meal kit
     */
    public VegeMealKit(String name, char displayChar) {
        super(name, displayChar);
    }

    /**
     * A tick function.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    /**
     * Another tick function
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}
