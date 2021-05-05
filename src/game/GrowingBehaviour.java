package game;

import edu.monash.fit2099.engine.*;

public class GrowingBehaviour implements Behaviour{
    private int turnCounter = 0;
    public GrowingBehaviour(){};

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        // still WIP!!
        return null;
    }
}
