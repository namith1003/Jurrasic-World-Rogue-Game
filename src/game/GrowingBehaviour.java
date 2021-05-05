package game;

import edu.monash.fit2099.engine.*;

public class GrowingBehaviour implements Behaviour{
    // private int turnCounter = 0;
    public GrowingBehaviour(){};

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        // still WIP!!
        switch (dinosaur.toString()){
            case "Stegosaur" -> {
                if (dinosaur.getAge() == 30) {
                    // todo: delete instance and replace with grown up
                    return new GrowingAction(dinosaur);
                }
            }
            case "Brachiosaur", "Allosaur" -> {
                if (dinosaur.getAge() == 50) {
                    return new GrowingAction(dinosaur);
                }
            }
        }
        return null;
    }
}
