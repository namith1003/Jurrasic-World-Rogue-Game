package game;

public abstract class FoodItem extends PortableItem{

    protected int healValue;

    public FoodItem(String name, char displayChar) {
        super(name, displayChar);
    }
}
