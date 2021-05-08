package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

import java.util.Scanner;

public class VendingAction extends Action{

    /**
     * The vending machine that is accessed
     */
    protected VendingMachine vendingMachine;
    /**
     * A scanner object to read in the player's input
     */
    Scanner scanner = new Scanner(System.in);
    /**
     * A static and final price list to indicate the prices of the items.
     * The items are as such:
     * 1. Fruit [30 EP]
     * 2. Vegetarian Meal Kit [100 EP]
     * 3. Carnivore Meal Kit [500 EP]
     * 4. Stegosaur Egg [200 EP]
     * 5. Brachiosaur Egg [500 EP]
     * 6. Allosaur Egg [1000 EP]
     * 7. Laser Gun [500 EP]
     */


    /**
     * Constructor for vending action
     * @param vendingMachine the vending machine object to interact with
     */
    public VendingAction(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    /**
     * The main method for the player to interact with the vending machine.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string to indicate the player has interacted with the vending machine
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Display choices = new Display();
        while (true) {
            choices.println("""
                    ------------------------------------
                    It's a vending machine!
                    What do you want to buy?
                    (Enter item number to select item.)""" +
                    "\nYour EP Balance: " + Player.points.getPoints() +
                    """
                                            
                            ------------------------------------
                            1. Fruit [30 EP]
                            2. Vegetarian Meal Kit [100 EP]
                            3. Carnivore Meal Kit [500 EP]
                            4. Stegosaur Egg [200 EP]
                            5. Brachiosaur Egg [500 EP]
                            6. Allosaur Egg [1000 EP]
                            7. Laser Gun [500 EP]
                            8. Quit Buying
                            ------------------------------------
                            Insert item number here:
                            """);

            int choice = Character.getNumericValue(choices.readChar());

            if (choice == 8) {
                return "You've decided to walk away from the vending machine.";
            }
            else if (choice>=1 && choice<=7) {
                choices.println("How many of these do you need?\n" +
                        "You currently have " + Player.points.getPoints() + " EP."
                        + "\nYou can buy a maximum amount of " + Player.points.getPoints()/VendingMachine.vendingItems[choice-1].getPrices() + "."
                        + "\nInsert amount: ");
                int amount = scanner.nextInt();
                if (amount <= Player.points.getPoints()/VendingMachine.vendingItems[choice-1].getPrices()) {
                    Player.points.setPoints(-(VendingMachine.vendingItems[choice-1].getPrices()*amount));
                    choices.println("You have bought " + amount + " item(s). " +
                            "\nYour EP balance now is: "+ Player.points.getPoints() + "\n");

                    switch (choice){
                        case 1 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Fruit("Fruit"));
                            }
                            return amount + " " + VendingMachine.vendingItems[choice-1].toString() +"(s) added to inventory";
                        }
                        case 2 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new VegeMealKit("VegeMealKit"));
                            }
                            return amount + " Vegetarian Meal Kits(s) added to inventory";
                        }
                        case 3 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new MeatMealKit("MeatMealKit"));
                            }
                            return amount + " Carnivorous Meal Kits(s) added to inventory";
                        }
                        case 4 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg( "Stegosaur", 'o'));
                            }
                            return amount + " " + VendingMachine.vendingItems[choice-1].toString() +"(s) added to inventory";
                        }
                        case 5 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg("Brachiosaur", 'O'));
                            }
                            return amount + " " + VendingMachine.vendingItems[choice-1].toString() +"(s) added to inventory";
                        }
                        case 6 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg("Allosaur", '0'));
                            }
                            return amount + " " + VendingMachine.vendingItems[choice-1].toString() +"(s) added to inventory";
                        }
                        case 7 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new LazerGun("LazerGun"));
                            }
                            return amount + " " + VendingMachine.vendingItems[choice-1].toString() +"(s) added to inventory";
                        }
                    }

                } else {
                    return  "You do not have enough points to buy " + amount + " " + VendingMachine.vendingItems[choice-1].toString() + "(s)" ;
                }
            }
            else {
                choices.println("There is no such item! Try again.");
            }
        }
    }

    /**
     * A method to show the choice for the player to interact with the vending machine.
     * @param actor The actor performing the action.
     * @return A string to indicate the player has accessed the vending machine
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " access " + "Vending Machine";
    }
}
