package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitGameAction extends Action {
    static boolean gameOver;

    public QuitGameAction(boolean gameOver) {
        QuitGameAction.gameOver = gameOver;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Player Quit the Game Mode";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Quit Game Mode";
    }
    
}
