package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;
import java.util.List;

/**
 * RandomWorldGenerator
 * Class to generate random world consisting of rooms, hallways, and walls(that set boundaries).
 * Can generate: Rooms, Hallways(that connects Rooms), and Walls.
 * The order in which the objects must be generated is: Rooms, Hallways, Walls.
 * Usage:
 *  RandomWorldGenerator rwg = new RandomWorldGenerator(world, Tileset.FLOOR, Tileset.WALL, new Random());
 *  Room[] rooms = rwg.generateRooms(5, 6, 1, 13);
 *  Hallway[] hallways = rwg.generateHallways(rooms);
 *  Walls walls = rwg.generateWalls(rooms, hallways);
 */
public class RandomWorldGenerator {
    /* The width of the walls in number of tiles. */
    private static final int WALL_SIZE = 1;

    /* World on which the room is to be generated. */
    private TETile[][] world;

    /* Tile types for the floor and the walls. */
    private TETile floor;
    private TETile wall;
    /* Random number generator from java.util. */
    private Random random;

    public RandomWorldGenerator(TETile[][] worldP, TETile floorP, TETile wallP, Random randomP) {
        this.world = worldP;
        this.floor = floorP;
        this.wall = wallP;
        this.random = randomP;
    }


    /**
     * Generates random rooms in the world.
     * No collision detection between rooms, which means that room floors can be overlapping.
     * @param sideMin is the minimum dimension for the width or height of a room.
     * @param sideMax is the maximum dimension for the width or height of a room.
     * @param deltaWH is the maximum allowable difference between width and height of any room.
     * @param n the number of rooms to draw.
     * @return a List of rooms that might overlap.
     */
    public List<Room> generateRooms(int sideMin, int sideMax, int deltaWH, int n) {
        if (sideMax + deltaWH + 2 * WALL_SIZE >= world.length
         || sideMax + deltaWH + 2 * WALL_SIZE >= world[0].length) {
            throw new RuntimeException("Room size is too big for world size.");
        }
        int[] widths = randomUniformArray(sideMin, sideMax + 1, n);
        int[] heights = randomUniformArray()
    }


    /**
     * Generates random rooms, that don't overlap, in the world.
     * Collision detection between rooms, which means that room floors won't overlap.
     * Every room will be tried to be added a fixed number of times, if fails, won't be added.
     * As some rooms may not be added, the total number of rooms might be lower than n.
     * @param sideMin is the minimum dimension for the width or height of a room.
     * @param sideMax is the maximum dimension for the width or height of a room.
     * @param deltaWH is the maximum allowable difference between the width and height of any room.
     * @param n the maximum number of rooms to draw (actual number may be lower due to collisions).
     * @param numTries is the number of times that each room will be tried to be added.
     * @return a List of rooms that don't overlap.
     */
    public List<Room> generateRoomsNoOverlap(int sideMin, int sideMax, int deltaWH, int n, int numTries) {

    }

    /**
     * Generates random straight and bent hallways to connect the rooms passed as parameter.
     * First shuffle rooms passed as parameter, to add randomness in connections.
     * Then connects rooms one by one.If n rooms, makes n - 1 connections.
     * @param rooms the Room instance to connect among.
     * @return an array of hallways connecting the rooms passed as parameter.
     */
    public List<HallWay> generateHallWays(List<Room> rooms) {

    }




}
