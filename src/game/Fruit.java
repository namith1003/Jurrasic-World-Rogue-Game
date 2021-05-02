package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

public class Fruit extends FoodItem{

    private int onFloorCounter = 0;

    public Fruit(String name, char displayChar) {
        super(name, displayChar);
        healValue = 10;
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
    }

    @Override
    public PickUpItemAction getPickUpAction() {
        removeCapability(FruitStatus.ON_FLOOR);
        addCapability(FruitStatus.IN_INVENTORY);
        healValue = 20;
        return super.getPickUpAction();
    }

    @Override
    public DropItemAction getDropAction() {
        removeCapability(FruitStatus.IN_INVENTORY);
        addCapability(FruitStatus.ON_FLOOR);
        healValue = 10;
        return super.getDropAction();
    }

    private void rot() {
    }
}
