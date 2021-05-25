package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class for the lakes that have fish and sips of water in them
 */
public class Lake extends Ground {

    /**
     * number of sips left in the lake
     */
    private int sips;
    /**
     * Array List of all the fish in the lake
     */
    private ArrayList<Fish> fish = new ArrayList<>();


    /**
     * number of lakes that have been created
     */
    private static int numOfLakes = 0;

    /**
     * amount of sips to be added to all lakes
     */
    private static int rainValue=0;

    /**
     * constructor for the lake instance, initializing all the starting values for the lake
     */
    public Lake() {
        super('~');
        sips = 25;
        List<Fish> fishList = Arrays.asList(new Fish("Fish"),new Fish("Fish"),new Fish("Fish"),new Fish("Fish"),new Fish("Fish"));
        fish.addAll(fishList);
        numOfLakes++;
    }

    /**
     * every turn the lake has a chance for the rain to
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        sips += Sky.rainValue;
        fishBorn();
    }

    /**
     * creates a fish in the lake with a 60% chance of one being born every turn
     */
    public void fishBorn(){
        if (fish.size() < 24) {
            if (new Random().nextInt(5) <= 2) {
                fish.add(new Fish("Fish"));
            }
        }
    }

    /**
     * returns the number of sips left in the lake
     * @return the number of sips left in the lake
     */
    @Override
    public int getSips() {
        return sips;
    }

    /**
     * sets the new number of sips for the lake
     * @param sips the new number of sips for the lake
     */
    public void setSips(int sips) {
        this.sips = sips;
    }

    /**
     * remove a sip from the lake, usually used when a dinosaur drinks from the lake
     */
    public void removeSip(){
        this.sips --;
    }

    /**
     * Allows the player to interact with this lake and use the fish action on it once it is next to the lake
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the actions the user can do to this lake
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new FishAction(this));
    }

    /**
     * remove a fish from this lake, usually when a dinosaur eats a fish or the player fishes
     * in the lake
     * @return the fish that was removed from the lake
     */
    @Override
    public Fish removeFish(){
        // remove last fish in the array list for efficiency when shuffling
        if (fish.size() != 0) {
            return fish.remove(fish.size() - 1);
        }
        return null;
    }

    /**
     * returns the list of all the fish that are in the lake
     * @return the list of all the fish that are in the lake
     */
    @Override
    public ArrayList<Fish> getFish() {
        return fish;
    }

    /**
     * A method for the actor to know that the vending machine counts as impassable terrain
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.toString().equals("Player") || actor.hasCapability(FlyingStatus.CAN_FLY);
    }
}
