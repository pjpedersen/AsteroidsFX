package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.CommonAsteroidEntity;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
* AsteroidPlugin class responsible for starting and stopping the Asteroid module with entities
 */


public class AsteroidPlugin implements IGamePluginService {
    /**
        * Method to start the Asteroid module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    @Override
    public void start(GameData gameData, World world) {

    }

    /**
     * Method to stop the Asteroid module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == CommonAsteroidEntity.class) {
                world.removeEntity(e);
            }
        }
    }
}
