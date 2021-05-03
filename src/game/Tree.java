package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;
	protected ArrayList<Fruit> fruits = new ArrayList<>();

	public Tree() {
		super('+');
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(new PickFruitTreeAction(this));
	}

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

	public boolean ripening(Location location){

		if (new Random().nextInt(2) == 0){
			Fruit fruit = produceFruit(location);
			fruits.add(fruit);
			return true;
		}
		return false;
	}

	public Fruit produceFruit(Location location){
		Fruit fruit = new Fruit("Fruit");
		fruit.addCapability(FruitStatus.ON_TREE);
		location.addItem(fruit);
		Player.points.setPoints(1);

		return fruit;
	}

	public void dropsFruit(Location location){
		if(new Random().nextInt(20) == 0 && fruits.size() != 0){
			Fruit droppedFruit = fruits.remove(fruits.size() - 1);
			droppedFruit.removeCapability(FruitStatus.ON_TREE);
			droppedFruit.addCapability(FruitStatus.ON_FLOOR);
			location.addItem(droppedFruit);
		}
	}
}
