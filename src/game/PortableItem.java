package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	/**
	 * The constructor for PortableItem
	 * @param name The name of the item
	 * @param displayChar The display character of the item
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
