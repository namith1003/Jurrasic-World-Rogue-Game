package game;

import edu.monash.fit2099.engine.WeaponItem;

public class LazerGun extends WeaponItem {
    /**
     * Constructor.
     *
     * @param name name of the item
     *
     */
    public LazerGun(String name) {
        super(name, '=', 60, "zaps");
    }

}
