package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class FeedAction extends Action {

    /**
     * The Actor that is to be fed
     */
    protected Actor target;
    Scanner scanner = new Scanner(System.in);
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
                        System.out.print("h");
                    }
                    case "Fruits" ->{
                         System.out.print("hy");
                    }
                    case "VegeMealKit" ->{
                         System.out.print("yu");
                    }
                    case "MeatMealKit" ->{
                         System.out.print("uy");
                    }
                    case "DinoCorpse" ->{
                         System.out.print("yy");
                    }
                }
                food.add(items.toString());
            }
            System.out.print("""
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

            int choice = scanner.nextInt();
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}
