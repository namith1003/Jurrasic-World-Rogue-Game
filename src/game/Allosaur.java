package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Allosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private ArrayList<Behaviour> behaviour = new ArrayList<>();
    private HashMap<Integer, Stegosaur> stegosaurs = new HashMap<>();
    private ArrayList<Stegosaur> attackedStegosaurs = new ArrayList<>();
    private ArrayList<Integer> timeRemaining = new ArrayList<>();
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
        stegosaurs = new HashMap<>();

        if (attackedStegosaurs != null && timeRemaining.size() != 0){
            for (int i = 0; i < timeRemaining.size(); i++){
                timeRemaining.set(i,timeRemaining.get(i) - 1);
            }

            if (timeRemaining.get(0) == 0){
                timeRemaining.remove(0);
                attackedStegosaurs.remove(0);
            }

        }

        for (int x = 0; x < 80; x++){
            for (int y = 0; y < 25; y++){
                Actor actor = new Location(map, x, y).getActor();
                if (actor != null && actor.toString().equals("Stegosaur") && !attackedStegosaurs.contains(actor)){
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
                return new FollowBehaviour(stegosaurs.get(lowestDistance)).getAction(this,map);
            }
            /*else if (new FollowBehaviour(stegosaurs.get(lowestDistance)).location.getActor().toString().equals("Stegosaur")){
                return new AttackAction(stegosaurs.get(lowestDistance));
            }*/
            else{
                attackedStegosaurs.add(stegosaurs.get(lowestDistance));
                timeRemaining.add(20);
                heal(20);
                return new AttackAction(stegosaurs.remove(lowestDistance));
            }
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
