package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * World class responsible for storing the entities in the game
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    /**
     * Method to add an entity to the world
     * @param entity Entity object to be added to the world
     * @return String containing the ID of the entity
     */
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    /**
     * Method to remove an entity from the world
     * @param entityID String containing the ID of the entity to be removed
     */
    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    /**
     * Method to remove an entity from the world
     * @param entity Entity object to be removed
     */
    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    /**
     * Method to get all entities in the world
     * @return Collection of Entity objects containing all entities in the world
     */
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    /**
     * Method to get all entities of a specific type in the world
     * @param entityTypes Class array containing the types of entities to be retrieved
     * @param <E> Generic type extending Entity
     * @return List of Entity objects containing all entities of the specified types
     */
    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    /**
     * Method to return entity based on ID
     * @param ID of entity
     * @return entity based on ID
     */
    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}
