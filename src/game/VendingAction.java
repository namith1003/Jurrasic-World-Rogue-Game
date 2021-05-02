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
        boolean quit = false;
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
                8. Nah, I'm broke
                ------------------------------------
                Insert item number here:
                """);

        int choice = scanner.nextInt();
        while (!quit) {
            if (choice == 8) {
                quit = true;
            }
            else if (choice>=1 && choice<=7){
                // TODO: ASK AMOUNT
                System.out.print("How many of these do you need?\n" +
                        "You currently have " + Player.points.getPoints()
                        + " Eco Points.\nInsert amount: ");
                int amount = scanner.nextInt();
                if (amount <= Player.points.getPoints()/priceList[choice-1]) {
                    Player.points.setPoints(-(priceList[choice-1]*amount));
                    System.out.println("You have bought " + amount + " item(s). " +
                            "\nYour EP balance now is: "+ Player.points.getPoints());
                    continue;
                } else {
                    System.out.println("you aint that rich pls");
                }
            }
            else {
                System.out.println("what");
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " access " + "vendingMachine";
    }
}
