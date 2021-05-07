package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class to create Tree object
 */
public class Tree extends Ground {
	/**
	 * a variable to store the age of the tree
	 */
	private int age = 0;

	/**
	 * an ArrayList to store the fruits
	 */
	protected ArrayList<Fruit> fruits = new ArrayList<>();

	/**
	 * The constructor.
	 */
	public Tree() {
		super('+');
	}

	/**
	 * A method to check if the tree has fruits for the player to pick
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return A return string from PickFruitTreeAction to indicate if a fruit has been picked or not.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(new PickFruitTreeAction(this));
	}

	/**
	 * A tick function. After several ticks, the trees will grow.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		ripening(location);
		dropsFruit(location);
	}

	/**
	 * A method to randomly determine whether the tree produces a fruit.
	 * Calls produceFruit method to do so if it wins the 20/80 coin flip.
	 * @param location The location of the current tree
	 * @return A boolean to indicate if a fruit has been produced or not.
	 */
	public boolean ripening(Location location){

		if (new Random().nextInt(2) == 0){
			Fruit fruit = produceFruit(location);
			fruits.add(fruit);
			return true;
		}
		return false;
	}

	/**
	 * A method to produce a fruit.
	 * @param location The location of the tree
	 * @return The fruit object.
	 */
	public Fruit produceFruit(Location location){
		Fruit fruit = new Fruit("Fruit");
		fruit.addCapability(FruitStatus.ON_TREE);
		Player.points.setPoints(1);

		return fruit;
	}

	/**
	 * A method to drop a ripe fruit from the tree onto the ground.
	 * @param location The location of the tree
	 */
	public void dropsFruit(Location location){
		if(new Random().nextInt(20) == 0 && fruits.size() != 0){
			Fruit droppedFruit = fruits.remove(fruits.size() - 1);
			droppedFruit.removeCapability(FruitStatus.ON_TREE);
			droppedFruit.addCapability(FruitStatus.ON_FLOOR);
			location.addItem(droppedFruit);
		}
	}

	/**
	 * Gets the list of fruits.
	 * @return Fruits
	 */
	@Override
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	/**
	 * Removes fruits.
	 * @return Either removes fruits from the fruits ArrayList, or nothing.
	 */
	@Override
	public Fruit removeFruit(){
		if (fruits.size() != 0) {
			return fruits.remove(fruits.size() - 1);
		}
		return null;
	}
}
