package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemyShip;
    @Override
    public void start(GameData gameData, World world) {
        enemyShip = createEnemyShip(gameData);
        world.addEntity(enemyShip);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    private Entity createEnemyShip(GameData gameData) {

        int sizeUnit = 10;

        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight()/4);
        enemyShip.setY(gameData.getDisplayWidth()/4);
        enemyShip.setLifePoints(10);
        enemyShip.setRadius(sizeUnit);
        return enemyShip;
    }
}
