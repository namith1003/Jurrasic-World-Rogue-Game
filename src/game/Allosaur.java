package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Allosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private ArrayList<Behaviour> behaviour = new ArrayList<>();
    private HashMap<Integer, Stegosaur> stegosaurs = new HashMap<>();
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
        behaviour.add(new WanderBehaviour());

        if (new Random().nextInt(2) == 0){
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }

    }

    public Allosaur(String name, String gender){
        super(name, 'A', 20);
        maxHitPoints = 100;
        behaviour.add(new WanderBehaviour());

        /*behaviour.add(new FollowBehaviour());*/

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

        for (int x = 0; x < 80; x++){
            for (int y = 0; y < 25; y++){
                Actor actor = new Location(map, x, y).getActor();
                if (actor != null && actor.toString().equals("Stegosaur")){
                    int distance = new FollowBehaviour(actor).distance(map.locationOf(actor), map.locationOf(this));
                    stegosaurs.put(distance, (Stegosaur) actor);
                }
            }
        }
        if (stegosaurs.size() != 0) {
            int lowestDistance = (int) stegosaurs.keySet().toArray()[0];
            for (int keys : stegosaurs.keySet()) {
                if (keys < lowestDistance) {
                    lowestDistance = keys;
                }
            }

            if (new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this,map) != null){
                behaviour.add(new FollowBehaviour(stegosaurs.get(lowestDistance)));
            }
            /*else if (new FollowBehaviour(stegosaurs.get(lowestDistance)).location.getActor().toString().equals("Stegosaur")){
                return new AttackAction(stegosaurs.get(lowestDistance));
            }*/
            else{
                return new AttackAction(stegosaurs.get(lowestDistance));
            }
            return behaviour.get(1).getAction(this,map);
        }

        Action wander = behaviour.get(0).getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "claws");
    }
}
