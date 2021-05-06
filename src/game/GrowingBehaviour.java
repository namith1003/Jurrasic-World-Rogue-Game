package game;

import edu.monash.fit2099.engine.*;

public class GrowingBehaviour implements Behaviour{
    // private int turnCounter = 0;
    public GrowingBehaviour(){};

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // still WIP!!
        switch (actor.toString()){
            case "Stegosaur" -> {
                if (actor.getAge() == 30) {
                    return new GrowingAction(actor);
                }
            }
            case "Brachiosaur", "Allosaur" -> {
                if (actor.getAge() == 50) {
                    return new GrowingAction(actor);
                }
            }
        }
        return null;
    }
}
