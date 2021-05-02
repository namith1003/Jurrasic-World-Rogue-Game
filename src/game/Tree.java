package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Fruit> fruits = new ArrayList<>();

	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		ripening();
	}

	public boolean ripening(){

		if (new Random().nextInt(2) == 0){
			Fruit fruit = produceFruit();
			fruits.add(fruit);
			return true;
		}
		return false;
	}

	public Fruit produceFruit(){
		Fruit fruit = new Fruit("Fruit", 'f');
		fruit.addCapability(FruitStatus.ON_TREE);
		Player.points.setPoints(1);

		return fruit;
	}
}
