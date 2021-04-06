package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.*;

public interface Behaviour {
	
	/**
	 * A factory for creating actions. Chaining these together can result in an actor performing more complex tasks.
	 *
	 * The idea of a Behaviour is that you can put AI code in one to perform specific tasks, such as moving toward
	 * or running away from something, attacking, etc.  Then, you can have logic within an Actor's {@code playTurn()}
	 * method that determines which Behaviour to perform.  This allows the behaviour's logic to be reused in
	 * other Actors via delegation instead of inheritance.
	 *
	 * Behaviours arer purely optional.  If you like, you can simply create the appropriate Action
	 * within {@code playTurn()} and return it.  This may be the better option of you
	 *
	 * @param actor the Actor performing the action
	 * @param map the GameMap where the action is taking place
	 * @return an Action that actor can perform, or null if actor can't follow this behaviour right now.
	 *
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	Action getAction(Actor actor, GameMap map);
}
