package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    public static TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 50;
    public static final int HEIGHT = 30;

    private static boolean isSaved;
    private static TETile[][] finalWorldFrame;
    private static long SEED;
    private static Random RANDOM;

    /** Picks a RANDOM tile with a 25% change of being
     *  a wall, 25% chance of being a floor, and 25%
     *  chance of being unlocked_door, 25% chance of being nothing.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOOR;
            //case 2: return Tileset.NOTHING;
            //case 3: return Tileset.LOCKED_DOOR;
            default: return Tileset.NOTHING;
        }
    }

    public static void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = randomTile();
            }
        }
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        // judge whether the input has been saved or not. if has not been saved, make the finalWorldFrame to null
        // if has been saved, we need to modify the return results based on the saved 2D TETile[][].
        if (!isSaved) {
            finalWorldFrame = null;
        }

        int i = 0;
        int len = input.length();
        int num = 0;

        // handle the string input 'n' or 'N'
        while (i < len) {
            if (input.charAt(i) == 'n' || input.charAt(i) == 'N') {
                i += 1;
                break;
            }
            i += 1;
        }
        // handle the string input between 'n' and number
        while (i < len) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                i += 1;
                continue;
            } else break;

        }

        // handler the input string of number
        while (i < len) {
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                num = num * 10 + input.charAt(i);
                i += 1;
            } else {
                break;
            }
        }

        // handler the tail of the string input, if it has the ":q", then the isSaved = true
        if (input.charAt(len - 1) == 'q' && input.charAt(len - 2) == ':') {
            isSaved = true;
        }

        // generate the random worldFrame
        SEED = num;
        RANDOM = new Random(SEED);
        ter.initialize(WIDTH, HEIGHT);
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        fillWithRandomTiles(finalWorldFrame);
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    public static void main(String[] args) {
        playWithInputString("n4132sss");
    }
}
