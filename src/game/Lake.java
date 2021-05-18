package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

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
     * number of fish in the lake
     */
    private int fish;
    /**
     * the number of turns since the last chance to rain for all the lakes, rain might happen every 10 turns
     * for all lakes
     */
    private static int rainTurns = 0;

    private static int numOfLakes = 0;

    private static int rainValue=0;

    /**
     * constructor for the lake instance, initializing all the starting values for the lake
     */
    public Lake() {
        super('~');
        sips = 25;
        fish = 5;
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


    public void fishBorn(){
        if (new Random().nextInt(5) <= 2){
            fish++;
        }
    }

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
