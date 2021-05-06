package game;

import edu.monash.fit2099.engine.*;

public class GrowingBehaviour implements Behaviour{
    // private int turnCounter = 0;

    /**
     * The constructor for GrowingBehaviour.
     */
    public GrowingBehaviour(){};

    /**
     * The function to check the dinosaur's age.
     * If it's over a certain value, it calls GrowingAction on the dinosaur.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Either returns a string from GrowingAction if a dinosaur "grows" via the method,
     * or nothing at all if no dinosaurs have grown into an adult.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;

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
