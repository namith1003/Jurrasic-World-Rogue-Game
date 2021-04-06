package edu.monash.fit2099.engine;

/**
 * An interface for representing a collection of any kind of enum.
 * A practical alternative to type introspection and other problems.
 *
 */
public interface Capable {
	boolean hasCapability(Enum<?> capability);
	void addCapability(Enum<?> capability);
	void removeCapability(Enum<?> capability);
}