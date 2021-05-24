package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.Random;

/**
 * the class for the action for the dinosaurs to eat food that they have found, it will remove the fruit or food item
 * from the relevant location on the map, if the stegosaur eats a fruit on a bush the fruit will be removed from the
 * bushes list of fruits and if the brachiosaur eats a fruit from a tree the fruit is removed from the list of fruits
 * on the tree in a similar way and if the fruit or food item is on the map and is eated it is directly removed from the map.
 */
public class EatingAction extends Action {

    /**
     * the location where the food items are found
     */
    private Location targetLocation;

    /**
     * constructor for the eating action of the dinosaur
     * @param targetLocation the location of the dinosaur that is going to eat
     */
    public EatingAction(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    /**
     * performs the action of for the dinosaur eating the food source it has found.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description how much the dinosaur has healed for by the item it has consumed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> items = targetLocation.getItems();
        Ground theTile = targetLocation.getGround();
        Actor target = targetLocation.getActor();

        switch (actor.toString()) {
            case "Stegosaur" -> {

                // stegosaur searches the fruits on the bush that it has found and eats one
                if (theTile.getFruits() != null ) {
                    if (theTile.getFruits().size() != 0) {

                        FoodItem theFood = theTile.removeFruit();
                        actor.heal(theFood.getHealValue());
                        map.locationOf(actor).removeItem(theFood);
                        return "Stegosaur has healed " + theFood.getHealValue();
                    }
                } else {
                    // stegosaur has found a food item on the map and will consume it.
                    for (Item item : items) {
                        for (String food: actor.getDiet()){
                            if (item.toString().equals(food)){
                                actor.heal(item.getHealValue());
                                map.locationOf(actor).removeItem(item);
                                return "Stegosaur has healed " + item.getHealValue();
                            }
                        }
                    }
                }
            }
            case "Brachiosaur" -> {
                // will search the tree it has found and will eat a ripe fruit on the tree, since the brachiosaur
                // has a weka digestive system it wil not heal the full amount a fruit can give and heals only 5 hp.
                if(theTile.getFruits().size() != 0){

                    FoodItem theFood = theTile.removeFruit();
                    actor.heal(5);

                    map.locationOf(actor).removeItem(theFood);
                    return "Brachiosaur has healed " + 5;

                }
            }
            case "Allosaur" -> {
                if (items != null) {
                    // Allosaur has found a food item on the map and will consume it.
                    for (Item item : items) {
                        for (String food: actor.getDiet()){
                            if (item.toString().equals(food)){
                                actor.heal(item.getHealValue());
                                map.locationOf(actor).removeItem(item);
                                return "Allosaur has healed " + item.getHealValue();
                            }
                        }
                    }
                }
                if (target != null) {
                    actor.heal(30);
                    map.removeActor(target);
                    return "Allosaur has healed 30";
                }

            }
            case "Pterodactyl" -> {

                // Pterodactyl searches for a lake with fish in it and eats randomly 0, 1 or 2 fish
                // while flying over the lake and also drinks a sip of the lake while doing so.
                if (theTile.getFish() != null) {
                    if (theTile.getFish().size() != 0) {
                        int numberOfFish = 0;
                        if (theTile.getFish().size() > 1) {
                            numberOfFish = new Random().nextInt(3);
                        } else if (theTile.getFish().size() == 1) {
                            numberOfFish = new Random().nextInt(2);
                        }
                        int totalHeal = 0;

                        for (int i = 1; i <= numberOfFish; i++) {
                            FoodItem theFood = theTile.removeFish();
                            actor.heal(theFood.getHealValue());

                            if (theTile.getSips() != 0) {
                                theTile.removeSip();
                                actor.drink(30);
                            }

                            totalHeal += theFood.getHealValue();
                            map.locationOf(actor).removeItem(theFood);
                        }
                        return "Pterodactyl has healed " + totalHeal;
                    }
                } else {
                    // Pterodactyl has found a food item on the map that's in its diet and will consume it.
                    for (Item item : items) {
                        for (String food: actor.getDiet()){
                            if (item.toString().equals(food)){
                                if (item.toString().split(" ")[1].equals("Corpse")){
                                    actor.heal(10);
                                    item.setHealValue(item.getHealValue() - 10);
                                    return "Pterodactyl has eaten a piece of corpse and healed 10";
                                } else {
                                    actor.heal(item.getHealValue());
                                    map.locationOf(actor).removeItem(item);
                                    return "Pterodactyl has healed " + item.getHealValue();
                                }
                            }
                        }
                    }
                }
            }

        }
        return "Invalid Actor";
    }

    /**
     * no menu will be displayed to the user while this action is occurring
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
