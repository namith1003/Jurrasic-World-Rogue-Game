package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Allosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    /**
     * A list of behaviours that the Allosaur has
     */
    private ArrayList<Behaviour> behaviour = new ArrayList<>();
    /**
     * list of all the stegosaurs on the map that havent been attacked by this allosaur
     */
    private HashMap<Integer, Stegosaur> stegosaurs = new HashMap<>();
    /**
     * list of all the stegosaurs that the Allosaur has attacked
     */
    private ArrayList<Stegosaur> attackedStegosaurs = new ArrayList<>();
    /**
     * the time remaining for each stegosaur that the allosaur has attacked corresponding to the attacked stegosaur list
     */
    private ArrayList<Integer> timeRemaining = new ArrayList<>();
    /**
     * the target food items on the map that the allosaur can follow.
     */
    private HashMap<Integer, Location> targets = new HashMap<>();

    /**
     * Constructor.
     *
     * @param name the name of this Allosaur
     *
     */
    public Allosaur(String name) {
        super(name, 'a', 20);
        maxHitPoints = 100;
        behaviour.add(new WanderBehaviour());
        diet = new String[]{"MeatMealKit", "Stegosaur Egg", "Stegosaur Corpse", "Brachiosaur Egg", "Allosaur Egg", "Brachiosaur Corpse", "Allosaur Corpse"};
        hungerLevel = 50;
        pregnantTime = 20;
    }

    public Allosaur(String name, String gender){
        super(name, 'a', 20, gender);
        maxHitPoints = 100;
        behaviour.add(new WanderBehaviour());
        diet = new String[]{"MeatMealKit", "Stegosaur Egg", "Stegosaur Corpse", "Brachiosaur Egg", "Allosaur Egg", "Brachiosaur Corpse", "Allosaur Corpse"};
        hungerLevel = 50;
        pregnantTime = 20;
    }


    /**
     * Select and return an action to perform on the current turn for this Allosaur.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // if the allosaur is hungry it will find and attack a stegosaur
        age++;
        stegosaurs = new HashMap<>();
        targets = new HashMap<>();

        boolean isHungry = isHungry(map);

        // checks if the allosaur is unconscious and bout to die
        Action death = new DeathBehaviour().getAction(this, map);
        if (death != null){
            return death;
        }

        // checks if the allosaur has been there for the right amount of turns for it to turn into an adult
        Action grow = new GrowingBehaviour().getAction(this, map);
        if (grow != null){
            return grow;
        }

        // checks if the allosaur meets the conditions to lay an egg and if yes lays it
        Action layEgg = new LayEggBehaviour().getAction(this,map);
        if (layEgg != null){
            return layEgg;
        }

        // decreases the timer on the stegosaurs that have been attacked by this allosaur.
        if (attackedStegosaurs != null && timeRemaining.size() != 0) {
            for (int i = 0; i < timeRemaining.size(); i++) {
                timeRemaining.set(i, timeRemaining.get(i) - 1);
            }

            if (timeRemaining.get(0) == 0) {
                timeRemaining.remove(0);
                attackedStegosaurs.remove(0);
            }
        }

        // if the allosaur is hungry it will try to find all closest food source including items and stegosaurs
        if (isHungry) {
            Location targetLocation;
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Actor actor = map.at(x, y).getActor();
                    ArrayList<Item> items = new ArrayList<>(map.at(x, y).getItems());

                    // allosaur checks for stegosaur in all locations of the map
                    if (actor != null && actor.toString().equals("Stegosaur") && !attackedStegosaurs.contains(actor)) {
                        int distance = new FollowBehaviour(actor).distance(map.locationOf(actor), map.locationOf(this));
                        stegosaurs.put(distance, (Stegosaur) actor);
                    }
                    // allosaur checks for all of his diets food items in all the locations in the map
                    if (items.size() != 0) {
                        targetLocation = map.at(x, y);

                        for (Item item : items) {
                            if (item.toString().equals("Stegosaur Egg") || item.toString().equals("Brachiosaur Egg") || item.toString().equals("Allosaur Egg") || item.toString().equals("Stegosaur Corpse") || item.toString().equals("Brachiosaur Corpse") || item.toString().equals("Allosaur Corpse")) {

                                Location here = map.locationOf(this);
                                int distance = new HungryBehaviour(targetLocation).distance(here, targetLocation);
                                targets.put(distance, targetLocation);
                            }
                        }
                    }
                }
            }
            // if there are stegosaurs that the allosaur can feed on and no food items in the map, it takes the closest stegosaur to follow
            if (stegosaurs.size() != 0 && targets.size() == 0) {
                int lowestDistance = (int) stegosaurs.keySet().toArray()[0];
                for (int keys : stegosaurs.keySet()) {
                    if (keys < lowestDistance) {
                        lowestDistance = keys;
                    }
                }
                // allosaur will follow the closest stegosaur
                if (new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this, map) != null) {
                    return new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this, map);
                }
                // allosaur has reached the stegosaur and will attack it for hp
                else {
                    for (Exit exits : map.locationOf(this).getExits()) {
                        if (exits.getDestination() == map.locationOf(stegosaurs.get(lowestDistance))) {
                            attackedStegosaurs.add(stegosaurs.get(lowestDistance));
                            timeRemaining.add(20);
                            heal(20);
                            return new AttackAction(stegosaurs.remove(lowestDistance));
                        }
                    }
                    return new DoNothingAction();
                }
            }
            // if there are items on the map as a food source but no stegosaurs in the map that it hasn't attacked it will
            // find the closest food item as the food source
            else if (targets.size() != 0 && stegosaurs.size() == 0) {
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
            // if there are food items and stegosaurs on the map it will follow the closest food source available to it.
            else if (targets.size() != 0) {

                int lowestItemDistance = (int) targets.keySet().toArray()[0];
                for (int keys : targets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                int lowestDistance = (int) stegosaurs.keySet().toArray()[0];
                for (int keys : stegosaurs.keySet()) {
                    if (keys < lowestDistance) {
                        lowestDistance = keys;
                    }
                }

                if (lowestDistance < lowestItemDistance) {
                    if (new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this, map) != null) {
                        return new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this, map);
                    }

                    else {
                        for (Exit exits : map.locationOf(this).getExits()) {
                            if (exits.getDestination() == map.locationOf(stegosaurs.get(lowestDistance))) {
                                attackedStegosaurs.add(stegosaurs.get(lowestDistance));
                                timeRemaining.add(20);
                                heal(20);
                                return new AttackAction(stegosaurs.remove(lowestDistance));
                            }
                        }
                        return new DoNothingAction();

                    }
                } else {
                    if (lowestItemDistance > 0) {
                        targetLocation = targets.get(lowestItemDistance);
                        return new HungryBehaviour(targetLocation).getAction(this, map);
                    } else {
                        targetLocation = targets.get(lowestItemDistance);
                        return new EatingAction(targetLocation);
                    }
                }
            }
        }
        // if the allosaur is not hungry it will look for a partner to breed with
        else {
            return new BreedingBehaviour().getAction(this, map);
        }

        // if nothing else to do he will just wander around or do nothing
        Action wander = behaviour.get(0).getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    /**
     * the intrinsic weapon of the allosaur is his claws.
     * @return His intrinsic weapon the claws and the stats for his claws
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "claws");
    }
}
