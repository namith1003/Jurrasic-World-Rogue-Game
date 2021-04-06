package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;


public class Crater extends Ground {

	public Crater() {
		super('o');
	}
	
	@Override
	public boolean canActorEnter(Actor a) {
		return a.hasCapability(DemoCapabilities.SPACETRAVELLER);
	}
}