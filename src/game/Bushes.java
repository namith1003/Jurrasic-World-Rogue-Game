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

    public void ripening(){

        if (new Random().nextInt(10) == 0){
            Fruit fruit = new Fruit("fruit", 'f');
            fruit.addCapability(FruitStatus.ON_BUSH);
            fruits.add(fruit);
        }
    }

}
