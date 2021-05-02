package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Fruit extends FoodItem{

    public int onFloorCounter = 0;

    public Fruit(String name, char displayChar) {
        super(name, displayChar);
    }

    @Override
    public void tick(Location currentLocation) {
        if (hasCapability(FruitStatus.ON_FLOOR)){
            onFloorCounter++;
        }

        if (onFloorCounter > 15){
            rot();
        }

    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (hasCapability(FruitStatus.ON_FLOOR)){
            onFloorCounter++;
        }
    }

    private void rot() {
    }
}
