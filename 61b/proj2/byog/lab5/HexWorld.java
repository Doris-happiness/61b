package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public void addHexagon(int startX, int startY, int size) {
        TETile[][] world = addHexagonHelper(startX, startY, size);
        addHexagonDrawerHerlper(world);
    }
    private TETile[][] addHexagonHelper(int x, int y, int size) {
        int width = size + 2 * (size - 1);
        int hight = size * 2;
        TETile[][] world = new TETile[width][hight];
        for (int i = 0; i < hight; i += 1) {
            int count = 0;
            int start = 0;
            if (i < size) {
                count = size + 2 * i;
                start = size - i - 1;
            } else {
                count = width - (i - size) * 2;
                start = i - size;
            }
            for (int j = start; j < count; j += 1) {
                world[i][j] = Tileset.FLOWER;
            }
        }
        return world;
    }

    private void addHexagonDrawerHerlper(TETile[][] world) {
        TERenderer ter = new TERenderer();
        ter.initialize(30, 40);
        ter.renderFrame(world);
    }

}
