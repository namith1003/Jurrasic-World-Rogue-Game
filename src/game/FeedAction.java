package game;

import edu.monash.fit2099.engine.*;

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

    int sEggCounter = 0;
    int bEggCounter = 0;
    int aEggCounter = 0;
    int fruitCounter = 0;
    int vegeCounter = 0;
    int meatCounter = 0;
    int sCorpseCounter = 0;
    int bCorpseCounter = 0;
    int aCorpseCounter = 0;

    public FeedAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        food.put("Stegosaur Egg", 0);
        food.put("Brachiosaur Egg", 0);
        food.put("Allosaur Egg", 0);
        food.put("Fruit", 0);
        food.put("VegeMealKit", 0);
        food.put("MeatMealKit", 0);
        food.put("Stegosaur Corpse", 0);
        food.put("Allosaur Corpse", 0);
        food.put("Brachiosaur Corpse", 0);

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
                }
            }
            display.println("""
                    What do you want to feed?
                    (Enter item number to select food item.)""" +
                    "\nYour Food Items in inventory: ");

            display.println("1) Fruit X " + food.get("Fruit") +
                    "\n2) Vegetarian Meal Kit X " + food.get("VegeMealKit")  +
                    "\n3) Carnivorous Meal Kit X " + food.get("MeatMealKit")  +
                    "\n4) Stegosaur Egg X " + food.get("Stegosaur Egg")  +
                    "\n5) Brachiosaur Egg X " + food.get("Brachiosaur Egg")  +
                    "\n6) Allosaur Egg X " + food.get("Allosaur Egg")  +
                    "\n7) Stegosaur Corpse X " + food.get("Stegosaur Corpse")  +
                    "\n8) Brachiosaur Corpse X " + food.get("Brachiosaur Corpse")  +
                    "\n9) Allosaur Corpse X " + food.get("Allosaur Corpse") +
                    "\n10) Do not feed");

            display.println("\nInsert item number here: ");
            int choice = Character.getNumericValue(display.readChar());

            String selectedItem = "";
            if (choice == 10) {
                return "You've decided not to feed anything.";
            }
            else if (choice>=1 && choice<=9) {
                switch (choice){
                    case 1 -> {
                        if (food.get("Fruit") == 0){
                            display.println("You do not have any Fruits to feed");
                            continue;
                        }
                        selectedItem = "Fruit";
                    }
                    case 2 -> {
                        if (food.get("VegeMealKit") == 0){
                            display.println("You do not have any Vegetarian meal kits to feed");
                            continue;
                        }
                        selectedItem = "VegeMealKit";
                    }
                    case 3 -> {
                        if (food.get("MeatMealKit") == 0){
                            display.println("You do not have any Carnivorous meal kits to feed");
                            continue;
                        }
                        selectedItem = "MeatMealKit";
                    }
                    case 4 -> {
                        if (food.get("Stegosaur Egg") == 0){
                            display.println("you do not have any Stegosaur Eggs to feed");
                            continue;
                        }
                        selectedItem = "Stegosaur Egg";
                    }
                    case 5 -> {
                        if (food.get("Brachiosaur Egg") == 0){
                            display.println("you do not have any Brachiosaur Eggs to feed");
                            continue;
                        }
                        selectedItem = "Brachiosaur Egg";
                    }
                    case 6 -> {
                        if (food.get("Allosaur Egg") == 0){
                            display.println("you do not have any Allosaur Eggs to feed");
                            continue;
                        }
                        selectedItem = "Allosaur Egg";
                    }
                    case 7 -> {
                        if (food.get("Stegosaur Corpse") == 0){
                            display.println("you do not have any Stegosaur Corpses to feed");
                            continue;
                        }
                        selectedItem = "Stegosaur Corpse";
                    }
                    case 8 -> {
                        if (food.get("Brachiosaur Corpse") == 0){
                            display.println("you do not have any Brachiosaur Corpses to feed");
                            continue;
                        }
                        selectedItem = "Brachiosaur Corpse";
                    }
                    case 9 -> {
                        if (food.get("Allosaur Corpse") == 0){
                            display.println("you do not have any Allosaur Corpses to feed");
                            continue;
                        }
                        selectedItem = "Allosaur Corpse";
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
