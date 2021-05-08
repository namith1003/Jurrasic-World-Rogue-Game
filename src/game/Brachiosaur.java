package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

/**
 * Class for the Herbivore dinosaur Brachiosaur which only eats fruits fed to it and ripe fruits on trees
 */
public class Brachiosaur extends Dinosaur{

    /**
     * All the behaviours that the brachiosaur has
     */
    private Behaviour behaviour;
    /**
     * all the fruits on the map that the brachiosaur can target
     */
    private HashMap<Integer, Location> targets = new HashMap<>();

    /**
     * Constructor.
     *
     * @param name the name of this Brachiosaur
     *
     */
    public Brachiosaur(String name) {
        super(name, 'b', 10);
        maxHitPoints = 160;
        behaviour = new WanderBehaviour();
        diet = new String[]{"Fruit", "VegeMealKit"};
        hungerLevel = 140;
        pregnantTime = 30;
        pregnantCounter = 0;
    }

    public Brachiosaur(String name, String gender){
        super(name, 'b', 10,gender);
        maxHitPoints = 160;
        behaviour = new WanderBehaviour();
        diet = new String[]{"Fruit", "VegeMealKit"};
        hungerLevel = 140;
        pregnantTime = 30;
        pregnantCounter = 0;
    }

    /**
     * Select and return an action to perform on the current turn for this Brachiosaur.
     *
     * @param actions    collection of possible Actions for this Brachiosaur
     * @param lastAction The Action this Brachiosaur took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Brachiosaur
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        age++;
        targets = new HashMap<>();

        boolean isHungry = isHungry(map);

        // checks if the brachiosaur is unconscious and bout to die
        Action death = new DeathBehaviour().getAction(this, map);
        if (death != null){
            return death;
        }

        // checks if the brachiosaur has been there for the right amount of turns for it to turn into an adult
        Action grow = new GrowingBehaviour().getAction(this, map);
        if (grow != null){
            return grow;
        }

        // checks if the brachiosaur meets the conditions to lay an egg and if yes lays it
        Action layEgg = new LayEggBehaviour().getAction(this,map);
        if (layEgg != null){
            return layEgg;
        }

        // if the brachiosaur is hungry it will try to find the closest tree that has ripe fruits on it.
        if (isHungry) {
            Location targetLocation;
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Ground trees = map.at(x, y).getGround();

                    if (trees != null) {
                        targetLocation = map.at(x, y);
                        if (trees.getDisplayChar() == '+' || trees.getDisplayChar() == 't' || trees.getDisplayChar() == 'T') {
                            Tree tree = (Tree) trees;
                            if (tree.getFruits().size() != 0) {
                                Location here = map.locationOf(this);
                                int distance = new HungryBehaviour(targetLocation).distance(here, targetLocation);
                                targets.put(distance, targetLocation);
                            }
                        }
                    }
                }
            }

            // if there are trees with ripe fruits on it on the map then it will try to find the closest one and go towards it
            if (targets.size() != 0) {
                int lowestItemDistance = (int) targets.keySet().toArray()[0];
                for (int keys : targets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                // if it has not reached the targeted tree then it will keep on following
                if (lowestItemDistance > 0) {
                    targetLocation = targets.get(lowestItemDistance);
                    return new HungryBehaviour(targetLocation).getAction(this, map);
                }
                // if it has reached the tree it will call the eating action which helps it eat the fruit from the tree
                else {
                    targetLocation = targets.get(lowestItemDistance);
                    return new EatingAction(targetLocation);
                }
            }
        }
        // if dinosaur is not hungry it will find a partner to breed with.
        else {
            return new BreedingBehaviour().getAction(this, map);
        }

        // if none of the above occurs it will wander around or do nothing
        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }
}
