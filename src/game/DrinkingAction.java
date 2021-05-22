package game;

import edu.monash.fit2099.engine.*;

public class DrinkingAction extends Action {
    /**
     * the location where the lake sources are found
     */
    private Location waterLocation;

    /**
     * constructor for the drinking action of the dinosaur
     * @param waterLocation the location of the dinosaur that is going to drink
     */
    public DrinkingAction(Location waterLocation) {
        this.waterLocation = waterLocation;
    }

    /**
     * performs the action of for the dinosaur drinking from the lake source it has found.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description how much the dinosaur has watered for by the lake it has drunk from.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Ground lake = waterLocation.getGround();

        switch (actor.toString()) {
            case "Stegosaur", "Allosaur", "Pterodactyl" -> {
                if(lake.getSips() != 0){

                    lake.removeSip();
                    actor.drink(30);

                    return actor.toString()+" has drunk " + 30;

                }
            }
            case "Brachiosaur" -> {
                if(lake.getSips() != 0){

                    lake.removeSip();
                    actor.drink(80);

                    return "Brachiosaur has drunk " + 80;

                }
            }
        }

        return "";
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
