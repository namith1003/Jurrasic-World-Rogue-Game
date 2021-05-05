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
                Bushes theBush = (Bushes) targetLocation.getGround();
                if(theBush.getFruits().size() != 0){

                    FoodItem theFood = theBush.removeFruit();
                    stegosaur.heal(theFood.getHealValue());
                    map.locationOf(actor).removeItem(theFood);
                    return "Stegosaur has healed " + theFood.getHealValue();

                }
            }
            case "Brachiosaur" -> {
                Brachiosaur brachiosaur = (Brachiosaur) actor;
                Tree theTree = (Tree) targetLocation.getGround();
                if(theTree.getFruits().size() != 0){

                    FoodItem theFood = theTree.removeFruit();
                    brachiosaur.heal(theFood.getHealValue());

                    map.locationOf(actor).removeItem(theFood);
                    return "Brachiosaur has healed " + theFood.getHealValue();

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
                            return "Allosaur has healed " + theFood.getHealValue();
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
