package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 */
public class HungryBehaviour implements Behaviour {

    /**
     * A HashMap to store the targets
     */
    private HashMap<Integer, Location> targets = new HashMap<>();
    /**
     * A Location variable to store the coordinates of the target
     */
    private Location targetLocation;

    /**
     * The constructor for HungryBehaviour
     * @param target The target to attack
     */
    public HungryBehaviour(Location target) {
        this.targetLocation = target;
    }


    /**
     * A method to scan the map for a target and determines if it is reachable.
     * If the target is, calls the MoveActorAction to move the dinosaur to the target.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An instance of MoveActorAction to move the actor to the target,
     * or DoNothingAction to do nothing
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

                Location here = map.locationOf(actor);
                Location there = targetLocation;

                int currentDistance = distance(here, there);

                for (Exit exit : here.getExits()) {
                    Location destination = exit.getDestination();
                    if (destination.canActorEnter(actor)) {
                        int newDistance = distance(destination, there);
                        if (newDistance < currentDistance) {
                            return new MoveActorAction(destination, exit.getName());
                        }
                    }
                }

        return new DoNothingAction();
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    public int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
