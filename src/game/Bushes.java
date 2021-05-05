package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public class Bushes extends Ground {
    /**
     * A class that represents the bushes.
     */

    protected ArrayList<Fruit> fruits = new ArrayList<>();

    public Bushes() {
        super('*');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new PickFruitBushAction(this));
    }

    @Override
    public void tick(Location location) {
        ripening(location);
    }

    public boolean ripening(Location location){

        if (new Random().nextInt(10) == 0){
            Fruit fruit = produceFruit(location);
            fruits.add(fruit);
            return true;
        }
        return false;
    }

    public Fruit produceFruit(Location location){
        Fruit fruit = new Fruit("Fruit");
        fruit.addCapability(FruitStatus.ON_BUSH);
        return fruit;
    }

    @Override
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    @Override
    public Fruit removeFruit(){
        if (fruits.size() != 0) {
            return fruits.remove(fruits.size() - 1);
        }
        return null;
    }

}
