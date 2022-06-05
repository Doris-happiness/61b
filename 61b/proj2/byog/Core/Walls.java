package byog.Core;


import byog.TileEngine.TETile;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Walls
 * Represents a set of walls in the game, that are to be drawn as the limit of rooms and hallways.
 * When creating a Walls object, the type of TETile for the floor is needed in order for the walls to be able to generated.
 * Walls will not only be created around a set of points of the floor type.
 */
public class Walls {
    /* points List represents where each piece of wall is placed. */
    private final List<Position> points;

    /* gPoints (geometry points) List represents where points for rooms and walls are placed .*/
    private final List<Position> gPoints;
    private final List<Room> rooms;
    private final List<HallWay> hallWays;
    private final TETile[][] world;

    /* TETile that represent the floor for rooms and hallways in the world. */
    private final TETile floor;

    /* TETile that represent the wall for rooms and hallways in the world. */
    private final TETile wall;

    public Walls(Room[] roomsP, HallWay[] hallWaysP, TETile floorP, TETile wallP, TETile[][] worldP) {
        this.rooms = Arrays.asList(roomsP);
        this.hallWays = Arrays.asList(hallWaysP);
        this.floor = floorP;
        this.wall = wallP;
        this.world = worldP;
        this.points = new ArrayList<>();
        this.gPoints = new ArrayList<>();
        populateGeometryPoints();
        generateWalls();
    }


    /**
     * Populates List<Position> gPoints, which contains all the points corresponding to rooms and walls that are in the world.
     */
    private void populateGeometryPoints() {
        for (Room room : rooms) {
            List<Position> roomPoints = room.getPoints();
            for (Position p : roomPoints) {
                if (!gPoints.contains(p)) {
                    gPoints.add(p);
                }
            }
            for (HallWay hallway : hallWays) {
                List<HallWay> hallwayPoints = hallway.getPoints();
                for (Position p : hallwayPoints) {
                    if (!gPoints.contains(p)) {
                        gPoints.add(p);
                    }
                }
            }
        }
    }


    /**
     *  Generates all the walls for the world.
     *  First, sweep the world left-right and bottom-up, to add right and upper walls, and top-right corners.
     *  Second, sweep the world right-left and top-down, to add left and lower walls, and bottom-left corners.
     *   Third, sweep the world left-right and bottom-up, to add top-left and bottom-right corners.
     *
     */
    private void generateWalls() {
        /* 1-sweep left-right and bottom-up, draw right and upper walls, and top-right corners */
        for (int x = 1; x < world.length; x += 1) {
            for (int y = 1; y < world[x].length; y += 1) {
                Position current = new Position(x, y);
                Position down = new Position(x, y - 1);
                Position left = new Position(x - 1, y);
                Position leftDown = new Position(x - 1, y - 1);
                if (gPoints.contains(current)) {
                    continue;
                }
                if (gPoints.contains(left)) {
                    addWall(current);
                }
                if (gPoints.contains(down)) {
                    addWall(current);
                }
                if (gPoints.contains(leftDown) && points.contains(left) && points.contains(down)) {
                    addWall(current);
                }
            }
        }

        /* 2-sweep right-left and top-down, draw left and lower walls, and bottom-left corners */
        for (int x = world.length - 2; x >= 0; x -= 1) {
            for (int y = world[x].length - 2; y >= 0; y -= 1) {
                Position current = new Position(x, y);
                Position up = new Position(x, y + 1);
                Position right = new Position(x + 1, y);
                Position upRight = new Position(x + 1, y + 1);
                if (gPoints.contains(current) || points.contains(current)) {
                    continue;
                }
                if (gPoints.contains(right)) {
                    addWall(current);
                }
                if (gPoints.contains(up)) {
                    addWall(current);
                }
                if (gPoints.contains(upRight) && points.contains(right) && points.contains(up)) {
                    addWall(current);
                }
            }
        }

        /*  3-sweep left-right and bottom-up, draw top-left and bottom-right corners */
        for (int x = 1; x < world.length; x += 1) {
            for (int y = 1; y < world[x].length; y += 1) {
                Position current = new Position(x, y);
                Position down = new Position(x, y - 1);
                Position left = new Position(x - 1, y);
                Position leftDown = new Position(x - 1, y - 1);
                if (!points.contains(current)) {
                    continue;
                }
                if (gPoints.contains(down) && !gPoints.contains(left) && points.contains(leftDown)) {
                    addWall(left);
                }
                if (!gPoints.contains(down) && gPoints.contains(left) && points.contains(leftDown)) {
                    addWall(down);
                }
            }
        }



    }

    /**
     * Adds a wall, on the world, at the coordinates passed as parameter.
     * @param x is the x coordinate on which to add a single-tile wall.
     * @param y is the y coordinate on which to add a single-tile wall.
     */
    private void addWall(int x, int y) {
        Position p = new Position(x, y);
        points.add(p);
    }

    /**
     * Adds a wall, on the world, at the Position passed as parameter.
     * @param p the position on which to add a single-tile wall.
     */
    private void addWall(Position p) {
        points.add(p);

    }

    /**
     *  Return the Walls representation as a  List of Points, representing the set of coordinates occupied by the all the walls in the world.
     *  @return the set of points that the walls occupy.
     */
    public List<Position> getPoints() {
        return points;
    }


    /**
     * Draws the Walls on the world which they reference.
     */
    public void draw() {
        for (Position p : points) {
            world[p.x()][p.y()] = wall;
        }
    }



}
