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
     * the water level that the dinosaur becomes thirsty at
     */
    protected int thirstLevel;
    /**
     * the water level that the dinosaur currently has
     */
    protected int waterLevel;
    /**
     * the maximum water level that the dinosaur can have
     */
    protected int maxWaterLevel;
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
     * Constructor for the general dinosaurs, shared by all the species of dinosaur that extend this class and will
     * set their name, display character and hit points and will also in random with a 50-50 chance the gender of the
     * dinosaur as a male or female.
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
        waterLevel = 60;
        thirstLevel = 40;
    }

    /**
     * the admin class to create dinosaurs which create dinosaurs in the same way by setting their name, display char
     * and hit points but unlike the three parameter constructor, it will allow direct setting of the gender of the
     * dinosaur
     * @param name the name of the dinosaur
     * @param displayChar the display character for the dinosaur
     * @param hitPoints the hit points during creation of the dinosaur
     * @param gender the gender during creation of the dinosaur
     */
    public Dinosaur(String name, char displayChar, int hitPoints, String gender) {
        super(name, displayChar, hitPoints);
        if (gender.equals("male")){
            this.gender="male";
        } else if (gender.equals("female") ){
            this.gender="female";
        }
        waterLevel = 60;
        thirstLevel = 40;
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
     * checks if the dinosaurs water levels are under his thirst water level and returns whether or not the dinosaur is
     * thirsty, it also sends a message to the player right when a dinosaur goes from being not thirsty to thirsty in
     * this turn.
     * @param map the map the dinosaurs are on
     * @return boolean of whether the dinosaur is thirsty
     */
    @Override
    public boolean isThirsty(GameMap map){
        Display display = new Display();
        if (waterLevel >= thirstLevel){
            int newThirst = waterLevel - 1;
            if (newThirst < thirstLevel) {
                display.println(toString() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ") is getting thirsty!");
            }
        }
        //decreases the dinosaurs water level every turn until it goes to zero.
        if (waterLevel > 0){
            waterLevel--;
        }

        System.out.println(waterLevel);
        // if dinosaur is thirsty it cannot breed and if not thirsty it can breed.
        if (waterLevel < thirstLevel) {
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

    /**
     * return the maximum hit points of the Actor.
     * @return the maximum hit points of the actor.
     */
    @Override
    public int getMaximumHitPoints(){
        return this.maxHitPoints;
    }

    /**
     * calls the required behaviours of the dinosaur for it to grow, the behaviour checks all the attributes of the
     * dinosaur and whether or not the requirements for it to grow are met, and returns the action for the dinosaur
     * to grow and if requirements not met returns null.
     * @param map the map that the actor is on
     * @return the action for the dinosaur to grow
     */
    public Action grows(GameMap map){
        return new GrowingBehaviour().getAction(this, map);
    }

    /**
     * calls the required behaviours of the dinosaur for it to lay an egg, the behaviour checks all the attributes of the
     * dinosaur and whether or not the requirements for it to lay an egg are met, and returns the action for the dinosaur
     * to lay an egg and if requirements not met returns null.
     * @param map the map that the actor is on
     * @return the action for the dinosaur to lay an egg
     */
    public Action layEgg(GameMap map){
        return new LayEggBehaviour().getAction(this,map);
    }

    /**
     * calls the required behaviours of the dinosaur for it to die, the behaviour checks all the attributes of the
     * dinosaur and whether or not the requirements for it to die are met, and returns the action for the dinosaur
     * to die and if requirements not met returns null.
     * @param map the map that the actor is on
     * @return the action for the dinosaur to die
     */
    public Action dies(GameMap map){
        return new DeathBehaviour().getAction(this, map);
    }

    /**
     * calls the required behaviours of the dinosaur for it to breed, the behaviour checks all the attributes of the
     * dinosaur and whether or not the requirements for it to breed are met, and returns the action for the dinosaur
     * to breed and if requirements not met returns null.
     * @param map the map that the actor is on
     * @return the action for the dinosaur to breed
     */
    public Action breeding(GameMap map){
        return new BreedingBehaviour().getAction(this, map);
    }

    /**
     * calls the required behaviours of the dinosaur for it to find Food, the behaviour checks all the attributes of the
     * dinosaur and whether or not the requirements for it to find Food are met, and returns the action for the dinosaur
     * to find Food and if requirements are not met returns null.
     * @param map the map that the actor is on
     * @param targetLocation the location that the dinosaur is going to find its food
     * @return the action for the dinosaur to find Food
     */
    public Action findFood(GameMap map, Location targetLocation){
        return new HungryBehaviour(targetLocation).getAction(this, map);
    }

    public Action drinks(GameMap map){
        return new ThirstyBehaviour().getAction(this, map);
    }

    @Override
    public Action findLake(GameMap map, Location targetLocation){
        return new HungryBehaviour(targetLocation).getAction(this, map);
    }

    public boolean isConsciousThirst() {
        return waterLevel > 0;
    }

    public void drinkRain(){
        if (!isConsciousThirst()){
            if (Sky.rainValue != 0){
                this.waterLevel = 10;
            }
        }
    }

    @Override
    public void drink(int waterValue){
        this.waterLevel += waterValue;
    }
}
