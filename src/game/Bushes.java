package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents the bushes.
 */
public class Bushes extends Ground {

    /**
     * the list of all the ripe fruits that are produced by this bush
     */
    protected ArrayList<Fruit> fruits = new ArrayList<>();

    /**
     * the constructor for the bush to have the display char *
     */
    public Bushes() {
        super('*');
    }

    /**
     * Returns the actions that the player can do to the bush and here it is only picking fruits from the bush.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new PickFruitBushAction(this));
    }

    /**
     * the passage of time for this bush through each turn and what the bush does in each turn, it only tries to ripen a fruit
     * on each turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        ripening();
    }

    /**
     * the class checks if the chances for a ripe fruit to be produced is right and calls a method to produce a fruit on the bush
     * and then adds the returned fruit to the bush
     * @return whether or not a fruit was created on the bush
     */
    public boolean ripening(){

        if (new Random().nextInt(10) == 0){
            Fruit fruit = produceFruit();
            fruits.add(fruit);
            return true;
        }
        return false;
    }

    /**
     * produces a fruit on the bush
     * @returns the fruit that was created
     */
    public Fruit produceFruit(){
        Fruit fruit = new Fruit("Fruit");
        fruit.addCapability(FruitStatus.ON_BUSH);
        return fruit;
    }

    /**
     * gets the list of fruits that are in the bush
     * @return the array list of all the fruits on this bush
     */
    @Override
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    /**
     * removes a fruit from the bush once it may be plucked or eaten from the bush
     * @return the fruit that was removed from the list of fruits on the bush
     */
    @Override
    public Fruit removeFruit(){
        if (fruits.size() != 0) {
            return fruits.remove(fruits.size() - 1);
        }
        return null;
    }

}
