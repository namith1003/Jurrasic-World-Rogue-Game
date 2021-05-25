package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

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
    HashMap<String, Integer> food = new HashMap();

    /**
     * the array list of all the characters the player can enter
     */
    ArrayList<Character> chars = new ArrayList<>();


    /**
     * counter for number of stegosaur eggs the player has in his inventory
     */
    int sEggCounter = 0;
    /**
     * counter for number of Brachiosaur eggs the player has in his inventory
     */
    int bEggCounter = 0;
    /**
     * counter for number of allosaur eggs the player has in his inventory
     */
    int aEggCounter = 0;
    /**
     * counter for number of pterodactyl eggs the player has in his inventory
     */
    int pEggCounter = 0;
    /**
     * counter for number of fruits the player has in his inventory
     */
    int fruitCounter = 0;
    /**
     * counter for number of vegetarian meal kits the player has in his inventory
     */
    int vegeCounter = 0;
    /**
     * counter for number of Carnivorous meal kits the player has in his inventory
     */
    int meatCounter = 0;
    /**
     * counter for number of stegosaur corpse the player has in his inventory
     */
    int sCorpseCounter = 0;
    /**
     * counter for number of Brachiosaur corpse the player has in his inventory
     */
    int bCorpseCounter = 0;
    /**
     * counter for number of allosaur corpse the player has in his inventory
     */
    int aCorpseCounter = 0;
    /**
     * counter for number of pterodactyl corpse the player has in his inventory
     */
    int pCorpseCounter = 0;

    /**
     * the constructor for the FeedAction class which saves the target actor the player will feed.
     * @param target the target actor the player will feed.
     */
    public FeedAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        food.put("Stegosaur Egg", 0);
        food.put("Brachiosaur Egg", 0);
        food.put("Allosaur Egg", 0);
        food.put("Pterodactyl Egg", 0);
        food.put("Fruit", 0);
        food.put("VegeMealKit", 0);
        food.put("MeatMealKit", 0);
        food.put("Stegosaur Corpse", 0);
        food.put("Allosaur Corpse", 0);
        food.put("Brachiosaur Corpse", 0);
        food.put("Pterodactyl Corpse", 0);

        while (true) {
            for (Item items: actor.getInventory()){
                switch (items.toString()){
                    case "Stegosaur Egg" -> {
                        sEggCounter++;
                        food.put("Stegosaur Egg", sEggCounter);
                    }
                    case  "Brachiosaur Egg" -> {
                        bEggCounter++;
                        food.put("Brachiosaur Egg", bEggCounter);
                    }
                    case "Allosaur Egg" -> {
                        aEggCounter++;
                        food.put("Allosaur Egg", aEggCounter);
                    }
                    case "Pterodactyl Egg" -> {
                        pEggCounter++;
                        food.put("Pterodactyl Egg", pEggCounter);
                    }
                    case "Fruit" -> {
                        fruitCounter++;
                        food.put("Fruit", fruitCounter);
                    }
                    case "VegeMealKit" -> {
                        vegeCounter ++;
                        food.put("VegeMealKit", vegeCounter);
                    }
                    case "MeatMealKit" -> {
                        meatCounter++;
                        food.put("MeatMealKit", meatCounter);
                    }
                    case "Stegosaur Corpse" -> {
                        sCorpseCounter++;
                        food.put("Stegosaur Corpse", sCorpseCounter);
                    }
                    case "Allosaur Corpse" -> {
                        aCorpseCounter++;
                        food.put("Allosaur Corpse", aCorpseCounter);
                    }
                    case "Brachiosaur Corpse" -> {
                        bCorpseCounter++;
                        food.put("Brachiosaur Corpse", bCorpseCounter);
                    }
                    case "Pterodactyl Corpse" -> {
                        pCorpseCounter++;
                        food.put("Pterodactyl Corpse", pCorpseCounter);
                    }

                }
            }
            display.println("""
                    What do you want to feed?
                    (Enter item number to select food item.)  
                    Enter 'e' to not feed anything """ +
                    "\nYour Food Items in inventory: ");

            display.println("a) Fruit X " + food.get("Fruit") +
                    "\nb) Vegetarian Meal Kit X " + food.get("VegeMealKit")  +
                    "\nc) Carnivorous Meal Kit X " + food.get("MeatMealKit")  +
                    "\nd) Stegosaur Egg X " + food.get("Stegosaur Egg")  +
                    "\ne) Brachiosaur Egg X " + food.get("Brachiosaur Egg")  +
                    "\nf) Pterodactyl Egg X " + food.get("Pterodactyl Egg")  +
                    "\ng) Allosaur Egg X " + food.get("Allosaur Egg")  +
                    "\nh) Stegosaur Corpse X " + food.get("Stegosaur Corpse")  +
                    "\ni) Brachiosaur Corpse X " + food.get("Brachiosaur Corpse")  +
                    "\nj) Allosaur Corpse X " + food.get("Allosaur Corpse") +
                    "\nk) Pterodactyl Corpse X " + food.get("Pterodactyl Corpse") +
                    "\n1) Do not feed");

            display.println("\nInsert item number here: ");

            chars.add('a');
            chars.add('b');
            chars.add('c');
            chars.add('d');
            chars.add('e');
            chars.add('f');
            chars.add('g');
            chars.add('h');
            chars.add('i');
            chars.add('j');
            chars.add('k');

            char choiceChar = display.readChar();
            int choice = Character.getNumericValue(choiceChar);

            if (choice == 1){
                return "You've decided not to feed anything.";
            }

            String selectedItem = "";

           if (chars.contains(choiceChar)) {
                switch (choiceChar){
                    case 'a' -> {
                        if (food.get("Fruit") == 0){
                            display.println("You do not have any Fruits to feed");
                            continue;
                        }
                        selectedItem = "Fruit";
                    }
                    case 'b' -> {
                        if (food.get("VegeMealKit") == 0){
                            display.println("You do not have any Vegetarian meal kits to feed");
                            continue;
                        }
                        selectedItem = "VegeMealKit";
                    }
                    case 'c' -> {
                        if (food.get("MeatMealKit") == 0){
                            display.println("You do not have any Carnivorous meal kits to feed");
                            continue;
                        }
                        selectedItem = "MeatMealKit";
                    }
                    case 'd' -> {
                        if (food.get("Stegosaur Egg") == 0){
                            display.println("you do not have any Stegosaur Eggs to feed");
                            continue;
                        }
                        selectedItem = "Stegosaur Egg";
                    }
                    case 'e' -> {
                        if (food.get("Brachiosaur Egg") == 0){
                            display.println("you do not have any Brachiosaur Eggs to feed");
                            continue;
                        }
                        selectedItem = "Brachiosaur Egg";
                    }
                    case 'f' -> {
                        if (food.get("Pterodactyl Egg") == 0){
                            display.println("you do not have any Pterodactyl Eggs to feed");
                            continue;
                        }
                        selectedItem = "Pterodactyl Egg";
                    }
                    case 'g' -> {
                        if (food.get("Allosaur Egg") == 0){
                            display.println("you do not have any Allosaur Eggs to feed");
                            continue;
                        }
                        selectedItem = "Allosaur Egg";
                    }
                    case 'h' -> {
                        if (food.get("Stegosaur Corpse") == 0){
                            display.println("you do not have any Stegosaur Corpses to feed");
                            continue;
                        }
                        selectedItem = "Stegosaur Corpse";
                    }
                    case 'i' -> {
                        if (food.get("Brachiosaur Corpse") == 0){
                            display.println("you do not have any Brachiosaur Corpses to feed");
                            continue;
                        }
                        selectedItem = "Brachiosaur Corpse";
                    }
                    case 'j' -> {
                        if (food.get("Allosaur Corpse") == 0){
                            display.println("you do not have any Allosaur Corpses to feed");
                            continue;
                        }
                        selectedItem = "Allosaur Corpse";
                    }
                    case 'k' -> {
                        if (food.get("Pterodactyl Corpse") == 0){
                            display.println("you do not have any Pterodactyl Corpses to feed");
                            continue;
                        }
                        selectedItem = "Pterodactyl Corpse";
                    }
                }
            }
            else {
                display.println("There is no such item! Try again.");
                continue;
            }
            boolean canEat = false;
            for (String consumables: target.getDiet()){
                if (selectedItem.equals(consumables)){
                    canEat = true;
                    break;
                }
            }

            if (canEat) {
                for (Item item : actor.getInventory()) {
                    if (item.toString().equals(selectedItem)) {

                        String itemName = item.toString();
                        int itemHeal = item.getHealValue();
                        if (item.toString().equals("VegeMealKit") || item.toString().equals("MeatMealKit")){
                            target.heal(target.getMaximumHitPoints());
                            actor.removeItemFromInventory(item);
                            return actor + " feeds " + target + " " + itemName + " for " + target.getMaximumHitPoints();
                        } else {
                            target.heal(item.getHealValue());
                            actor.removeItemFromInventory(item);
                            return actor + " feeds " + target + " " + itemName + " for " + itemHeal;
                        }
                    }
                }
            } else {
                return target + " cannot eat " + selectedItem;
            }
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
