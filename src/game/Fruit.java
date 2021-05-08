package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * the class for the fruits being created within the game, a food object that is edible by the herbivores and feed able
 * and portable by the player.
 */
public class Fruit extends FoodItem{

    /**\
     * a counter to keep track of how many turns the fruit has been on the floor for.
     */
    private int onFloorCounter = 0;

    /**
     * the constructor for creating fruits.
     * @param name the name of the fruit when it is created.
     */
    public Fruit(String name) {
        super(name, 'f');
        healValue = 10;
        price = 30;
    }

    /**
     * provides the passage of time to the fruit object and checks if its on the floor, if it has been on the floor
     * for over the required amount of turns it will rot.
     * @param currentLocation The location of the fruit on the map.
     */
    @Override
    public void tick(Location currentLocation) {
        removeCapability(FruitStatus.IN_INVENTORY);
        addCapability(FruitStatus.ON_FLOOR);
        healValue = 10;

        if (hasCapability(FruitStatus.ON_FLOOR)){
            displayChar = 'f';
            onFloorCounter++;
        }
        if (onFloorCounter > 15){
            rot(currentLocation);
        }
    }

    /**
     * the passage of time of when the fruit exists in the players inventory, the fruit will pause to rot while
     * it is in the players inventory and will heal for more when it is fed to a dinosaur
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        removeCapability(FruitStatus.ON_FLOOR);
        addCapability(FruitStatus.IN_INVENTORY);
        healValue = 20;
    }

    /**
     * will remove the fruit from the map when it rots
     * @param currentLocation the location of the fruit where it will rot
     */
    public void rot(Location currentLocation) {
        currentLocation.removeItem(this);
    }

    @Override
    public int getOnFloorCounter() {
        return onFloorCounter;
    }


}
