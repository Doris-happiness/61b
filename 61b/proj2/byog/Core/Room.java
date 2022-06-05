package byog.Core;


import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a square room in the game.
 * The room is determined by its position(x, y) and size(width, height), tile type for the floor and the walls
 * and the world state
 *
 */
public class Room {
    /* Position of the room in the world, lower left corner. */
    private int x;
    private int y;

    /* Size of the room, in tiles */
    private int width;
    private int height;

    /* Tile types fro the floor and the walls. */
    private TETile floor;
    private TETile wall;

    /* world in which the room is to be drawn. */
    private TETile[][] world;

    public Room(int x0, int y0, int w0, int h0, TETile floor0, TETile wall0, TETile[][] ws) {
        x = x0;
        y = y0;
        width = w0;
        height = h0;
        floor = floor0;
        wall = wall0;
        world = ws;

        if (x < 0) {
            throw new RuntimeException("Room out of bounds, x:" + x);
        }
        if (y < 0) {
            throw new RuntimeException("Room out of bounds, y:" + y);
        }
        if (world.length < x + width) {
            throw new RuntimeException("Room out of bounds, x:" + x + ", width:" + width);
        }
        if (world[0].length < y + height) {
            throw new RuntimeException("Room out of bounds, y:" + y + ", height:" + height);
        }
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public TETile floor() {
        return floor;
    }

    public TETile wall() {
        return wall;
    }

    public TETile[][] world() {
        return world;
    }

    /**
     * Takes two Room objects as parameters, and returns true if the body of objects overlaps on the x direction.
     * @param r1 is one of the rooms to check for overlapping along the x direction.
     * @param r2 is one of the rooms to check for overlapping along the x direction.
     * @return true if both rooms have point with common coordinates along x.
     */
    static boolean overlapOnX(Room r1, Room r2) {
        return (r1.x <= r2.x && r2.x <= (r1.x + r1.width - 1))
                || (r2.x <= r1.x && r1.x <= (r2.x + r2.width - 1));

    }

    /**
     * Takes two Room objects as parameters, and returns true if the body of both objects overlaps
     * on the y direction.
     * @param r1 is one of the rooms to check for overlapping along the y direction.
     * @param r2 is one of the rooms to check for overlapping along the y direction.
     * @return true if both rooms have point with common coordinates along y.
     */
    static boolean overlapOnY(Room r1, Room r2) {
        return (r1.y <= r2.y && r2.y <= r1.y + r1.height - 1)
                || (r2.y <= r1.y && r1.y <= r2.y + r2.height - 1);
    }

    public List<Position> getPoints() {
        List<Position> points = new ArrayList<>();
        for (int i = x; i < x + width; i += 1) {
            for (int j =y; j < y + height; j += 1) {
                points.add(new Position(i, j));
            }
        }
        return points;
    }

    /* Draw this Room on the world. */
    public void draw() {
        for (int i = x; i < x + width; i += 1) {
            for (int j = y; j < y + height; j += 1) {
                world[i][j] = floor;
            }
        }
    }




}
