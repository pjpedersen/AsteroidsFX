package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface contract responsible for processing entities and their cycle.
 */

public interface IEntityProcessingService {

    /**
     * Method to process the entities
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     * Precondition: gameData and world are not null
     * Postcondition: The entities have been processed
     */
    void process(GameData gameData, World world);
}
