package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * A class for a carnivorous meal kit.
 * The player should be able to purchase a said meal kit from the vending machine
 * to feed an Allosaur
 */
public class MeatMealKit extends MealKit{
    /**
     * The constructor for the MeatMealKit.
     * @param name name of the carnivorous meal kits
     * @param displayChar the display character for the meal kit
     */
    public MeatMealKit(String name, char displayChar) {
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
     * Another tick function.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}
