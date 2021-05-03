package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Scanner;

public class VendingAction extends Action{

    /**
     * The vending machine that is accessed
     */
    protected VendingMachine vendingMachine;
    Scanner scanner = new Scanner(System.in);
    private final int[] priceList = {30, 100, 500, 200, 500, 1000, 500};

    public VendingAction(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        while (true) {
            System.out.print("""
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

            int choice = scanner.nextInt();

            if (choice == 8) {
                return "You've decided to walk away from the vending machine.";
            }
            else if (choice>=1 && choice<=7){
                System.out.print("How many of these do you need?\n" +
                        "You currently have " + Player.points.getPoints()
                        + " Eco Points."
                        + "\nYou can buy a maximum amount of " + Player.points.getPoints()/priceList[choice-1]
                        + "\nInsert amount: ");
                int amount = scanner.nextInt();
                if (amount <= Player.points.getPoints()/priceList[choice-1]) {
                    Player.points.setPoints(-(priceList[choice-1]*amount));
                    System.out.println("You have bought " + amount + " item(s). " +
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
                                actor.addItemToInventory(new Egg("Egg", "Stegosaur", 'o'));
                            }
                        }
                        case 5 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg("Egg", "Brachiosaur", 'O'));
                            }
                        }
                        case 6 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new Egg("Egg", "Allosaur", '0'));
                            }
                        }
                        case 7 -> {
                            for (int i = 0; i < amount; i++) {
                                actor.addItemToInventory(new LazerGun("LazerGun"));
                            }
                        }
                    }

                } else {
                    System.out.println("You don't have enough points to buy these!");
                }
            }
            else {
                System.out.println("change da world, goodby");
            }
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " access " + "vendingMachine";
    }
}
