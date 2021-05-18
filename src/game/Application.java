package game;

import edu.monash.fit2099.engine.*;

import java.util.Arrays;
import java.util.List;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		
		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(38, 8).addActor(new Stegosaur("Stegosaur", "male"));
		gameMap.at(48, 8).addActor(new Stegosaur("Stegosaur", "female"));

		// Place 4 Brachiosaurs into the map
		gameMap.at(48, 19).addActor(new Brachiosaur("Brachiosaur", "female"));
		gameMap.at(60, 19).addActor(new Brachiosaur("Brachiosaur", "male"));
		gameMap.at(18, 12).addActor(new Brachiosaur("Brachiosaur", "male"));
		gameMap.at(12, 16).addActor(new Brachiosaur("Brachiosaur", "female"));
		gameMap.at(48,10).addActor(new Allosaur("Allosaur"));

		// Placing a vending machine to the map
		gameMap.at(11, 6).setGround(new VendingMachine());

		//placing Lakes on the map
		gameMap.at(22,10).setGround(new Lake());
		gameMap.at(11,20).setGround(new Lake());
		gameMap.at(44,16).setGround(new Lake());
		gameMap.at(22,11).setGround(new Lake());
		gameMap.at(23,11).setGround(new Lake());
		gameMap.at(60,6).setGround(new Lake());
		gameMap.at(72,10).setGround(new Lake());
		gameMap.at(68,22).setGround(new Lake());
		gameMap.at(20,4).setGround(new Lake());
		gameMap.at(40,5).setGround(new Lake());
		gameMap.at(6,13).setGround(new Lake());
		gameMap.at(40,23).setGround(new Lake());
		gameMap.at(65,17).setGround(new Lake());
		gameMap.at(47,9).setGround(new Lake());


		gameMap.at(0,0).setGround(new Sky());


		world.run();
	}
}
