package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class VendingMachine extends Ground {

    private String[] vendingItems = {"Fruit", "VegeMealKit", "MeatMealKit", "Egg", "LazerGun"};
    public VendingMachine() {
        super('V');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new VendingAction(this));
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
