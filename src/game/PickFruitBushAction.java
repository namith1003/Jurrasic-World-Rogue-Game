package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

public class PickFruitBushAction extends Action {
    protected Bushes theBush;

    public PickFruitBushAction(Bushes theBush) {
        this.theBush = theBush;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (new Random().nextInt(10) < 4 && theBush.fruits.size() > 0){
            Fruit fruit = theBush.fruits.remove(theBush.fruits.size() - 1);
            fruit.removeCapability(FruitStatus.ON_BUSH);
            fruit.addCapability(FruitStatus.IN_INVENTORY);
            actor.addItemToInventory(fruit);
            return "You have Successfully found a fruit !!!";
        }
        else{
            return  "You search the tree or bush for fruit, but you can’t find any ripe ones.";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks fruit from " + "Bush";
    }
}

