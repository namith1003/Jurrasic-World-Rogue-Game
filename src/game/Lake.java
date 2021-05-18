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
    private ArrayList<Fish> fish;
    /**
     * the number of turns since the last chance to rain for all the lakes, rain might happen every 10 turns
     * for all lakes
     */
    private static int rainTurns = 0;

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
        rain();
        sips += rainValue;
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

    /**
     * makes it rain for all the lakes every 10 turns with a 20% chance of raining
     * and the lakes being refilled with sips
     */
    public static void rain(){
        rainTurns++;
        if (rainTurns == numOfLakes*9 + 1) {
            if (new Random().nextInt(5) == 0){
                double rainFall = (new Random().nextInt(6) + 1)*1.0/10;
                rainValue = (int) (rainFall*20);
            }
        } else if (rainTurns > numOfLakes*10){
            rainTurns = 1;
            rainValue = 0;
        }
    }


}
