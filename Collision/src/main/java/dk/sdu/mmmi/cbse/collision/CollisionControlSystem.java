package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 * CollisionControlSystem class responsible for handling collision between entities
 */
public class CollisionControlSystem implements IPostEntityProcessingService {

    /**
     * Method to process the collision between entities
     * @param gameData GameData object containing the game data
     * @param world World object containing the game world
     */
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

    /**
     * Method to deal damage to entities
     * @param entity1 Entity object containing the first entity
     * @param entity2 Entity object containing the second entity
     */
    public void dealDamage(Entity entity1, Entity entity2) {
        entity1.setLifePoints(entity2.getLifePoints() - 1);
        entity2.setLifePoints(entity1.getLifePoints() - 1);
    }

    /**
     * Method to check if entities are colliding
     * @param entity1 Entity object containing the first entity
     * @param entity2 Entity object containing the second entity
     * @return boolean value indicating if the entities are colliding
     */
    public boolean checkCollision(Entity entity1, Entity entity2) {
        double x1 = entity1.getX();
        double y1 = entity1.getY();
        double x2 = entity2.getX();
        double y2 = entity2.getY();
        double dx = x2 - x1;
        double dy = y2 - y1;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance <=  (entity1.getRadius() + entity2.getRadius());
    }
}
