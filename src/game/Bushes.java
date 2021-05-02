package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public class Bushes extends Ground {
    /**
     * A class that represents the bushes.
     */

    private ArrayList<Fruit> fruits = new ArrayList<>();

    public Bushes() {
        super('b');
    }

    @Override
    public void tick(Location location) {
        ripening();
    }

    public boolean ripening(){

        if (new Random().nextInt(10) == 0){
            Fruit fruit = produceFruit();
            fruits.add(fruit);
            return true;
        }
        return false;
    }

    public Fruit produceFruit(){
        Fruit fruit = new Fruit("fruit", 'f');
        fruit.addCapability(FruitStatus.ON_BUSH);

        return fruit;
    }

}
