package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;

/**
 * HallWay
 * Represents a generic hallway, a set of adjacent tiles in the world.
 */
public abstract class HallWay {
    protected TETile floor;
    /* Inner data types */

    /**
     * Represents a vertical(or horizontal) set of tiles forming a column (or row).
     */
    class Segment {
        private Position p;
        private Position q;
        private Position[] points;

        Segment(Position p0, Position q0) {
            if (Position.isAlignedX(p0, q0)) {
                if (p0.y() < q0.y()) {
                    p = p0;
                    q = q0;
                } else {
                    p = q0;
                    q = p0;
                }
                int numPoints = q.y() - p.y() + 1;
                points = new Position[numPoints];
                for (int i = 0; i < numPoints; i += 1) {
                    points[i] = new Position(p0.x(), p0.y() + i);
                }
            } else if (Position.isAlignedY(p0, q0)) {
                if (Position.isAlignedY(p0, q0)) {
                    if (p0.x() < q0.x()) {
                        p = p0;
                        q = q0;
                    } else {
                        p = q0;
                        q = p0;
                    }
                    int numPoints = q.x() - p.x() + 1;
                    points = new Position[numPoints];
                    for (int i = 0; i < numPoints; i += 1) {
                        points[i] = new Position(p.x() + i, p.y());
                    }
                } else {
                    throw new IllegalArgumentException("Trying to initialize " + getClass() + "with points " +
                            "that a not aligned on x nor y.");
                }

            }

        }

        /* class implementation */

        /* The set of segments that comprise the hallway. Must be initialized by subclasses. */
        protected Segment[] segments;
        // world in which the room is to be drawn.
        protected TETile[][] world;
        // Tile types for the floor and the walls.
        protected TETile floor;
        protected TETile wall;

        /**
         * This function must be called within the constructor, to initialize the Hallway object/
         *
         * @param worldP is the world on which the hallway is going to be drawn.
         * @param floorP is the type if TETile used for drawing the floor.
         * @param wallP  is the type if TETile used for drawing the walls.
         */
        abstract void initialize(TETile[][] worldP, TETile floorP, TETile wallP);


        /**
         * Returns true if this instance is a straight hallway.
         */
        public boolean isStraight() {
            return segments.length == 1;
        }


        /**
         * Returns true if this Hallway contains the Point passed as parameter.
         *
         * @param ps is the Point to be check is in this Hallway.
         * @return true if the point belongs to the hallway.
         */
        boolean contains(Position ps) {
            for (Segment seg : segments) {
                for (Position p : seg.points) {
                    if (ps.equals(p)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public List<Position> getPoints() {
            List<Position> points = new ArrayList<>();
            for (Segment seg : segments) {
                for (Position p : seg.points) {
                    points.add(p);
                }
            }
            return points;
        }

        /* Draw this hallway on the world. */
        public void draw() {
            for (Segment seg : segments) {
                for (Position p : points) {
                    world[p.x()][p.y()] = floor;
                }
            }
        }
    }
}
