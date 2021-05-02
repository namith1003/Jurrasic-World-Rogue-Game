package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class VegeMealKit extends MealKit{
    public VegeMealKit(String name, char displayChar) {
        super(name, displayChar);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}