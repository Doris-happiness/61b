package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.*;
import java.util.Random;

public class HexWorldSolution {
    private class Position {
        int x = 0;
        int y = 0;
        public Position(int xPos, int yPos) {
            x = xPos;
            y = yPos;
        }

    }

    /**
     * Computes the width of row i for a size hexagon.
     * @param size The size of the hex.
     * @param i The row number
     * @return
     */
    public static int hexRowWidth(int size, int i) {
        int effectiveI = i;
        if (i >= size) {
            effectiveI = 2 * size - 1 - effectiveI;
        }
        return size + 2 * effectiveI;
    }

    /**
     * Computes x coordinate of the leftmost tile in the ith row of a hexagon.
     * e.g. if size = 3, and i = 2, this funtion returns -2(比起最bottom的那一行，向左偏移了2个)
     *   xxxx
     *  xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     *  xxxxxx
     *   xxxx
     *
     * @param size The size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexRowOffset(int size, int i) {
        int effectiveI = i;
        if (i >= size) {
            effectiveI = 2 * size - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /**
     * Add a row of the same tile
     * @param world The world to draw on
     * @param p the Leftmost position of the row
     * @param width The number of tiles wide to draw
     * @param t The tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord =p.y;
            Random r = new Random();
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, r);
        }
    }

    public void addHexagon(TETile[][] world, Position p, int size, TETile t) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        // hexagons have 2*size rows. This code iterates up from the bottom row which we call row0.
        for (int yi = 0; yi < 2 * size; yi += 1) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(size, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(size, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }
}



