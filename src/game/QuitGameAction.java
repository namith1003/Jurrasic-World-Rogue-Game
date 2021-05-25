package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * the action for the player to quit the entire game or the game mode that he is playing
 * depending on the game over variable
 */
public class QuitGameAction extends Action {
    /**
     * the boolean to show whether or not the player quits the whole game or only the game mode
     */
    static boolean gameOver;

    /**
     * constructor for the quit game action class
     * @param gameOver the boolean to show whether or not the player quits the whole game or only the game mode
     */
    public QuitGameAction(boolean gameOver) {
        QuitGameAction.gameOver = gameOver;
    }

    /**
     * execute the action to quit the game by removing the actor off of the map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Player Quit the Game Mode";
    }

    /**
     * shows the menu option for the player to quit the game on the menu
     * @param actor The actor performing the action.
     * @return the menu option for the player to quit the game on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Quit Game Mode";
    }
    
}
