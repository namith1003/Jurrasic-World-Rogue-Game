package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Egg extends FoodItem {

    private int eggFloorCounter = 0;
    private Dinosaur dinosaur;
    private String dinoName;
    public Egg(String name, String dinoName, char displayChar) {
        super(name, displayChar);
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

    public void hatch(Location currentLocation){
        eggFloorCounter++;

        switch (dinoName) {
            case "Stegosaur" -> {
                if (eggFloorCounter >= 10){
                    dinosaur = new Stegosaur("Stegosaur");
                    currentLocation.addActor(dinosaur);
                    Player.points.setPoints(100);
                }
            }
            case "Brachiosaur" -> {
                if (eggFloorCounter >= 20){
                    dinosaur = new Brachiosaur("Brachiosaur");
                    currentLocation.addActor(dinosaur);
                    Player.points.setPoints(1000);
                }
            }
            case "Allosaur" -> {
                if (eggFloorCounter >= 50){
                    dinosaur = new Allosaur("Allosaur");
                    currentLocation.addActor(dinosaur);
                    Player.points.setPoints(1000);
                }
            }
        }
    }

}
