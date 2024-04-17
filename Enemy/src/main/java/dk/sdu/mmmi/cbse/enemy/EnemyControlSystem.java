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

public class EnemyControlSystem implements IEntityProcessingService {
    private World world;
    private GameData gameData;

    @Override
    public void process(GameData gameData, World world) {
        this.world = world;
        this.gameData = gameData;

        checkMovement();
        shoot();

    }

    public void checkMovement() {

        for (Entity e : world.getEntities(Enemy.class)) {
            if (e.getX() < 0) {
                e.setX(1);
            }

            if (e.getX() > gameData.getDisplayWidth()) {
                e.setX(gameData.getDisplayWidth()-1);
            }

            if (e.getY() < 0) {
                e.setY(1);
            }

            if (e.getY() > gameData.getDisplayHeight()) {
                e.setY(gameData.getDisplayHeight()-1);
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


    public int getRandomInput(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void shoot() {

        int randomInt = getRandomInput(0,3);

        for (Entity e : world.getEntities(Enemy.class)) {
            if (randomInt == 1) {
                System.out.println("enemy shooting");
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {
                            world.addEntity(spi.createBullet(e, gameData));
                        }
                );
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
