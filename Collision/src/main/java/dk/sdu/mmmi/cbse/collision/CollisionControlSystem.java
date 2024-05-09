package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;


public class CollisionControlSystem implements IPostEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for(Entity entityInner : world.getEntities()) {
                if (entity != entityInner) {

                if (checkCollision(entity, entityInner)) {
                        dealDamage(entity, entityInner);
                        if (entity.getLifePoints() <= 0) {
                            world.removeEntity(entity);
                        }
                        if (entityInner.getLifePoints() <= 0) {
                            world.removeEntity(entityInner);
                        }
                    }
                }
            }

        }
    }

    public void dealDamage(Entity entity1, Entity entity2) {
        entity1.setLifePoints(entity2.getLifePoints() - 1);
        entity2.setLifePoints(entity1.getLifePoints() - 1);
    }

    public boolean checkCollision(Entity entity1, Entity entity2) {
        double x1 = entity1.getX();
        double y1 = entity1.getY();
        double x2 = entity2.getX();
        double y2 = entity2.getY();
        double dx = x2 - x1;
        double dy = y2 - y1;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance <= 0.5;
    }
}
