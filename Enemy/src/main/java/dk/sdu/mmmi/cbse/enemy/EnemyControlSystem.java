package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {
    private World world;
    private GameData gameData;

    @Override
    public void process(GameData gameData, World world) {
        this.world = world;
        this.gameData = gameData;

        checkMovement();
        //shoot();

    }

    public void checkMovement() {

        for (Entity e : world.getEntities(Enemy.class)) {
            double randomInt = getRandomMovementInput(1, 3);
                if (randomInt == 1) {
                    e.setRotation(e.getRotation() + Math.random());
                }
                if (randomInt == 2) {
                    e.setRotation(e.getRotation() - Math.random());
                }
                if (randomInt == 3) {
                    double changeX = Math.cos(Math.toRadians(e.getRotation()));
                    double changeY = Math.sin(Math.toRadians(e.getRotation()));
                    e.setX(e.getX() + changeX);
                    e.setY(e.getY() + changeY);
                }
        }
    }


    public int getRandomMovementInput(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
