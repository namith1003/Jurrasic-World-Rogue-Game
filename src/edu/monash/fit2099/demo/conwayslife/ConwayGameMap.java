package edu.monash.fit2099.demo.conwayslife;

import java.util.List;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

public class ConwayGameMap extends GameMap {

	public ConwayGameMap(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
	}
	
	@Override
	protected Location makeNewLocation(int x, int y) {
		return new ConwayLocation(this, x, y);
	}
}
