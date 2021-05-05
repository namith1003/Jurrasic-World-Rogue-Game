package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class LayEggAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(BreedingStatus.IS_PREGNANT);
        actor.setPregnantCounter(0);

        char egg;
        switch (actor.toString()) {
            case "Allosaur" -> egg = '0';
            case "Brachiosaur" -> egg = 'O';
            case "Stegosaur" -> egg = 'o';
            default -> throw new IllegalStateException("Unexpected value: " + actor.toString());
        }

        map.locationOf(actor).addItem(new Egg(actor.toString(), egg));
        return actor.toString() + " laid an Egg";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
