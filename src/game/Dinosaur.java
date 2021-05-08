package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * The general class for all the dinosaurs with functions of common functionality between all the dinosaur species that
 * will be made.
 */
public class Dinosaur extends Actor {

    /**
     * the diet of the dinosaur that is created, which has all the food items the dinosaur can consume
     */
    protected static String[] diet = new String[0];
    /**
     * the gender of the dinosaur
     */
    protected String gender;
    /**
     * the food level that the dinosaur becomes hungry at
     */
    protected int hungerLevel;
    /**
     * the number of turns that the dinosaur has been pregnant for
     */
    protected int pregnantCounter = 0;
    /**
     * the number of turns that the dinosaur has been unconscious for at a stretch
     */
    protected int unconsciousCounter = 0;
    /**
     * the age of the dinosaur from time of creation
     */
    protected int age = 0;
    /**
     * the amount of turns the dinosaur should be pregnant before laying her egg
     */
    protected int pregnantTime = 0;
    /**
     * whether the dinosaur is an adult or baby
     */
    protected boolean isAdult = false;



    /**
     * Constructor for the general dinosaurs, shared by all the species of dinosaur that extend this class.
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

    /**
     * all the actions that the player can do to the dinosaurs that inherit this class, including attacking and feeding
     * them.
     *
     * @param otherActor the Actor that might be performing attack or feeding
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return           the list of all the actions the player can do to the dinosaurs
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        actions.add(new Actions(new AttackAction(this)));
        actions.add(new Actions(new FeedAction(this)));
        return actions;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    /**
     * gets the list of the food items that the dinosaur can eat and is in its diet
     * @return the list of food items in the dinosaurs diet
     */
    @Override
    public String[] getDiet() {
        return diet;
    }

    /**
     * sets a diet as the dinosaurs new diet
     * @param diet the diet to set as the dinosaurs new diet
     */
    public static void setDiet(String[] diet) {
        Dinosaur.diet = diet;
    }

    /**
     * set the gender of the dinosaur instance
     * @param gender the new gender to set the dinosaur to
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get the hit points/ food level of the dinosaur
     * @return the hit points of the dinosaur
     */
    public int getHitPoints(){
        return hitPoints;
    }

    /**
     * set the hit points / food level of the dinosaur
     * @param newHitPoints the new hit points of the dinosaur to be set to
     */
    @Override
    public void setHitPoints(int newHitPoints) {
        this.hitPoints = newHitPoints;
    }

    /**
     * sets the new display character that represents the dinosaur on the map
     * @param newDisplayChar the new display character that represents the dinosaur on the map
     */
    @Override
    public void setDisplayChar(char newDisplayChar) {
        this.displayChar = newDisplayChar;
    }

    /**
     * checks if the dinosaurs hit points are under his hunger food level and returns whether or not the dinosaur is
     * hungry, it also sends a message to the player right when a dinosaur goes from being not hungry to hungry in
     * this turn.
     * @param map the map the dinosaurs are on
     * @return boolean of whether the dinosaur is hungry
     */
    public boolean isHungry(GameMap map){
        Display display = new Display();
        if (hitPoints >= hungerLevel){
            int newHunger = hitPoints - 1;
            if (newHunger < hungerLevel) {
                display.println(toString() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ") is getting hungry!");
            }
        }
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

    /**
     * sets the boolean representing whether or not the dinosaur is an adult
     */
    @Override
    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    /**
     * returns the boolean value representing whether or not the dinosaur is an adult
     * @return the boolean value representing whether or not the dinosaur is an adult
     */
    @Override
    public boolean getIsAdult() {
        return isAdult;
    }

    /**
     * gets the current age of the dinosaur
     * @return the current age of the dinosaur
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * sets the current age of the dinosaur with a new value
     * @param newAge the new value to set the current age of the dinosaur to
     */
    @Override
    public void setAge(int newAge) {
        this.age = newAge;
    }

    /**
     * gets the gender of the dinosaur
     * @return the gender of the dinosaur
     */
    @Override
    public String getGender() {
        return gender;
    }

    /**
     * gets the number of turns the dinosaur has been at a stretch unconscious for.
     * @return the number of turns the dinosaur has been at a stretch unconscious for.
     */
    @Override
    public int getUnconsciousCounter() {
        return unconsciousCounter;
    }

    /**
     * sets the new number of turns the dinosaur has been at a stretch unconscious for to a new value.
     * @param unconsciousCounter the new number of turns the dinosaur has been at a stretch unconscious for the dinosaur.
     */
    @Override
    public void setUnconsciousCounter(int unconsciousCounter) {
        this.unconsciousCounter = unconsciousCounter;
    }

    /**
     * increments the number of turns the dinosaur has been at a stretch unconscious for.
     */
    @Override
    public void increaseUnconsciousCounter(){
        this.unconsciousCounter++;
    }

    /**
     * gets the number of turns the dinosaur has been pregnant for.
     * @return the number of turns the dinosaur has been pregnant for.
     */
    @Override
    public int getPregnantCounter() {
        return pregnantCounter;
    }

    /**
     * sets the new number of turns the dinosaur has been pregnant for to a new value.
     * @param pregnantCounter the new number of turns the dinosaur has been pregnant for.
     */
    @Override
    public void setPregnantCounter(int pregnantCounter) {
        this.pregnantCounter = pregnantCounter;
    }

    /**
     * gets the number of turns the dinosaur should be pregnant for before laying her egg
     * @return the number of turns the dinosaur should be pregnant for before laying her egg
     */
    @Override
    public int getPregnantTime(){
        return pregnantTime;
    }

    /**
     * sets the number of turns the dinosaur should be pregnant for before laying her egg to a new value
     * @param pregnantTime the new number of turns the dinosaur should be pregnant for before laying her egg
     */
    @Override
    public void setPregnantTime(int pregnantTime){
        this.pregnantTime = pregnantTime;
    }

    /**
     * increments the number of turns the dinosaur has been pregnant for, by incrementing the pregnantCounter.
     */
    @Override
    public void updatePregnantCounter() {
        this.pregnantCounter++;
    }

    @Override
    public int getMaximumHitPoints(){
        return this.maxHitPoints;
    }
}
