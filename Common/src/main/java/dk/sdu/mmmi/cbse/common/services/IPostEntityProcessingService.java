package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface contract responsible for processing entities post interaction.
 */
public interface IPostEntityProcessingService {

    /**
     * Method to process the entities post interaction
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    void process(GameData gameData, World world);
}
