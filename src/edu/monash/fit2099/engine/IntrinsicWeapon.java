package edu.monash.fit2099.engine;

/**
 * Class that represents a weapon for an unarmed Actor (e.g. fists, claws, etc.)
 *
 * @see Weapon
 */
public class IntrinsicWeapon implements Weapon{

	private int damage;
	private String verb;

	public IntrinsicWeapon(int damage, String verb) {
		this.damage = damage;
		this.verb = verb;
	}

    /**
     * The amount of damage the Weapon will inflict
     *
     * @return the damage, in hitpoints
     */
	@Override
	public int damage() {
		return damage;
	}

    /**
     * A verb to use when displaying the results of attacking with this Weapon
     *
     * @return String, e.g. "punches", "zaps"
     */
	@Override
	public String verb() {
		return verb;
	}
}
