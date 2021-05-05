package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Dinosaur extends Actor {

    protected static String[] diet = new String[0];

    protected String gender;
    protected int hungerLevel;
    protected int pregnantCounter = 0;
    protected int unconsciousCounter = 0;
    protected int age = 0;
    protected int pregnantTime = 0;
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

    @Override
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public void setHitPoints(int newHitPoints) {
        this.hitPoints = newHitPoints;
    }

    public void setDisplayChar(char newDisplayChar) {
        this.displayChar = newDisplayChar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public boolean isHungry(GameMap map){
        Display display = new Display();
        /*if (hitPoints >= hungerLevel){
            int newHunger = hitPoints - 1;
            if (newHunger < hungerLevel) {
                display.println(toString() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ") is getting hungry!");
            }
        }*/
        hitPoints--;
        if (hitPoints < hungerLevel) {
            addCapability(BreedingStatus.CANNOT_BREED);
            removeCapability(BreedingStatus.CAN_BREED);
            return true;
        }else {
            removeCapability(BreedingStatus.CANNOT_BREED);
            addCapability(BreedingStatus.CAN_BREED);
            return false;
        }
    }

    @Override
    public int getUnconsciousCounter() {
        return unconsciousCounter;
    }

    @Override
    public void setUnconsciousCounter(int unconsciousCounter) {
        this.unconsciousCounter = unconsciousCounter;
    }

    @Override
    public void increaseUnconsciousCounter(){
        this.unconsciousCounter++;
    }

    @Override
    public int getPregnantCounter() {
        return pregnantCounter;
    }

    @Override
    public void setPregnantCounter(int pregnantCounter) {
        this.pregnantCounter = pregnantCounter;
    }

    @Override
    public int getPregnantTime(){
        return pregnantTime;
    }

    @Override
    public void setPregnantTime(int pregnantTime){
        this.pregnantTime = pregnantTime;
    }

    @Override
    public void updatePregnantCounter() {
        this.pregnantCounter++;
    }

}
