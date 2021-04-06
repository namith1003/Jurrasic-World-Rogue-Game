package edu.monash.fit2099.engine;

import java.util.List;

import edu.monash.fit2099.interfaces.ItemInterface;

/**
 * Abstract base class representing a physical object in the game world.
 *
 */
public abstract class Item implements ItemInterface, Printable, Capable {

	protected boolean portable;
	protected String name;
	protected char displayChar;
	protected Actions allowableActions;
	protected Capabilities capabilities = new Capabilities();

	/***
	 * Constructor.
	 * 
	 * @param name the name of this Item
	 * @param displayChar the character to use to represent this item if it is on the ground
	 * @param portable true if and only if the Item can be picked up
	 */
	public Item(String name, char displayChar, boolean portable) {
		this.name = name;
		this.displayChar = displayChar;
		this.portable = portable;
		allowableActions = new Actions();
	}

    /**
     * Inform a carried Item of the passage of time.
     * 
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
	public void tick(Location currentLocation, Actor actor) {
	}
	
    /**
     * Inform an Item on the ground of the passage of time. 
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
	public void tick(Location currentLocation) {
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Create and return an action to pick this Item up.
	 * If this Item is not portable, returns null. 
	 * 
	 * @return a new PickUpItemAction if this Item is portable, null otherwise.
	 */
	public PickUpItemAction getPickUpAction() {
		if(portable)
			return new PickUpItemAction(this);
		
		return null;
	}

	/**
	 * Create and return an action to drop this Item.
	 * If this Item is not portable, returns null.
	 * @return a new DropItemAction if this Item is portable, null otherwise.
	 */
	public DropItemAction getDropAction() {
		if(portable)
			return new DropItemAction(this);
		
		return null;
	}
	
	/**
	 * Getter.
	 * 
	 * Returns an unmodifiable copy of the actions list so that calling methods won't
	 * be able to change what this Item can do without the Item checking.
	 * @return an unmodifiable list of Actions
	 */
	public List<Action> getAllowableActions() {
		return allowableActions.getUnmodifiableActionList();
	}


	/**
	 * Casts this Item to a Weapon if possible.
	 * 
	 * @return a reference to the current Item as type Weapon, or null if this Item isn't a Weapon
	 */
	public Weapon asWeapon() {
		return this instanceof Weapon ? (Weapon) this : null;
	}

	/**
	 * Does this Item have the given Capability?
	 * 
	 * @return true if and only if is Item has the given Capability
	 */
	public boolean hasCapability(Enum<?> capability) {
		return capabilities.hasCapability(capability);
	}

	/**
	 * Add a Capability to this Item.
	 * 
	 * @param capability the Capability to add
	 */
	public void addCapability(Enum<?> capability) {
		capabilities.addCapability(capability);
	}

	/**
	 * Remove a Capability from this Item.
	 * 
	 * @param capability the Capability to remove
	 */
	public void removeCapability(Enum<?> capability) {
		capabilities.removeCapability(capability);
	}
}
