package game;


import edu.monash.fit2099.engine.*;

import java.util.HashMap;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour;
	private HashMap<Integer, Location> targets = new HashMap<>();

	int age=0;

	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name) {
		super(name, 'S', 50);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();
		diet = new String[]{"Fruit", "VegeMealKit"};
		hungerLevel = 80;
	}

	public Stegosaur(String name, String gender){
		super(name, 'S', 50, gender);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();
		diet = new String[]{"Fruit", "VegeMealKit"};
		hungerLevel = 80;
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

		// checks if the dinosaur is unconscious and bout to die
		Action death = new DeathBehaviour().getAction(this, map);
		if (death != null){
			return death;
		}

		// searches for food if the dinosaur is hungry
		if (isHungry) {
			Location targetLocation;
			for (int x = 0; x < 80; x++) {
				for (int y = 0; y < 25; y++) {
					Ground bushes = map.at(x, y).getGround();

					if (bushes != null) {
						targetLocation = map.at(x, y);
						if (bushes.getDisplayChar() == '*') {
							Bushes bush = (Bushes) bushes;
							if (bush.getFruits().size() != 0) {
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
