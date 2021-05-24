package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

import java.io.IOException;
import java.util.List;

public class LocationMaps extends GameMap {
    private int id;
    public LocationMaps(GroundFactory groundFactory, char groundChar, int width, int height, int id) {
        super(groundFactory, groundChar, width, height);
    }

    public LocationMaps(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    public LocationMaps(GroundFactory groundFactory, String mapFile) throws IOException {
        super(groundFactory, mapFile);
    }

    @Override
    public void tick() {
        super.tick();

    }
}
