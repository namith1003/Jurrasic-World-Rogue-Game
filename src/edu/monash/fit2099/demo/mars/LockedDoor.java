package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


public class LockedDoor extends Ground {

	public LockedDoor() {
		super('+');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new WindowSmashAction(direction, location));
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
