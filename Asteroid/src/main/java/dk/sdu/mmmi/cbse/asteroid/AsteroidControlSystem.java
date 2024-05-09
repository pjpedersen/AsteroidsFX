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
        moveAsteroids();
    }

    @Override
    public Entity createAsteroid(GameData gameData) {
        int sizeUnit = 10;
        double randomX = getRandomInput(1, gameData.getDisplayWidth());
        double randomY = getRandomInput(1, gameData.getDisplayHeight());
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
        asteroid.setX(randomX);
        asteroid.setY(randomY);
        asteroid.setLifePoints(3);
        asteroid.setRadius(sizeUnit);
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

    public void moveAsteroids() {

        for (Entity e : world.getEntities(Asteroid.class)) {
            if (e.getX() < 0) {
                e.setX(1);
            }

            if (e.getX() > gameData.getDisplayWidth()) {
                e.setX(gameData.getDisplayWidth()-1);
            }

            if (e.getY() < 0) {
                e.setY(1);
            }


                    double changeX = Math.cos(Math.toRadians(e.getRotation()));
                    double changeY = Math.sin(Math.toRadians(e.getRotation()));
                    e.setX(e.getX() + changeX);
                    e.setY(e.getY() + changeY);

        }
    }

    public int getRandomInput(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }



}
