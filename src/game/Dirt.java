package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents bare dirt and will help check if the dirt can produce a bush every turn.
 */
public class Dirt extends Ground {

	/**
	 * the map that the dirt is on
	 */
	private GameMap map;
	/**
	 * list of all the display characters of the ground types around the dirt for it to know what ground types
	 * are surrounding it
	 */
	ArrayList<Character> aroundGround = new ArrayList<>();

	/**
	 * the constructor for the dirt class
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * provides the passage of time on the dirt and checks if it is possible for the dirt to grow a bush in the turn,
	 * if the probability to grow is met then it will create and set a bush on the location this dirt is on.
	 * @param location The location of the  dirt on the map
	 */
	@Override
	public void tick(Location location) {
		boolean bushGrew = growBush(location);

		if (bushGrew){
			location.setGround(new Bushes());
		}
	}

	/**
	 * checks if the ground surrounding the dirt tile can allow the bush to grow faster or not at all and also creates
	 * a bush if the probability is met, if a tree is next to the dirt a bush cannot grow on it, if at least 2 bushes
	 * next to the dirt is has a higher 10% chance to grow a bush and if none of these it has a base 1% chance to
	 * grow a bush.
	 * @param location the location of the dirt object on the map
	 * @return a boolean of whether a bush has grown on this dirt in this turn
	 */
	private boolean growBush(Location location) {
		int bushCount = 0;
		int treeCount = 0;

		map = location.map();


		List<Exit> exits = location.getExits();

		// checks all the ground objects around it for trees and bushes
		for (Exit allExits: exits){
			aroundGround.add(allExits.getDestination().getGround().getDisplayChar());
		}

		// checks for the amount of trees and bushes around it
		for (char tile: aroundGround){
			if (tile == 'b'){
				bushCount++;
			}
			if (tile == '+'){
				treeCount++;
			}
		}

		// if there are trees it cannot grow a bush
		if (treeCount != 0){
			return false;
		}
		// if there is more than 2 bushes 10% chance to grow a bush
		else if (bushCount >= 2){
			return new Random().nextInt(10) == 0;
		}
		// if there is no special conditions it has a base 1% chance to grow a bush
		else{
			return new Random().nextInt(100) == 0;
		}
	}
}
