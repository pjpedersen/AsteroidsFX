package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.CommonAsteroidEntity;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *  * AsteroidControlSystem class responsible for controlling Asteroid module logic
 */

public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {

    private World world;
    private GameData gameData;

    @Override
    public void process(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
        addAsteroid(gameData, world);
        moveAsteroids();
        checkForSplit();
    }

    /**
     * Creates an asteroid entity with a random position and fixed size.
     * @param gameData display width and height
     * @return created asteroid entity
     */
    @Override
    public Entity createAsteroid(GameData gameData) {
        int sizeUnit = 10;
        double randomX = getRandomInput(1, gameData.getDisplayWidth());
        double randomY = getRandomInput(1, gameData.getDisplayHeight());
        Entity asteroid = new CommonAsteroidEntity();
        asteroid.setPolygonCoordinates(-10,-10,10,-10,10,10,-10,10);
        asteroid.setX(randomX);
        asteroid.setY(randomY);
        asteroid.setLifePoints(101);
        asteroid.setRadius(sizeUnit);
        return asteroid;
    }

    /**
     * Splits an asteroid entity into two smaller asteroids.
     * @param entity
     */
    @Override
    public void splitAsteroid(Entity entity) {
        if(!((CommonAsteroidEntity)entity).isSplit()) {
            Entity asteroid1 = createAsteroid(gameData);
            Entity asteroid2 = createAsteroid(gameData);
            asteroid1.setX(entity.getX()+10);
            asteroid1.setY(entity.getY()+10);
            asteroid2.setX(entity.getX()+20);
            asteroid2.setY(entity.getY()+20);
            asteroid1.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
            asteroid2.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
            asteroid1.setRadius(5);
            asteroid2.setRadius(5);
            ((CommonAsteroidEntity)asteroid1).setSplit(true);
            ((CommonAsteroidEntity)asteroid2).setSplit(true);
            world.removeEntity(entity);
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);
        }
    }

    /**
     * Checks if an asteroid entity has less than or equal to 100 life points.
     * If true, the asteroid entity is split into two smaller asteroids.
     */
    public void checkForSplit() {
        for(Entity e : world.getEntities(CommonAsteroidEntity.class)) {
            if(e.getLifePoints() <= 100) {
                splitAsteroid(e);
            }
        }
    }

    /**
     * Checks the number of spawned asteroids in the world.
     * @return number of spawned asteroids
     */
    public int checkSpawnedAsteroids() {
        int asteroidCounter = 0;
        for(Entity e : world.getEntities()) {
            if(e.getClass() == CommonAsteroidEntity.class) {
                asteroidCounter++;
            }
            //System.out.println("asteroidCounter = "+asteroidCounter); DEBUGGING COUNTER
        }
        return asteroidCounter;
    }

    /**
     * Adds an asteroid entity to the world if the number of spawned asteroids is less than 5.
     * @param gameData display width and height
     * @param world containing all entities
     */
    public void addAsteroid(GameData gameData, World world) {
        if (checkSpawnedAsteroids() < 5) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    /**
     * Moves all asteroid entities in the world.
     */
    public void moveAsteroids() {

        for (Entity e : world.getEntities(CommonAsteroidEntity.class)) {
            if (e.getX() <= 0) {
                e.setX(gameData.getDisplayWidth()-1);
            }

            if (e.getX() >= gameData.getDisplayWidth()) {
                e.setX(1);
            }

            if (e.getY() <= 0) {
                e.setY(gameData.getDisplayHeight()-1);
            }
            if (e.getY() >= gameData.getDisplayHeight()) {
                e.setY(1);
            }
                    double changeX = Math.cos(Math.toRadians(e.getRotation()));
                    double changeY = Math.sin(Math.toRadians(e.getRotation()));
                    e.setX(e.getX() + changeX);
                    e.setY(e.getY() + changeY);

        }
    }

    /**
     * Generates a random integer between a minimum and maximum value.
     * @param min minimum value
     * @param max maximum value
     * @return random integer
     */
    public int getRandomInput(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }



}
