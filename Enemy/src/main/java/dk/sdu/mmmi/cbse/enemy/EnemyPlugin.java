package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.CommonEnemyEntity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * EnemyPlugin class responsible for starting and stopping Enemy module with entities
 */
public class EnemyPlugin implements IGamePluginService {

    private Entity enemyShip;

    /**
     * Method to start the Enemy module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    @Override
    public void start(GameData gameData, World world) {
        enemyShip = createEnemyShip(gameData);
        world.addEntity(enemyShip);
    }

    /**
     * Method to stop the Enemy module
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemyShip);
    }

    /**
     * Method to create an enemy ship
     * @param gameData GameData object containing the game data
     * @return Entity object containing the enemy ship
     */
    private Entity createEnemyShip(GameData gameData) {


        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight()/4);
        enemyShip.setY(gameData.getDisplayWidth()/4);
        enemyShip.setLifePoints(10);
        enemyShip.setRadius(8);
        return enemyShip;
    }
}
