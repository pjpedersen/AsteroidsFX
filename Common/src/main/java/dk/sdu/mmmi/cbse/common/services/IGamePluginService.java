package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * Interface contract responsible for starting and stopping the game.
     * @param gameData
     * @param world
     */

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
