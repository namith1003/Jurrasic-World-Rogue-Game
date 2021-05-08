package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

/**
 * A class to pick a fruit from the tree directly
 */
public class PickFruitTreeAction extends Action {
    /**
     * A variable to store the target tree
     */
    protected Tree theTree;

    /**
     * The constructor for PickFruitTreeAction
     * @param theTree the target tree to pick a fruit from
     */
    public PickFruitTreeAction(Tree theTree) {
        this.theTree = theTree;
    }

    /**
     * The method to pick a fruit from the tree,
     * given if there are fruits in the tree and a simple RNG generator hits 4/10.
     * The fruit is removed from the tree and added to the player's inventory.
     * The player's points is also increased.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string to indicate if a fruit is successfully picked or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // have a 60% chance to pick a ripe fruit from the tree
        if (new Random().nextInt(10) < 4 && theTree.fruits.size() != 0){
            Fruit fruit = theTree.fruits.remove(theTree.fruits.size() - 1);
            fruit.removeCapability(FruitStatus.ON_TREE);
            fruit.addCapability(FruitStatus.IN_INVENTORY);
            Player.points.setPoints(10);
            actor.addItemToInventory(theTree.fruits.remove(theTree.fruits.size() - 1));
            return "You have Successfully found a fruit !!!";
        }
        else{
            return  "You search the tree or bush for fruit, but you can’t find any ripe ones.";
        }
    }

    /**
     * A function to return a string for the player to do something.
     * But does nothing here since this class isn't supposed to be accessible to players.
     * @param actor The actor performing the action.
     * @return null.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks fruit from " + "Tree";
    }
}
