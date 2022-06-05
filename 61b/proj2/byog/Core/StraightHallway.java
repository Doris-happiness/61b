package byog.Core;


import byog.TileEngine.TETile;

/**
 * StraightHallway
 * Represents a straight hallway, either vertical or horizontal.
 */
public class StraightHallway extends HallWay{
    private Position p1;
    private Position p2;

    public StraightHallway(Position pt1, Position pt2, TETile[][] world, TETile floor, TETile wall) {
        if (pt1 == null || pt2 == null || world == null || floor == null || wall == null) {
            throw new IllegalArgumentException("Trying to initialize " + getClass() +
                    " with null arguments.");
        }
        p1 = pt1;
        p2 = pt2;
        Segment[] segments = new Segment[1];
        segments[0] = new Segment(p1, p2);
        initialize(world, floor, wall);
    }


    void initialize(TETile[][] world, TETile floor, TETile wall) {
        this.world = world;
        this.floor = floor;
        this.wall = wall;
    }

}
