package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A wall class
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * A method for the actor to know that the vending machine counts as impassable terrain
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Seems like a method only used in the mars demo. Unused for dino farmville it seems.
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
