package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * PlayerPlugin class responsible for starting and stopping Player module with entities
 */
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    /**
     * Method to start the Player module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     * Precondition: gameData and world are not null
     * The Player component starts and player entity has been created
     */
    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    /**
     * Method to create a player ship
     * @param gameData GameData object containing the game data
     * @return Entity object containing the player ship
     */
    private Entity createPlayerShip(GameData gameData) {

        int sizeUnit = 3;
        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setLifePoints(10);
        playerShip.setRadius(sizeUnit);

        return playerShip;
    }

    /**
     * Method to stop the Player module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     * Precondition: gameData and world are not null
     * Postcondition: The Player component stops and player entity has been removed
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
