package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
            /*Entity thisShooter = null;
            Collection<Entity> thisWorld = world.getEntities();
            for(Entity e : thisWorld) {
                if(e.getClass()== Player.class) {
                    thisShooter = e;
                }
            }
            world.addEntity(createBullet(thisShooter, gameData));

            MAYBE WRONG IMPLEMENTATION CONSIDERING NATURE OF COMPONENT BASED DEVELOPMENT?
            RESPONSIBILITY FOR PROCESSING DATA CONCERNING THE PLAYER AND ITS ACTIONS INCLUDING
            SHOOTING SHOULD BE IN PLAYERCONTROLSYSTEM?
            */

        for (Entity bullet : world.getEntities(Bullet.class)) {

            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);




        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {

            Entity bullet = new Bullet();
            bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
            bullet.setX(shooter.getX());
            bullet.setY(shooter.getY());
            bullet.setRotation(shooter.getRotation());
            bullet.setLifePoints(1);

        return bullet;
        }



}
