package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Fruit extends FoodItem{

    private int onFloorCounter = 0;

    public Fruit(String name) {
        super(name, 'f');
        healValue = 10;
    }

    @Override
    public void tick(Location currentLocation) {
        removeCapability(FruitStatus.IN_INVENTORY);
        addCapability(FruitStatus.ON_FLOOR);
        healValue = 10;
        if (hasCapability(FruitStatus.ON_FLOOR)){
            onFloorCounter++;
        }
        if (onFloorCounter > 15){
            rot(currentLocation);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        removeCapability(FruitStatus.ON_FLOOR);
        addCapability(FruitStatus.IN_INVENTORY);
        healValue = 20;
    }

    public void rot(Location currentLocation) {
        currentLocation.removeItem(this);
    }
}
