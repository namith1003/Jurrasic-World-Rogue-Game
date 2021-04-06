package edu.monash.fit2099.engine;

import java.util.HashSet;
import java.util.Set;

/**
 * A collection of Capability objects.
 * 
 * 
 * The original purpose of Capabilities was to allow game clients to check whether Actors could do particular Actions,
 * whether Items provided (or required) certain abilities, whether terrain was passable under particular
 * circumstances, etc.  Consider Capabilities an all-purpose mechanism for enabling game capabilities, statuses, etc.
 * 
 * Don't be too literal about the name. You can keep all sorts of things in here.
 * PURPLE, FLAT, HOUSE_RAVENCLAW, TEAM_HERBIVORE, etc.  
 * 
 * Any Enum type can be used to represent a Capability, so these classes can and should be defined in the game client.
 */
public class Capabilities implements Capable {
	private Set<Object> capabilitySet = new HashSet<Object>();

	public boolean hasCapability(Enum<?> capability) {
		return capabilitySet.contains(capability);
	}

	public void addCapability(Enum<?> capability) {
		capabilitySet.add(capability);
	}

	public void removeCapability(Enum<?> capability) {
		capabilitySet.remove(capability);
	}
}
