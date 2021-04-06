package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ConwayLocation extends Location {

	private boolean read = false;
	private NextTurn action = NextTurn.SAME;
	
	public ConwayLocation(GameMap map, int x, int y) {
		super(map, x, y);
	}
	
	public void tick() {
		read = !read;
		if (read) {
			int aliveNeighbours = aliveNeighboursCount();
			boolean aliveHere = getGround().hasCapability(Status.ALIVE);

			// birth
			if (!aliveHere && aliveNeighbours == 3)
				action = NextTurn.GROW;

			// Death by isolation
			else if (aliveHere && aliveNeighbours <= 1)
				action = NextTurn.DIE;

			// Death by overcrowding
			else if (aliveHere && aliveNeighbours >= 4)
				action = NextTurn.DIE;
			
			else
				action = NextTurn.SAME;
		}
		else {
			if(action == NextTurn.GROW)
				setGround(new Tree());
			else if(action == NextTurn.DIE)
				setGround(new Floor());
		}

		super.tick();
	}
	
	
	private int aliveNeighboursCount() {
		return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
				.filter(ground -> ground.hasCapability(Status.ALIVE)).count();
	}
	
	private enum NextTurn {
		GROW, DIE, SAME
	}
}
