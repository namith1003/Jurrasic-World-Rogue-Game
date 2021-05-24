package game;

import edu.monash.fit2099.engine.*;

/**
 * Terrain type to allow user to move from one map to another.
 */
public class SwitchMap extends Location {

    private GameMap map;
    private int x;
    private int y;

    /**
     * Constructor.
     * <p>
     * Locations know which map they are part of, and where.
     *
     * @param map the map that contains this location
     * @param x   x coordinate of this location within the map
     * @param y   y coordinate of this location within the map
     */
    public SwitchMap(GameMap map, int x, int y) {
        super(map, x, y);
        this.map = map;
        this.x = x;
        this.y = y;

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public MoveActorAction getMoveAction(Actor actor, String direction, String hotKey) {

        return new MoveActorAction(map.at(x,y), "north");
    }


}
