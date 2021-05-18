package game;

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
        System.out.println(sips);
        fishBorn();
    }

    /**
     * creates a fish in the lake with a 60% chance of one being born every turn
     */
    public void fishBorn(){
        if (fish.size() <= 25) {
            if (new Random().nextInt(5) <= 2) {
                fish.add(new Fish("Fish"));
            }
        }
    }

    public int getSips() {
        return sips;
    }

    public void setSips(int sips) {
        this.sips = sips;
    }
}