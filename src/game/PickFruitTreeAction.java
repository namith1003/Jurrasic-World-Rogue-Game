package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

public class PickFruitTreeAction extends Action {
    protected Tree theTree;

    public PickFruitTreeAction(Tree theTree) {
        this.theTree = theTree;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (new Random().nextInt(10) < 4 && theTree.fruits.size() != 0){
            Fruit fruit = theTree.fruits.remove(theTree.fruits.size() - 1);
            fruit.removeCapability(FruitStatus.ON_BUSH);
            fruit.addCapability(FruitStatus.IN_INVENTORY);
            Player.points.setPoints(10);
            actor.addItemToInventory(theTree.fruits.remove(theTree.fruits.size() - 1));
            return "You have Successfully found a fruit !!!";
        }
        else{
            return  "You search the tree or bush for fruit, but you can’t find any ripe ones.";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks fruit from " + "Tree";
    }
}
