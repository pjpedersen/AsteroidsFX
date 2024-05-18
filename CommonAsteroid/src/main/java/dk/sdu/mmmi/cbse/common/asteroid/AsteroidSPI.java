package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * Interface contract responsible for creating and splitting asteroids.
 */
public interface AsteroidSPI {

    /**
     * Method to create an asteroid entity
     * @param gameData GameData object containing the game data
     * @return Entity object containing the asteroid entity
     */
    Entity createAsteroid(GameData gameData);

    /**
     * Method to split an asteroid entity
     * @param entity Entity object containing the asteroid entity
     */
    void splitAsteroid(Entity entity);
}
