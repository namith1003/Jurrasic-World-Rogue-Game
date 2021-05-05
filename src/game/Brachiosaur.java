package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Brachiosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;
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

    public Brachiosaur(String name, boolean isAdult) {
        super(name, 'B', 50);
        maxHitPoints = 160;
        behaviour = new WanderBehaviour();
        diet = new String[]{"Fruit", "VegeMealKit"};
        hungerLevel = 140;
        pregnantTime = 30;
        pregnantCounter = 0;
    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        targets = new HashMap<>();
		/*age++;
		if (age > 10){

			map.locationOf(this).addItem(new DinoCorpse("Stegosaur", 'X'));
			map.removeActor(this);
			return new DoNothingAction();
		}*/

        boolean isHungry = isHungry(map);

        // checks if the stegosaur is unconscious and bout to die
        Action death = new DeathBehaviour().getAction(this, map);
        if (death != null){
            return death;
        }
        // checks if the dinosaur meets the conditions to lay an egg and if yes lays it
        Action layEgg = new LayEggBehaviour().getAction(this,map);
        if (layEgg != null){
            return layEgg;
        }

        if (isHungry) {
            Location targetLocation;
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    ArrayList<Item> items = new ArrayList<>(map.at(x, y).getItems());
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

            if (targets.size() != 0) {
                int lowestItemDistance = (int) targets.keySet().toArray()[0];
                for (int keys : targets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                if (lowestItemDistance > 0) {
                    targetLocation = targets.get(lowestItemDistance);
                    return new HungryBehaviour(targetLocation).getAction(this, map);
                } else {
                    targetLocation = targets.get(lowestItemDistance);
                    return new EatingAction(targetLocation);
                }
            }
        } else {
            return new BreedingBehaviour().getAction(this, map);
        }

        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }
}
