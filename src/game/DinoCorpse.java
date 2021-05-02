package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class DinoCorpse extends FoodItem{

    protected int deadTime = 0;
    public DinoCorpse(String name, char displayChar) {
        super(name, displayChar);

        switch (name){
            case "Stegosaur", "Allosaur" -> healValue = 50;
            case "Brachiosaur" -> healValue = 100;
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {

    }

    private void decay(Location currentLocation) {
        currentLocation.removeItem(this);
    }

    @Override
    public void tick(Location currentLocation) {
        switch (name){
            case "Stegosaur", "Allosaur" -> {
                if (deadTime > 20){
                    decay(currentLocation);
                }
            }
            case "Brachiosaur" -> {
                if (deadTime > 40){
                    decay(currentLocation);
                }
            }
        }

        deadTime++;
    }
}
