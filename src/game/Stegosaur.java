package game;


import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A herbivorous dinosaur called Stegosaur that eats only the fruits on the ground and ripe fruits on the bushes.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour;
	private HashMap<Integer, Location> targets = new HashMap<>();
	private HashMap<Integer, Location> food = new HashMap<>();
	// int age=0;

	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name) {
		super(name, 's', 50);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();
		diet = new String[]{"Fruit", "VegeMealKit"};
		hungerLevel = 80;
		pregnantTime = 15;
	}

	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 *
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name, String gender){
		super(name, 's', 50, gender);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();
		diet = new String[]{"Fruit", "VegeMealKit"};
		hungerLevel = 80;
		pregnantTime = 15;
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

		// checks if the stegosaur is unconscious and bout to die
		Action death = dies(map);
		if (death != null) {
			return death;
		}

		// checks if the stegosaur has been there for the right amount of turns for it to turn into an adult
		Action grow = grows(map);
		if (grow != null){
			return grow;
		}

		Location targetLocation;
		// searches for food if the dinosaur is hungry
		if (isHungry) {
			for (int x = 0; x < 80; x++) {
				for (int y = 0; y < 25; y++) {
					Ground bushes = map.at(x, y).getGround();
					ArrayList<Item> items = new ArrayList<>(map.at(x, y).getItems());

					if (bushes != null) {
						targetLocation = map.at(x, y);
						if (bushes.getDisplayChar() == '*') {
							if (bushes.getFruits().size() != 0) {
								Location here = map.locationOf(this);
								int distance = new HungryBehaviour(targetLocation).distance(here, targetLocation);
								targets.put(distance, targetLocation);
							}
						}
					}

					if (items.size() != 0) {
						targetLocation = map.at(x, y);

						for (Item item : items) {
							if (item.toString().equals("Fruit")) {
								Location here = map.locationOf(this);
								int distance = new HungryBehaviour(targetLocation).distance(here, targetLocation);
								food.put(distance, targetLocation);
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
					return new HungryBehaviour(targetLocation).getAction(this, map);
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
					return new HungryBehaviour(targetLocation).getAction(this, map);
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
						return new HungryBehaviour(targetLocation).getAction(this, map);
					} else {
						targetLocation = food.get(lowestDistance);
						return new EatingAction(targetLocation);
					}
            /*else if (new FollowBehaviour(stegosaurs.get(lowestDistance)).location.getActor().toString().equals("Stegosaur")){
                return new AttackAction(stegosaurs.get(lowestDistance));
            }*/
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
		} // if dinosaur is not hungry it will find a partner to breed with.
		else {
			Action breed = breeding(map);
			if (breed != null){
				return breed;
			}
		}

		// checks if the brachiosaur meets the conditions to lay an egg and if yes lays it
		Action layEgg = layEgg(map);
		if (layEgg != null){
			return layEgg;
		}

			Action wander = behaviour.getAction(this, map);
			if (wander != null)
				return wander;

			return new DoNothingAction();
		}

}
