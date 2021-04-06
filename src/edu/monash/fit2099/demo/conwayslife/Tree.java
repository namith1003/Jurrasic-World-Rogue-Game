package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Tree extends Ground {
	private int age = 0;

	public Tree() {
		super('+');
		addCapability(Status.ALIVE);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}
}
