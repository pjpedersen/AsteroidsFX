package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {

    private World world;
    private GameData gameData;

    @Override
    public void process(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
        addAsteroid(gameData, world);
    }

    @Override
    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
        asteroid.setX(gameData.getDisplayHeight()/3);
        asteroid.setY(gameData.getDisplayWidth()/3);
        asteroid.setLifePoints(3);
        return asteroid;
    }

    public int checkSpawnedAsteroids() {
        int asteroidCounter = 0;
        for(Entity e : world.getEntities()) {
            if(e.getClass() == Asteroid.class) {
                asteroidCounter++;
            }
            //System.out.println("asteroidCounter = "+asteroidCounter); DEBUGGING COUNTER
        }
        return asteroidCounter;
    }

    public void addAsteroid(GameData gameData, World world) {
        if (checkSpawnedAsteroids() < 5) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }


}
