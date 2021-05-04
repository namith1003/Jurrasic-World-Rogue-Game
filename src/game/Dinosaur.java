package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Dinosaur extends Actor {

    protected static String[] diet = new String[0];

    protected String gender;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        if (new Random().nextInt(2) == 0){
            this.gender = "male";
        } else {
            this.gender="female";
        }
    }

    public Dinosaur(String name, char displayChar, int hitPoints, String gender) {
        super(name, displayChar, hitPoints);
        if (gender.equals("male")){
            this.gender="male";
        } else if (gender.equals("female") ){
            this.gender="female";
        }
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        actions.add(new Actions(new AttackAction(this)));
        actions.add(new Actions(new FeedAction(this)));
        return actions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    public static String[] getDiet() {
        return diet;
    }

    public static void setDiet(String[] diet) {
        Dinosaur.diet = diet;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
