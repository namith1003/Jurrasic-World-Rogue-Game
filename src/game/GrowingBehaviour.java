package game;

import edu.monash.fit2099.engine.*;

/**
 * A class to determine if a dinosaur should grow into its adult form.
 */
public class GrowingBehaviour implements Behaviour{

    /**
     * The method to check the actor's age.
     * If the actor hits a certain age value, it calls the GrowingAction to "grow" the dinosaur.
     * If not, does nothing.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Either returns a return string that indicates the dinosaur has "grown",
     * or nothing at all.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
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
