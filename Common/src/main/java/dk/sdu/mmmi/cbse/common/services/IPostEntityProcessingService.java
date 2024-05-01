package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    /**
     * Interface contract responsible for processing entities post interaction.
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
