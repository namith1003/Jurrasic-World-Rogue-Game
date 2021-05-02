package game;


import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour;
	private static final String[] diet = {"Fruit", "VegeMealKit"};
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

		if (new Random().nextInt(2) == 0){
			addCapability(Gender.MALE);
		} else {
			addCapability(Gender.FEMALE);
		}
	}

	public Stegosaur(String name, String gender){
		super(name, 'S', 50);
		maxHitPoints = 100;
		behaviour = new WanderBehaviour();

		if (gender.equals("male")){
			addCapability(Gender.MALE);
		} else if (gender.equals("female") ){
			addCapability(Gender.FEMALE);
		}

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
		/*age++;
		if (age > 10){

			map.locationOf(this).addItem(new DinoCorpse("Stegosaur", 'X'));
			map.removeActor(this);
			return new DoNothingAction();
		}*/
		hitPoints--;
		Action wander = behaviour.getAction(this, map);
		if (wander != null)
			return wander;
		
		return new DoNothingAction();
	}

}
