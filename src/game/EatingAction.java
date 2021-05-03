package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class EatingAction extends Action {

    private Location targetLocation;
    public EatingAction(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> items = targetLocation.getItems();
        switch (actor.toString()) {
            case "Stegosaur" -> {
                Stegosaur stegosaur = (Stegosaur) actor;
                for (Item item : items) {
                    for (String food: Stegosaur.getDiet()){
                        if (item.toString().equals(food)){
                            FoodItem theFood = (FoodItem) item;
                            stegosaur.heal(theFood.getHealValue());
                            map.locationOf(actor).removeItem(theFood);
                            return "stegosaur has healed " + theFood.getHealValue();
                        }
                    }
                }
            }
            case "Brachiosaur" -> {
                Brachiosaur brachiosaur = (Brachiosaur) actor;
                for (Item item : items) {
                    for (String food: Brachiosaur.getDiet()){
                        if (item.toString().equals(food)){
                            FoodItem theFood = (FoodItem) item;
                            brachiosaur.heal(theFood.getHealValue());
                            map.locationOf(actor).removeItem(theFood);
                            return "brachiosaur has healed " + theFood.getHealValue();
                        }
                    }
                }
            }
            case "Allosaur" -> {
                Allosaur allosaur = (Allosaur) actor;
                for (Item item : items) {
                    for (String food: Allosaur.getDiet()){
                        if (item.toString().equals(food)){
                            FoodItem theFood = (FoodItem) item;
                            allosaur.heal(theFood.getHealValue());
                            map.locationOf(actor).removeItem(theFood);
                            return "allosaur has healed " + theFood.getHealValue();
                        }
                    }
                }
            }
        }
        return "Invalid Actor";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
