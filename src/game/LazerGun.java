package game;

import edu.monash.fit2099.engine.WeaponItem;

public class LazerGun extends WeaponItem {

    private int price;
    /**
     * Constructor.
     *
     * @param name name of the item
     *
     */
    public LazerGun(String name) {
        super(name, '=', 60, "zaps");
        price = 500;
    }

    @Override
    public int getPrices(){
        return price;
    }

}
