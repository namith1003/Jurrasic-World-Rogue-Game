package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Egg extends FoodItem {

    private int eggFloorCounter = 0;
    private Dinosaur dinosaur;
    public Egg(String name, char displayChar) {
        super(name, displayChar);

    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        hatch(currentLocation);
    }

    public void hatch(Location currentLocation){
        eggFloorCounter++;

        switch (name) {
            case "stegosaur" -> {
                if (eggFloorCounter >= 10){
                    dinosaur = new Stegosaur("stegosaur");
                    currentLocation.addActor(dinosaur);
                    Player.points.setPoints(100);
                }
            }
            case "brachiosaur" -> {
                if (eggFloorCounter >= 20){
                    dinosaur = new Brachiosaur("brachiosaur");
                    currentLocation.addActor(dinosaur);
                    Player.points.setPoints(1000);
                }
            }
            case "allosaur" -> {
                if (eggFloorCounter >= 50){
                    dinosaur = new Allosaur("allosaur");
                    currentLocation.addActor(dinosaur);
                    Player.points.setPoints(1000);
                }
            }
        }
    }

}
