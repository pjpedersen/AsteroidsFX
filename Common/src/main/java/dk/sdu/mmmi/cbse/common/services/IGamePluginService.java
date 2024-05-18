package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface contract responsible for starting and stopping the game plugin
 */
public interface IGamePluginService {

    /**
     * Method to start the game plugin
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     * Precondition: gameData and world are not null
     * Postcondition: The module has been started and the component is running
     */
    void start(GameData gameData, World world);

    /**
     * Method to stop the game plugin
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     * Precondition: gameData and world are not null
     * Postcondition: The module has been stopped and the module is no longer running
     */
    void stop(GameData gameData, World world);
}
