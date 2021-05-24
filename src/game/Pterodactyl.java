package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Pterodactyl extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;
    /**
     * the list of target bushes that the stegosaur can find on the map with ripe fruits on them
     */
    private HashMap<Integer, Location> targets = new HashMap<>();
    /**
     * the list of target items on the ground of the map that the stegosaur can find
     */
    private HashMap<Integer, Location> food = new HashMap<>();

    private int fuel;


    /**
     * The constructor to create stegosaurs, they will be creates as babies, represented by 's' and
     * sets a random gender using the Dinosaur classes constructor and a health of 10.
     * @param name the name of the stegosaur.
     */
    public Pterodactyl(String name) {
        super(name, 'p', 80);
        maxHitPoints = 100;
        behaviour = new WanderBehaviour();
        diet = new String[]{"Fish", "Corpses", "MeatMealKit", "Stegosaur Egg", "Stegosaur Corpse", "Brachiosaur Egg", "Allosaur Egg", "Brachiosaur Corpse", "Allosaur Corpse"};
        hungerLevel = 80;
        pregnantTime = 15;
        maxWaterLevel = 100;
        fuel = 30;
    }

    /**
     * The admin constructor for the stegosaur, it will create baby stegosaurs and the same things of the 1 parameter
     * constructor but will also let the user set the gender of the stegosaur without it being random.
     * @param name the name of the stegosaur
     * @param gender the gender of the stegosaur
     */
    public Pterodactyl(String name, String gender){
        super(name, 'p', 80, gender);
        maxHitPoints = 100;
        behaviour = new WanderBehaviour();
        diet = new String[]{"Fish", "Corpses", "MeatMealKit", "Stegosaur Egg", "Stegosaur Corpse", "Brachiosaur Egg", "Allosaur Egg", "Brachiosaur Corpse", "Allosaur Corpse"};
        hungerLevel = 80;
        pregnantTime = 15;
        maxWaterLevel = 100;
        fuel = 30;
    }

    /**
     * Select and return an action to perform on the current turn for this Stegosaur.
     *
     * @param actions    collection of possible Actions for this Stegosaur
     * @param lastAction The Action this Stegosaur took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Stegosaur
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        age++;
        targets = new HashMap<>();
        food = new HashMap<>();

        boolean isHungry = isHungry(map);

        drinkRain();

        // checks if the Pterodactyl is unconscious and bout to die
        Action death = dies(map);
        if (death != null) {
            return death;
        }

        // checks if the Pterodactyl has been there for the right amount of turns for it to turn into an adult
        Action grow = grows(map);
        if (grow != null){
            return grow;
        }

        // checks if the Pterodactyl has been there for the right amount of turns for it to turn into an adult
        Action fly = flies(map);
        if (fly != null){
            return fly;
        }

        // checks if the Pterodactyl is thirsty and it will try to find the closest lake to drink if it is.
        Action drink = drinks(map);
        if (drink != null){
            return drink;
        }

        Location targetLocation;
        // searches for food if the dinosaur is hungry
        if (isHungry) {
            for (int x = 0; x < 80; x++) {
                for (int y = 0; y < 25; y++) {
                    Ground lakes = map.at(x, y).getGround();
                    ArrayList<Item> items = new ArrayList<>(map.at(x, y).getItems());

                    if (lakes != null) {
                        targetLocation = map.at(x, y);
                        if (lakes.getDisplayChar() == '~') {
                            if (lakes.getFish().size() != 0) {
                                Location here = map.locationOf(this);
                                int distance = new SearchBehaviour(targetLocation).distance(here, targetLocation);
                                targets.put(distance, targetLocation);
                            }
                        }
                    }

                    if (items.size() != 0) {
                        targetLocation = map.at(x, y);

                        for (Item item : items) {
                            for (String foodItem: this.getDiet()) {
                                if (item.toString().equals(foodItem)) {
                                    Location here = map.locationOf(this);
                                    int distance = new SearchBehaviour(targetLocation).distance(here, targetLocation);
                                    food.put(distance, targetLocation);
                                }
                            }
                        }
                    }
                }
            }

            if (targets.size() != 0 && food.size() == 0) {
                int lowestItemDistance = (int) targets.keySet().toArray()[0];
                for (int keys : targets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                if (lowestItemDistance > 0) {
                    targetLocation = targets.get(lowestItemDistance);
                    return new SearchBehaviour(targetLocation).getAction(this, map);
                } else {
                    targetLocation = targets.get(lowestItemDistance);
                    return new EatingAction(targetLocation);
                }
            } else if (targets.size() == 0 && food.size() != 0) {
                int lowestItemDistance = (int) food.keySet().toArray()[0];
                for (int keys : food.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                if (lowestItemDistance > 0) {
                    targetLocation = food.get(lowestItemDistance);
                    return new SearchBehaviour(targetLocation).getAction(this, map);
                } else {
                    targetLocation = food.get(lowestItemDistance);
                    return new EatingAction(targetLocation);
                }
            } else if (targets.size() != 0) {

                int lowestItemDistance = (int) targets.keySet().toArray()[0];
                for (int keys : targets.keySet()) {
                    if (keys < lowestItemDistance) {
                        lowestItemDistance = keys;
                    }
                }

                int lowestDistance = (int) food.keySet().toArray()[0];
                for (int keys : food.keySet()) {
                    if (keys < lowestDistance) {
                        lowestDistance = keys;
                    }
                }

                if (lowestDistance < lowestItemDistance) {
                    if (lowestDistance > 0) {
                        targetLocation = food.get(lowestDistance);
                        return new SearchBehaviour(targetLocation).getAction(this, map);
                    } else {
                        targetLocation = food.get(lowestDistance);
                        return new EatingAction(targetLocation);
                    }

                } else {
                    if (lowestItemDistance > 0) {
                        targetLocation = targets.get(lowestItemDistance);
                        return findFood(map, targetLocation);
                    } else {
                        targetLocation = targets.get(lowestItemDistance);
                        return new EatingAction(targetLocation);
                    }
                }
            }
        } // if Pterodactyl is not hungry it will find a partner to breed with, but only if its on a tree.
        else {
            char treeChar = map.locationOf(this).getGround().getDisplayChar();
            if (treeChar == '+' || treeChar == 't' || treeChar == 'T') {
                Action breed = breeding(map);
                if (breed != null) {
                    return breed;
                }
            }
        }

        // checks if the Pterodactyl meets the conditions to lay an egg and if yes lays it, it should also be on a tree
        char treeChar = map.locationOf(this).getGround().getDisplayChar();
        if (treeChar == '+' || treeChar == 't' || treeChar == 'T') {
            Action layEgg = layEgg(map);
            if (layEgg != null) {
                return layEgg;
            }
        }

        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    @Override
    public int getFuel() {
        return fuel;
    }

    @Override
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
