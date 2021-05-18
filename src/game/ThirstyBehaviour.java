package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

public class ThirstyBehaviour implements Behaviour{
    private HashMap<Integer, Location> lakeTargets = new HashMap<>();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        lakeTargets = new HashMap<>();
        if(actor.isThirsty(map)) {
            Location waterLocation;
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Ground lakes = map.at(x, y).getGround();

                    if (lakes != null) {
                        waterLocation = map.at(x, y);
                        if (lakes.getDisplayChar() == '~') {
                            Lake lake = (Lake) lakes;
                            if (lake.getSips() != 0) {
                                Location here = map.locationOf(actor);
                                int distance = new HungryBehaviour(waterLocation).distance(here, waterLocation);
                                lakeTargets.put(distance, waterLocation);
                            }
                        }
                    }
                }
            }

            if (lakeTargets.size() != 0) {
                int lowestItemDistance = (int) lakeTargets.keySet().toArray()[0];
                for (int keys : lakeTargets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                // if it has not reached the targeted tree then it will keep on following
                if (lowestItemDistance > 0) {
                    waterLocation = lakeTargets.get(lowestItemDistance);
                    return actor.findLake(map, waterLocation);
                }
                // if it has reached the tree it will call the eating action which helps it eat the fruit from the tree
                else {
                    waterLocation = lakeTargets.get(lowestItemDistance);
                    return new EatingAction(waterLocation);
                }
            }
        }
        return null;
    }
}
