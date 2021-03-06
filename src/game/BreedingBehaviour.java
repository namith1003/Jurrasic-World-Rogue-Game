package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

/**
 * This is the behaviour of the dinosaurs where they actively check if there are suitable targets for the breeding process too occur
 * and whether this actor can breed with another, this checks if there are target that are viable to breed with and whether
 * the target is already pregnant or is a baby
 */
public class BreedingBehaviour implements Behaviour {

    /**
     * Stores the list of all dinosaurs that the acting dinosaur is capable to breed with
     */
    private HashMap<Integer, Actor> dinosaurs = new HashMap<>();

    /**
     * The behaviour of the actor to search and follow a dinosaur that is viable to breed with and checks if all the statuses
     * for the actor and the target are viable for the breeding( they are not babies and are not pregnant or hungry)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Breeding Action that the actor can perform with the target, does nothing if not possible.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // checks if this dinosaur has the requirements for it to breed
        if (!actor.hasCapability(BreedingStatus.IS_PREGNANT) && actor.hasCapability(BreedingStatus.CAN_BREED) && actor.getIsAdult()) {
            // dinosaur will search through the entire map to find all the dinosaurs that it can mate with.
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Actor target = map.at(x, y).getActor();
                    // checks if the there is an actor at every position in the map and checks if the target actor has all the conditions needed to breed.
                    if (target != null && target.getIsAdult() &&(target.toString().equals(actor.toString())) && !target.hasCapability(BreedingStatus.IS_PREGNANT) && target.hasCapability(BreedingStatus.CAN_BREED)) {
                        if (!actor.getGender().equals(target.getGender())) {
                            int distance = new FollowBehaviour(actor).distance(map.locationOf(actor), map.locationOf(target));
                            dinosaurs.put(distance, target);
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
                            return new BreedingAction(dinosaurs.remove(lowestDistance));
                        }
                    }
                    return new DoNothingAction();
                }
            }
        }
        return null;
    }
}
