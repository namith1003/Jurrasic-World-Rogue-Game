package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Brachiosaur extends Dinosaur{
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;
    private HashMap<Integer, Location> targets = new HashMap<>();

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
        diet = new String[]{"Fruit", "VegeMealKit"};

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
        targets = new HashMap<>();
		/*age++;
		if (age > 10){

			map.locationOf(this).addItem(new DinoCorpse("Stegosaur", 'X'));
			map.removeActor(this);
			return new DoNothingAction();
		}*/
        hitPoints--;

        Location targetLocation;
        for (int x = 0; x < 80; x++){
            for (int y = 0; y < 25; y++){
                Actor actor = map.at(x, y).getActor();
                ArrayList<Item> items = new ArrayList<>(map.at(x, y).getItems());

                if (items.size() != 0) {
                    targetLocation = map.at(x, y);

                    for (Item item : items) {
                        if (item.toString().equals("Fruit") || item.toString().equals("VegeMealKit")){
                            Fruit fruit = (Fruit) item;
                            if (fruit.hasCapability(FruitStatus.ON_TREE)) {
                                Location here = map.locationOf(this);
                                int distance = new HungryBehaviour(targetLocation).distance(here, targetLocation);
                                targets.put(distance, targetLocation);
                            }
                        }
                    }
                }
            }
        }
        if (targets.size() != 0) {
            int lowestItemDistance = (int) targets.keySet().toArray()[0];
            for (int keys : targets.keySet()) {
                if (keys < lowestItemDistance) {
                    lowestItemDistance = keys;
                }
            }

            if (lowestItemDistance > 0) {
                targetLocation = targets.get(lowestItemDistance);
                return new HungryBehaviour(targetLocation).getAction(this, map);
            } else {
                targetLocation = targets.get(lowestItemDistance);
                return new EatingAction(targetLocation);
            }
        }


        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }
}
