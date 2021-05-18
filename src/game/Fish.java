package game;

/**
 * Class for the creation of fish
 */
public class Fish extends FoodItem{
    /**
     * constructor to create the food item
     *
     * @param name the name of the food item
     */
    public Fish(String name) {
        super(name, '!');
        healValue = 5;
    }
}
