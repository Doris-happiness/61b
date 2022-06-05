package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {
    private static final int WIDTH = 72;
    private static final int HEIGHT = 30;
    private static final int MAXW = 8;
    private static final int MAXH = 8;
    private static final int MAX_OFFSET = 3;
    private static final Room.Side ENTRY_SIDE = Room.Side.BOTTOM;
    private static final TETile ENTRY_TILE = Tileset.LOCKED_DOOR;
    private static final TETile EXIT_TILE = Tileset.FLOOR;

    public static final TERenderer ter = new TERenderer();

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        MapGeneratorParameters mgp = MapGeneratorParameters.defaultParameters();
        ter.initialize(mgp.width, mgp.height);
        WorldState ws = MapGenerator.generate(mgp);
        ter.renderFrame(ws.terrainGrid());
    }

    private static class MapGeneratorParameters {

    }

    private class WorldState {
        TETile[][] world;
    }

    public WorldState generator(MapGeneratorParameters mgp) {

    }

}
