package byog.Core;

public class Position {
    private int x;
    private int y;

    public Position(int x0, int y0) {
        x = x0;
        y = y0;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    /**
     * Returns true if p and q are aligned on the x (horizontal) axis.
     */
    public static boolean isAlignedX(Position p, Position q) {
        return p.y == q.y;
    }

    /**
     * Return true if p and q are aligned on the y (vertical) axis.
     */
    public static boolean isAlignedY(Position p, Position q) {
        return p.x == q.x;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        return this.x == ((Position) o).x && this.y == ((Position) o).y;
    }

    @Override
    public int hashCode() {
        return x + y;
    }



}
