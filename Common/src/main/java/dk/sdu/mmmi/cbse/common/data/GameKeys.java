package dk.sdu.mmmi.cbse.common.data;

/**
 * GameData class responsible for storing the game data
 */
public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 4;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;


    /**
     * Constructor for the GameKeys class
     */
    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    /**
     * Method to update the keys
     */
    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    /**
     * Method to set the key
     * @param k int value containing the key
     * @param b boolean value indicating if the key is pressed
     */
    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    /**
     * Method to check if a key is down
     * @param k int value containing the key
     * @return boolean value indicating if the key is down
     */
    public boolean isDown(int k) {
        return keys[k];
    }

    /**
     * Method to check if a key is pressed
     * @param k int value containing the key
     * @return boolean value indicating if the key is pressed
     */
    public boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

}
