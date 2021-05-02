package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Brachiosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;
    private static final String[] diet = {"Fruit", "VegeMealKit"};
    /**
     * Constructor.
     *
     * @param name the name of this Brachiosaur
     *
     */
    public Brachiosaur(String name) {
        super(name, 'B', 100);
        maxHitPoints = 160;
        behaviour = new WanderBehaviour();

        if (new Random().nextInt(2) == 0){
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }
    }

    public Brachiosaur(String name, String gender){
        super(name, 'B', 100);
        maxHitPoints = 160;
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
