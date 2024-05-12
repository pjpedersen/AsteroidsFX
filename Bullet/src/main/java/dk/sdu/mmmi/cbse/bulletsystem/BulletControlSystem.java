package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.CommonBulletEntity;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

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

        for (Entity bullet : world.getEntities(CommonBulletEntity.class)) {

            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);

            if(bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }



        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {

            Entity bullet = new CommonBulletEntity();
            bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
            double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
            double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
            bullet.setX(shooter.getX() + changeX * 10);
            bullet.setY(shooter.getY() + changeY * 10);
            bullet.setRotation(shooter.getRotation());
            bullet.setRadius(1);
            bullet.setLifePoints(20);


        return bullet;
        }



}
