package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * Interface contract responsible for creating bullets.
 */
public interface BulletSPI {

    /**
     * Method to create a bullet entity
     * @param e Entity object containing the entity
     * @param gameData GameData object containing the game data
     * @return Entity object containing the bullet entity
     */
    Entity createBullet(Entity e, GameData gameData);
}
