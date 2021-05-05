package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

public class BreedingBehaviour implements Behaviour {

    /**
     * Stores the list of all dinosaurs that the acting dinosaur is capable to breed with
     */
    private HashMap<Integer, Dinosaur> dinosaurs = new HashMap<>();

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (!actor.hasCapability(BreedingStatus.IS_PREGNANT) && actor.hasCapability(BreedingStatus.CAN_BREED)) {
            // dinosaur will search through the entire map to find all the dinosaurs that it can mate with.
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Actor target = map.at(x, y).getActor();
                    // checks if the there is an actor at every position in the map and checks if the actor has all the conditions needed to breed.

                    if (target != null && (target.toString().equals(actor.toString())) && !target.hasCapability(BreedingStatus.IS_PREGNANT) && target.hasCapability(BreedingStatus.CAN_BREED)) {
                        Dinosaur dinosaurTarget = (Dinosaur) target;
                        Dinosaur dinosaur = (Dinosaur) actor;
                        if (!dinosaur.getGender().equals(dinosaurTarget.getGender())) {
                            int distance = new FollowBehaviour(actor).distance(map.locationOf(dinosaur), map.locationOf(dinosaurTarget));
                            dinosaurs.put(distance, dinosaurTarget);
                        }

                    }
                }
            }

            // if there are dinosaurs that this dinosaur can breed with it will try to find the closest one from all the viable ones.
            if (dinosaurs.size() != 0) {
                int lowestDistance = (int) dinosaurs.keySet().toArray()[0];
                for (int keys : dinosaurs.keySet()) {
                    if (keys < lowestDistance) {
                        lowestDistance = keys;
                    }
                }

                // if the dinosaur can still move toward the targeted closest dinosaur.
                if (new FollowBehaviour(dinosaurs.get(lowestDistance)).getAction(actor, map) != null) {
                    return new FollowBehaviour(dinosaurs.get(lowestDistance)).getAction(actor, map);
                } else {
                    // if the dinosaur has come next to the targeted closest dinosaur.
                    for (Exit exits : map.locationOf(actor).getExits()) {
                        if (exits.getDestination() == map.locationOf(dinosaurs.get(lowestDistance))) {
                            System.out.println("breeding bout to start");
                            return new BreedingAction(dinosaurs.remove(lowestDistance));
                        }
                    }
                    return new DoNothingAction();
                }
            }
        }
        return new WanderBehaviour().getAction(actor,map);
    }
}
