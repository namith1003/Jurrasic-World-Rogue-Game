package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Allosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;
    private static final String[] diet = {"Fruit", "MeatMealKit", "Egg", "DinoCorpse"};
    /**
     * Constructor.
     *
     * @param name the name of this Allosaur
     *
     */
    public Allosaur(String name) {
        super(name, 'A', 20);
        maxHitPoints = 100;
        behaviour = new WanderBehaviour();

        if (new Random().nextInt(2) == 0){
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }
    }

    public Allosaur(String name, String gender){
        super(name, 'A', 20);
        maxHitPoints = 100;
        behaviour = new WanderBehaviour();

        if (gender.equals("male")){
            addCapability(Gender.MALE);
        } else if (gender.equals("female") ){
            addCapability(Gender.FEMALE);
        }

    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        hitPoints--;
        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }
}
