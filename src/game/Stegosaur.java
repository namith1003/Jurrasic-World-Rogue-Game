package game;


import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A herbivorous dinosaur.
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

	public Stegosaur(String name, String gender){
		super(name, 's', 50, gender);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();
		diet = new String[]{"Fruit", "VegeMealKit"};
		hungerLevel = 80;
		pregnantTime = 15;
	}

	public Stegosaur(String name, boolean isAdult) {
		super(name, 'S', 50);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();
		diet = new String[]{"Fruit", "VegeMealKit"};
		hungerLevel = 80;
		pregnantTime = 15;
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
		food = new HashMap<>();
		/*age++;
		if (age > 10){

			map.locationOf(this).addItem(new DinoCorpse("Stegosaur", 'X'));
			map.removeActor(this);
			return new DoNothingAction();
		}*/
		boolean isHungry = isHungry(map);

		// checks if the stegosaur is unconscious and bout to die
		Action death = new DeathBehaviour().getAction(this, map);
		if (death != null) {
			return death;
		}

		// checks if the dinosaur meets the conditions to lay an egg and if yes lays it
		Action layEgg = new LayEggBehaviour().getAction(this, map);
		if (layEgg != null) {
			return layEgg;
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
						return new HungryBehaviour(targetLocation).getAction(this, map);
					} else {
						targetLocation = targets.get(lowestItemDistance);
						return new EatingAction(targetLocation);
					}
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
