package game;

import edu.monash.fit2099.engine.Location;

/**
 * The class for the corpses of the dead dinosaurs, these are food items that are both portable and can be consumed by
 * Allosaur's since its a part of their diet
 */
public class DinoCorpse extends FoodItem{

    /**
     * number of turns that the corpse has been on the map for
     */
    protected int deadTime = 0;

    /**
     * constructor for initializing the corpses variables
     * @param name the name of the species of dinosaur that dies to leave the corpse
     */
    public DinoCorpse(String name) {
        super(name, 'X');

        switch (name){
            case "Stegosaur Corpse", "Allosaur Corpse" -> healValue = 50;
            case "Brachiosaur Corpse" -> healValue = 100;
        }
    }

    /**
     * this is called when the corpse should get decayed and removed from the map
     * @param currentLocation the location where the corpse is located at
     */
    private void decay(Location currentLocation) {
        currentLocation.removeItem(this);
    }

    /**
     * provides the passage of time on the corpse as its on the map, it will increment the number of turns the corpse
     * was on the map for and will call the decay function to remove the corpse once the required number of turns for
     * the corpse to rot is met.
     * @param currentLocation The location of the ground on which the corpse lies.
     */
    @Override
    public void tick(Location currentLocation) {
        switch (name){
            case "Stegosaur Corpse", "Allosaur Corpse" -> {
                if (deadTime > 20){
                    decay(currentLocation);
                }
            }
            case "Brachiosaur Corpse" -> {
                if (deadTime > 40){
                    decay(currentLocation);
                }
            }
        }
        deadTime++;
    }
}
