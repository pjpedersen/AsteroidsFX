package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


/**
 * EnemyControlSystem class responsible for controlling Enemy module logic
 */

public class EnemyControlSystem implements IEntityProcessingService {
    private World world;
    private GameData gameData;

    /**
     * Method to process the entities
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     * Precondition: gameData and world are not null
     * Postcondition: The entities have been processed
     */
    @Override
    public void process(GameData gameData, World world) {
        this.world = world;
        this.gameData = gameData;

        checkMovement();
        shoot();

    }

    /**
     * Method to check the movement of the entities
     */
    public void checkMovement() {
        for (Entity e : world.getEntities(Enemy.class)) {
            if (e.getX() < 10) {
                e.setX(11);
            }
            if (e.getX() > gameData.getDisplayWidth()-10) {
                e.setX(gameData.getDisplayWidth()-11);
            }
            if (e.getY() < 10) {
                e.setY(11);
            }
            if (e.getY() > gameData.getDisplayHeight()-10) {
                e.setY(gameData.getDisplayHeight()-11);
            }
            double randomInt = getRandomInput(1, 4);
                if (randomInt == 1) {
                    e.setRotation(e.getRotation() + 5);
                }
                if (randomInt == 2) {
                    e.setRotation(e.getRotation() - 5);
                }
                if (randomInt == 3) {
                    double changeX = Math.cos(Math.toRadians(e.getRotation()));
                    double changeY = Math.sin(Math.toRadians(e.getRotation()));
                    e.setX(e.getX() + changeX);
                    e.setY(e.getY() + changeY);
                }
        }
    }

    /**
     * Method to get a random input
     * @param min minimum value
     * @param max maximum value
     * @return random input
     */
    public int getRandomInput(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Method to shoot
     */
    public void shoot() {

        int randomInt = getRandomInput(0,50);

        for (Entity e : world.getEntities(Enemy.class)) {
            if (randomInt == 1) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {
                            world.addEntity(spi.createBullet(e, gameData));
                        }
                );
            }
        }
    }

    /**
     * Method to get the bullet SPIs
     * @return Collection of BulletSPI
     */
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
