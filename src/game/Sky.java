package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class Sky extends Ground {

    /**
     * the number of turns since the last chance to rain for all the lakes, rain might happen every 10 turns
     * for all lakes
     */
    private static int rainTurns = 0;
    /**
     * the amount of rain that comes from the rain
     */
    public static int rainValue=0;

    /**
     * Constructor.
     *
     */
    public Sky() {
        super('`');
    }

    @Override
    public void tick(Location location) {
        rain();
    }

    /**
     * makes it rain for all the lakes every 10 turns with a 20% chance of raining
     * and the lakes being refilled with sips
     */
    public static void rain(){
        rainTurns++;
        if (rainTurns == 10) {
            rainTurns = 0;
            if (new Random().nextInt(5) == 0){
                double rainFall = (new Random().nextInt(6) + 1)*1.0/10;
                rainValue = (int) (rainFall*20);
            }
        } else{
            rainValue = 0;
        }
    }
}
