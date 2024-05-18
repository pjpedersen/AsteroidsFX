package dk.sdu.mmmi.cbse.common.data;

/**
 * GameData class responsible for storing the game data
 */
public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();


    /**
     * Method to get the keys of the game
     * @return GameKeys object containing the keys of the game
     */
    public GameKeys getKeys() {
        return keys;
    }

    /**
     * Method to set the width of the display
     * @param width int value containing the width of the display
     */
    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    /**
     * Method to get the width of the display
     * @return int value containing the width of the display
     */
    public int getDisplayWidth() {
        return displayWidth;
    }

    /**
     * Method to set the height of the display
     * @param height int value containing the height of the display
     */
    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    /**
     * Method to get the height of the display
     * @return int value containing the height of the display
     */
    public int getDisplayHeight() {
        return displayHeight;
    }


}
