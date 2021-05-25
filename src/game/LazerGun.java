package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * class for the lazer gun weapon that the player may own.
 */
public class LazerGun extends WeaponItem {

    /**
     * stores the price of the lazer gun
     */
    private int price;
    /**
     * Constructor to create the lazer gun weapon with a damage of 60 and a price of 500.
     *
     * @param name name of the item
     *
     */
    public LazerGun(String name) {
        super(name, '=', 60, "zaps");
        price = 500;
    }

    /**
     * returns the price of the weapon item
     * @return the price of the weapon item
     */
    @Override
    public int getPrices(){
        return price;
    }

}
