package game;

/**
 * An abstract class that extends from FoodItem.
 * Gets extended by MeatMealKit and VegeMealKit.
 */
public abstract class MealKit extends FoodItem{
    /**
     * The constructor for the MealKit.
     * @param name The name of the MealKit
     * @param displayChar The display character for the MealKit.
     */
    public MealKit(String name, char displayChar) {
        super(name, displayChar);
    }
}
