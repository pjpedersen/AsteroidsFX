package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     *
     * Interface contract responsible for processing entities and their cycle.
     *
     * @param gameData
     * @param world
     * @throws
     */
    void process(GameData gameData, World world);
}
