package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

public class FlyingBehaviour implements Behaviour{

    private HashMap<Integer, Location> targets = new HashMap<>();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        actor.setFuel(actor.getFuel() - 1);
        char treeChar = map.locationOf(actor).getGround().getDisplayChar();
        if (treeChar == '+' || treeChar == 't' || treeChar == 'T') {
            actor.removeCapability(FlyingStatus.WALKING);
            actor.removeCapability(FlyingStatus.CAN_FLY);
            actor.addCapability(FlyingStatus.ON_TREE);
            actor.setFuel(30);
        }
        if (actor.getFuel() > 0 && (actor.isThirsty(map) || actor.isHungry(map))) {
            actor.removeCapability(FlyingStatus.ON_TREE);
            actor.removeCapability(FlyingStatus.WALKING);
            actor.addCapability(FlyingStatus.CAN_FLY);

        } else {
            actor.removeCapability(FlyingStatus.ON_TREE);
            actor.removeCapability(FlyingStatus.CAN_FLY);
            actor.addCapability(FlyingStatus.WALKING);

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
