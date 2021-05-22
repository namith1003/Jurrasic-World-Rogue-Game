package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Location;

/**
 * The class to create an egg instance of the species of dinosaur that lays it, or bought from a vending machine,
 * it also checks if the egg can hatch and creates a baby dinosaur on the location it hatches, the egg cannot hatch if
 * its in the player inventory.
 */
public class Egg extends FoodItem {

    /**
     * stores the number of turns that the egg has been on the floor for.
     */
    private int eggFloorCounter = 0;
    /**
     * the name of the species of dinosaur that laid the egg.
     */
    private String dinoName;

    private Display display = new Display();

    /**
     * constructor for initializing the variables of the egg when created.
     * @param dinoName the species name of the dinosaur the egg belongs to.
     * @param displayChar the display character representing the egg when its on the floor
     */
    public Egg(String dinoName, char displayChar) {
        super("Egg", displayChar);
        this.dinoName = dinoName;
        this.healValue = 10;

        switch (dinoName) {
            case "Stegosaur" -> price = 200;
            case "Brachiosaur", "Pterodactyl" -> price = 500;
            case "Allosaur" -> price = 1000;
        }
    }

    /**
     * provides the passage of time for the egg once its on the floor and keeps calling the hatch function every
     * turn to see if the egg can hatch
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        hatch(currentLocation);
    }

    /**
     * returns the name of the egg along with the species it belongs to
     * @return the name of the egg and the species it belongs to
     */
    @Override
    public String toString() {
        return dinoName + " " + name;
    }

    /**
     * keeps incrementing the counter for when the egg is on the floor when called by the tick function every turn
     * and when the egg has been on the floor for the required amount of turns the egg will hatch and a new instance
     * of a dinosaur of the same species as the egg will be created on the same location the egg hatched at.
     * @param currentLocation the location of the egg on the map
     */
    public void hatch(Location currentLocation){
        eggFloorCounter++;

        Dinosaur dinosaur;
        switch (dinoName) {
            case "Stegosaur" -> {
                if (eggFloorCounter >= 10 && currentLocation.getActor() == null){
                    dinosaur = new Stegosaur("Stegosaur");
                    currentLocation.addActor(dinosaur);
                    display.println("Stegosaur was born");
                    currentLocation.removeItem(this);
                    Player.points.setPoints(100);
                }
            }
            case "Pterodactyl" -> {
                if (eggFloorCounter >= 10 && currentLocation.getActor() == null){
                    dinosaur = new Pterodactyl("Pterodactyl");
                    currentLocation.addActor(dinosaur);
                    display.println("Pterodactyl was born");
                    currentLocation.removeItem(this);
                    Player.points.setPoints(100);
                }
            }
            case "Brachiosaur" -> {
                if (eggFloorCounter >= 20 && currentLocation.getActor() == null){
                    dinosaur = new Brachiosaur("Brachiosaur");
                    currentLocation.addActor(dinosaur);
                    display.println("Brachiosaur was born");
                    currentLocation.removeItem(this);
                    Player.points.setPoints(1000);
                }
            }
            case "Allosaur" -> {
                if (eggFloorCounter >= 50 && currentLocation.getActor() == null){
                    dinosaur = new Allosaur("Allosaur");
                    currentLocation.addActor(dinosaur);
                    display.println("Allosaur was born");
                    currentLocation.removeItem(this);
                    Player.points.setPoints(1000);
                }
            }
        }
    }

}
