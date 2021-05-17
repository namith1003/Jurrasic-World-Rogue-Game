package game;

import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class Pterodactyl extends Dinosaur{
    /**
     * A list of behaviours that the Allosaur has
     */
    private ArrayList<Behaviour> behaviour = new ArrayList<>();

    /**
     * the target food items on the map that the allosaur can follow.
     */
    private HashMap<Integer, Location> targets = new HashMap<>();

    public Pterodactyl(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
}
