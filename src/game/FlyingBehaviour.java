package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

/**
 * The behaviour that adds the capability for a dinosaur to fly once its on the tree and to walk once its
 * fuel runs out and then search and return back to the closest tree to refill its fuel.
 */
public class FlyingBehaviour implements Behaviour{

    /**
     * the target trees on the map that the dinosaur can follow, when its out of fuel.
     */
    private HashMap<Integer, Location> targets = new HashMap<>();

    /**
     * he behaviour class that checks if the conditions for the actor to fly has been met, here its whether the actor
     *  does have enough fuel to fly and will refill its fuel once it comes back to a tree, if it has enough fuel
     *  it will just fly around trying to find food or water for itself.
     * @param actor the Actor that's going to perform the death action
     * @param map the GameMap containing the Actor
     * @return the death action for the actor and if nothing is possible returns null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        actor.setFuel(actor.getFuel() - 1);
        char treeChar = map.locationOf(actor).getGround().getDisplayChar();
        // it will try to check if its on a tree and if yes will get back all its fuel
        if (treeChar == '+' || treeChar == 't' || treeChar == 'T') {
            actor.removeCapability(FlyingStatus.WALKING);
            actor.removeCapability(FlyingStatus.CAN_FLY);
            actor.addCapability(FlyingStatus.ON_TREE);
            actor.setFuel(30);
        }
        // if the actor has enough fuel and is thirsty it will start to fly, if its not hungry or thirsty it will
        // return to a tree
        if (actor.getFuel() > 0 && (actor.isThirsty(map) || actor.isHungry(map))) {
            actor.removeCapability(FlyingStatus.ON_TREE);
            actor.removeCapability(FlyingStatus.WALKING);
            actor.addCapability(FlyingStatus.CAN_FLY);

        }
        else if (actor.getFuel() <= 0) {
            actor.removeCapability(FlyingStatus.ON_TREE);
            actor.removeCapability(FlyingStatus.CAN_FLY);
            actor.addCapability(FlyingStatus.WALKING);
        }
        else{
            Location targetLocation;
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Ground trees = map.at(x, y).getGround();

                    if (trees != null) {
                        targetLocation = map.at(x, y);
                        if (trees.getDisplayChar() == '+' || trees.getDisplayChar() == 't' || trees.getDisplayChar() == 'T') {
                            Location here = map.locationOf(actor);
                            int distance = new SearchBehaviour(targetLocation).distance(here, targetLocation);
                            targets.put(distance, targetLocation);
                        }
                    }
                }
            }

            // if there are trees they go towards it to get back fuel
            if (targets.size() != 0) {
                int lowestItemDistance = (int) targets.keySet().toArray()[0];
                for (int keys : targets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                targetLocation = targets.get(lowestItemDistance);
                return new SearchBehaviour(targetLocation).getAction(actor, map);
            }
        }
        return null;
    }
}
