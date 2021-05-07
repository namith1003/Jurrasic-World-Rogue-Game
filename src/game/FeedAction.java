package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * the class for the feeding of a dinosaur by the player, it will heal the amount of food level from the dinosaur
 * corresponding to the food item being fed.
 */
public class FeedAction extends Action {

    /**
     * The Actor that is to be fed by the player
     */
    protected Actor target;
    /**
     * the object used to communicate the feeding action to the actor with.
     */
    Display display = new Display();
    /**
     * the list of all the food items the player can feed
     */
    ArrayList<String> food = new ArrayList<>();

    public FeedAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        while (true) {
            for (Item items: actor.getInventory()){
                switch (items.toString()){
                    case "Eggs" ->{
                        display.println("h");
                    }
                    case "Fruits" ->{
                        display.println("hy");
                    }
                    case "VegeMealKit" ->{
                        display.println("yu");
                    }
                    case "MeatMealKit" ->{
                        display.println("uy");
                    }
                    case "DinoCorpse" ->{
                        display.println("yy");
                    }
                }
                food.add(items.toString());
            }
            display.println("""
                    What do you want to feed?
                    (Enter item number to select food item.)""" +
                    "\nYour Food Items: " +
                    """
                                            
                            ------------------------------------
                            1. 
                            2. 
                            3. Carnivore Meal Kit [500 EP]
                            4. 
                            5. 
                            6. 
                            7. 
                            8. Quit Buying
                            ------------------------------------
                            Insert item number here:
                            """);

            int choice = display.readChar();
        }
    }

    /**
     * Returns a descriptive string about what occurred
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}
