package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Egg extends FoodItem {

    private int eggFloorCounter = 0;
    private String dinoName;
    public Egg(String dinoName, char displayChar) {
        super("Egg", displayChar);
        this.dinoName = dinoName;
        this.healValue = 10;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        hatch(currentLocation);
    }

    @Override
    public String toString() {
        return dinoName + " " + name;
    }

    public void hatch(Location currentLocation){
        eggFloorCounter++;

        Dinosaur dinosaur;
        switch (dinoName) {
            case "Stegosaur" -> {
                if (eggFloorCounter >= 10){
                    dinosaur = new Stegosaur("Stegosaur");
                    currentLocation.addActor(dinosaur);
                    currentLocation.removeItem(this);
                    Player.points.setPoints(100);
                }
            }
            case "Brachiosaur" -> {
                if (eggFloorCounter >= 20){
                    dinosaur = new Brachiosaur("Brachiosaur");
                    currentLocation.addActor(dinosaur);
                    currentLocation.removeItem(this);
                    Player.points.setPoints(1000);
                }
            }
            case "Allosaur" -> {
                if (eggFloorCounter >= 50){
                    dinosaur = new Allosaur("Allosaur");
                    currentLocation.addActor(dinosaur);
                    currentLocation.removeItem(this);
                    Player.points.setPoints(1000);
                }
            }
        }
    }

}
