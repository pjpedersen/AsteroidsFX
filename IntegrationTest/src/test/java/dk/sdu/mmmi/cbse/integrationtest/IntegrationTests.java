package dk.sdu.mmmi.cbse.integrationtest;

import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.collision.CollisionControlSystem;
import dk.sdu.mmmi.cbse.common.asteroid.CommonAsteroidEntity;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.CommonEnemyEntity;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class IntegrationTests {

    private World world;
    private GameData gameData;
    private GameKeys gameKeys;
    private Entity player;
    private Entity asteroid;
    private Entity asteroidTwo;
    private Entity enemy;
    private CollisionControlSystem collisionControlSystem;
    private List<Entity> entities;

    @Before
    public void setUp() {
        // Mock the World
        world = mock(World.class);
        entities = new ArrayList<>();

        // Mock the GameData and GameKeys
        gameData = mock(GameData.class);
        gameKeys = mock(GameKeys.class);
        when(gameData.getKeys()).thenReturn(gameKeys);

        // Create controlsystems
        collisionControlSystem = new CollisionControlSystem();

        // Create and set up generic Entity instances
        player = mock(Player.class);
        when(player.getX()).thenReturn(0.0);
        when(player.getY()).thenReturn(0.0);
        when(player.getLifePoints()).thenReturn(100.0);
        when(player.getRadius()).thenReturn(5.0F);

        asteroid = mock(CommonAsteroidEntity.class);
        when(asteroid.getX()).thenReturn(10.0);
        when(asteroid.getY()).thenReturn(0.0);
        when(asteroid.getLifePoints()).thenReturn(100.0);
        when(asteroid.getRadius()).thenReturn(4.0F);

        asteroidTwo = mock(CommonAsteroidEntity.class);
        when(asteroidTwo.getX()).thenReturn(14.0);
        when(asteroidTwo.getY()).thenReturn(0.0);
        when(asteroidTwo.getLifePoints()).thenReturn(100.0);
        when(asteroidTwo.getRadius()).thenReturn(4.0F);

        enemy = mock(CommonEnemyEntity.class);
        when(enemy.getX()).thenReturn(2.0);
        when(enemy.getY()).thenReturn(0.0);
        when(enemy.getLifePoints()).thenReturn(100.0);
        when(enemy.getRadius()).thenReturn(3F);

        // Mock the addEntity method to add to our local list
        doAnswer(invocation -> {
            Entity entity = invocation.getArgument(0);
            entities.add(entity);
            return null;
        }).when(world).addEntity(any(Entity.class));

        // Mock the getEntities method to return entities from our local list
        when(world.getEntities()).thenReturn(entities);
        when(world.getEntities(Player.class)).thenReturn(Collections.singletonList(player));
        when(world.getEntities(CommonAsteroidEntity.class)).thenReturn(Collections.singletonList(asteroid));
        when(world.getEntities(CommonEnemyEntity.class)).thenReturn(Collections.singletonList(enemy));

        // Add entities to the mocked world
        world.addEntity(player);
        world.addEntity(asteroid);
        world.addEntity(enemy);
    }

    @Test
    public void testCollision() {
        assertTrue(world.getEntities().contains(player));
        assertTrue(world.getEntities().contains(asteroid));
        assertTrue(world.getEntities().contains(enemy));


        // Test collision between player and asteroid
        assertTrue(collisionControlSystem.checkCollision(player, enemy));

        assertTrue(collisionControlSystem.checkCollision(asteroid, asteroidTwo));
    }
}
