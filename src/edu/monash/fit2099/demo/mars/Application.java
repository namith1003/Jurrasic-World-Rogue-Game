package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.*;
import game.FollowBehaviour;

import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String[] args) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(),
                new Crater());
        GameMap gameMap;

        List<String> map = Arrays.asList(
                ".............",
                "......######.",
                "......+....+.",
                "......######.",
                ".............");
        gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        List<String> marsMap = Arrays.asList(
                "ooooooooooooo",
                "oooooooo...oo",
                "oooooo....ooo",
                "oooooooo..ooo",
                "ooo..oooooooo",
                "ooooooooooooo");
        GameMap mars = new GameMap(groundFactory, marsMap);
        world.addGameMap(mars);

        MartianItem rocket = new MartianItem("Rocket", '^', false);
        rocket.addAction(new MoveActorAction(mars.at(7, 2), "to Mars!"));
        gameMap.at(1, 1).addItem(rocket);

        Item spaceSuit = new MartianItem("space suit", '[', true);
        spaceSuit.addCapability(DemoCapabilities.SPACETRAVELLER);
        gameMap.at(0, 1).addItem(spaceSuit);

        Item stick = new Stick();
        gameMap.at(8, 2).addItem(stick);

        Actor player = new Player("The Player", '@', 100);
        world.addPlayer(player, gameMap.at(2, 3));

        Bug bug = new Bug();
        bug.addItemToInventory(new MartianItem("rock", '*', true));
        bug.actionFactories.add(new SpitBehaviour(player));
        bug.actionFactories.add(new FollowBehaviour(player));
        gameMap.at(0, 2).addActor(bug);

        world.run();
    }
}
