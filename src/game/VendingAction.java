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
    private static final int[] priceList = {30, 100, 500, 200, 500, 1000, 500};

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
                        + "\nYou can buy a maximum amount of " + Player.points.getPoints()/priceList[choice-1] + "."
                        + "\nInsert amount: ");
                int amount = scanner.nextInt();
                if (amount <= Player.points.getPoints()/priceList[choice-1]) {
                    Player.points.setPoints(-(priceList[choice-1]*amount));
                    choices.println("You have bought " + amount + " item(s). " +
                            "\nYour EP balance now is: "+ Player.points.getPoints());

                    switch (choice){
                        case 1 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Fruit("Fruit"));
                            }
                        }
                        case 2 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new VegeMealKit("VegeMealKit", '^'));
                            }
                        }
                        case 3 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new MeatMealKit("MeatMealKit", '%'));
                            }
                        }
                        case 4 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg( "Stegosaur", 'o'));
                            }
                        }
                        case 5 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg("Brachiosaur", 'O'));
                            }
                        }
                        case 6 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg("Allosaur", '0'));
                            }
                        }
                        case 7 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new LazerGun("LazerGun"));
                            }
                        }
                    }

                } else {
                    choices.println("You don't have enough points to buy these!");
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
