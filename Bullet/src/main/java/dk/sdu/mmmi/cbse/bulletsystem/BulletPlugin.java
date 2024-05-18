package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.CommonBulletEntity;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * BulletPlugin responsible for starting and stopping Bullet module with entities
 */
public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    /**
     * Method to start the Bullet module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    @Override
    public void start(GameData gameData, World world) {

    }

    /**
     * Method to stop the Bullet module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == CommonBulletEntity.class) {
                world.removeEntity(e);
            }
        }
    }

}
