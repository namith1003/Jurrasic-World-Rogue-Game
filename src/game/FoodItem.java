package game;

/**
 * the general class for all the food items that will inherit it, all food items are portable and will have a value
 * that they can heal actors for.
 */
public abstract class FoodItem extends PortableItem{

    /**
     * amount of food level the actor that eats the fruit heals
     */
    protected int healValue;

    /**
     * constructor to create the food item
     * @param name the name of the food item
     * @param displayChar the character that displays the food item on the map
     */
    public FoodItem(String name, char displayChar) {
        super(name, displayChar);
    }

    /**
     * get the value that the food item heals for
     * @return return the amount of food level that will be healed when this item is consumed.
     */
    @Override
    public int getHealValue() {
        return healValue;
    }

}
