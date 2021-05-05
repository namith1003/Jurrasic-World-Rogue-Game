package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Allosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private ArrayList<Behaviour> behaviour = new ArrayList<>();
    private HashMap<Integer, Stegosaur> stegosaurs = new HashMap<>();
    private ArrayList<Stegosaur> attackedStegosaurs = new ArrayList<>();
    private ArrayList<Integer> timeRemaining = new ArrayList<>();
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

    }

    public Allosaur(String name, String gender){
        super(name, 'a', 20, gender);
        maxHitPoints = 100;
        behaviour.add(new WanderBehaviour());
        diet = new String[]{"MeatMealKit", "Stegosaur Egg", "Stegosaur Corpse", "Brachiosaur Egg", "Allosaur Egg", "Brachiosaur Corpse", "Allosaur Corpse"};
        /*behaviour.add(new FollowBehaviour());*/
        hungerLevel = 50;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // if the allosaur is hungry it will find and attack a stegosaur
        stegosaurs = new HashMap<>();
        targets = new HashMap<>();

        boolean isHungry = isHungry(map);

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


        if (isHungry) {
            Location targetLocation;
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Actor actor = map.at(x, y).getActor();
                    ArrayList<Item> items = new ArrayList<>(map.at(x, y).getItems());

                    if (actor != null && actor.toString().equals("Stegosaur") && !attackedStegosaurs.contains(actor)) {
                        int distance = new FollowBehaviour(actor).distance(map.locationOf(actor), map.locationOf(this));
                        stegosaurs.put(distance, (Stegosaur) actor);
                    }
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
            if (stegosaurs.size() != 0 && targets.size() == 0) {
                int lowestDistance = (int) stegosaurs.keySet().toArray()[0];
                for (int keys : stegosaurs.keySet()) {
                    if (keys < lowestDistance) {
                        lowestDistance = keys;
                    }
                }
                if (new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this, map) != null) {
                    return new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this, map);
                }
            /*else if (new FollowBehaviour(stegosaurs.get(lowestDistance)).location.getActor().toString().equals("Stegosaur")){
                return new AttackAction(stegosaurs.get(lowestDistance));
            }*/
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
            } else if (targets.size() != 0 && stegosaurs.size() == 0) {
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
            } else if (targets.size() != 0) {

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
            /*else if (new FollowBehaviour(stegosaurs.get(lowestDistance)).location.getActor().toString().equals("Stegosaur")){
                return new AttackAction(stegosaurs.get(lowestDistance));
            }*/
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
        // else the allosaur will look for a partner to breed with
        else {
            return new BreedingBehaviour().getAction(this, map);
        }

        Action wander = behaviour.get(0).getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "claws");
    }
}
