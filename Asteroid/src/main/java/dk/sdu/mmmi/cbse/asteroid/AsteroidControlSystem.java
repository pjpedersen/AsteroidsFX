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
        checkForSplit();
    }

    @Override
    public Entity createAsteroid(GameData gameData) {
        int sizeUnit = 10;
        double randomX = getRandomInput(1, gameData.getDisplayWidth());
        double randomY = getRandomInput(1, gameData.getDisplayHeight());
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-10,-10,10,-10,10,10,-10,10);
        asteroid.setX(randomX);
        asteroid.setY(randomY);
        asteroid.setLifePoints(101);
        asteroid.setRadius(sizeUnit);
        return asteroid;
    }

    @Override
    public void splitAsteroid(Entity entity) {
        if(!((Asteroid)entity).isSplit()) {
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
            ((Asteroid)asteroid1).setSplit(true);
            ((Asteroid)asteroid2).setSplit(true);
            world.removeEntity(entity);
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);

        }
    }

    public void checkForSplit() {
        for(Entity e : world.getEntities(Asteroid.class)) {
            if(e.getLifePoints() <= 100) {
                splitAsteroid(e);
            }
        }
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

    public int getRandomInput(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }



}
