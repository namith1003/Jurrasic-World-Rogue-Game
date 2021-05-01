package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private GameMap map;
	ArrayList<Character> aroundGround = new ArrayList<>();

	public Dirt() {
		super('.');
	}

	@Override
	public void tick(Location location) {
		boolean bushGrew = growBush(location);

		if (bushGrew){
			location.setGround(new Bushes());
		}
	}

	private boolean growBush(Location location) {
		int bushCount = 0;
		int treeCount = 0;

		map = location.map();
		int x = location.x();
		int y = location.y();


		if (x != 0){
			aroundGround.add(map.at(x-1, y).getGround().getDisplayChar());
		}
		if (x != 79){
			aroundGround.add(map.at(x+1, y).getGround().getDisplayChar());
		}
		if (y != 0){
			aroundGround.add(map.at( x, y-1).getGround().getDisplayChar());
		}
		if (y != 24){
			aroundGround.add(map.at(x, y+1).getGround().getDisplayChar());
		}
		if (x != 0 && y != 0){
			aroundGround.add(map.at(x-1, y-1).getGround().getDisplayChar());
		}
		if (x != 0 && y != 24){
			aroundGround.add(map.at(x-1, y+1).getGround().getDisplayChar());
		}
		if (x != 79 && y != 0){
			aroundGround.add(map.at(x+1, y-1).getGround().getDisplayChar());
		}
		if (x != 79 && y != 24){
			aroundGround.add(map.at(x+1, y+1).getGround().getDisplayChar());
		}

		for (char tile: aroundGround){
			if (tile == 'b'){
				bushCount++;
			}
			if (tile == '+'){
				treeCount++;
			}
		}

		if (treeCount != 0){
			return false;
		}
		else if (bushCount >= 2){
			return new Random().nextInt(10) == 0;
		}
		else{
			return new Random().nextInt(100) == 0;
		}
	}
}
