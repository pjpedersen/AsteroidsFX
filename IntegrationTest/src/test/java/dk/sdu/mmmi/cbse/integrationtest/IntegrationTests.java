package dk.sdu.mmmi.cbse.integrationtest;

import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.collision.CollisionControlSystem;
import dk.sdu.mmmi.cbse.common.asteroid.CommonAsteroidEntity;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.CommonEnemyEntity;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
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
    private PlayerControlSystem playerControlSystem;
    private AsteroidControlSystem asteroidControlSystem;
    private EnemyControlSystem enemyControlSystem;
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

        // Create control systems
        collisionControlSystem = new CollisionControlSystem();
        playerControlSystem = new PlayerControlSystem();
        asteroidControlSystem = new AsteroidControlSystem();
        enemyControlSystem = new EnemyControlSystem();

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

        // Mock the removeEntity method to remove from our local list
        doAnswer(invocation -> {
            Entity entity = invocation.getArgument(0);
            entities.remove(entity);
            return null;
        }).when(world).removeEntity(any(Entity.class));

        // Mock the getEntities method to return entities from our local list
        when(world.getEntities()).thenReturn(entities);
        when(world.getEntities(Player.class)).thenAnswer(invocation -> {
            List<Entity> players = new ArrayList<>();
            for (Entity entity : entities) {
                if (entity instanceof Player) {
                    players.add(entity);
                }
            }
            return players;
        });
        when(world.getEntities(CommonAsteroidEntity.class)).thenAnswer(invocation -> {
            List<Entity> asteroids = new ArrayList<>();
            for (Entity entity : entities) {
                if (entity instanceof CommonAsteroidEntity) {
                    asteroids.add(entity);
                }
            }
            return asteroids;
        });
        when(world.getEntities(CommonEnemyEntity.class)).thenAnswer(invocation -> {
            List<Entity> enemies = new ArrayList<>();
            for (Entity entity : entities) {
                if (entity instanceof CommonEnemyEntity) {
                    enemies.add(entity);
                }
            }
            return enemies;
        });

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

    @Test
    public void testComponentRemovalAtRuntime() {
        // Process game data with all control systems
        playerControlSystem.process(gameData, world);
        asteroidControlSystem.process(gameData, world);
        enemyControlSystem.process(gameData, world);
        collisionControlSystem.process(gameData, world);

        // Verify player entity is present
        Entity player = world.getEntities(Player.class).iterator().next();
        assertNotNull(player);

        // Remove player entity at runtime
        world.removeEntity(player);

        // Verify player entity is removed
        System.out.println("Entities after removal: " + world.getEntities());
        System.out.println("Player entities after removal: " + world.getEntities(Player.class));

        // Process game data again to ensure game continues to function
        playerControlSystem.process(gameData, world);
        asteroidControlSystem.process(gameData, world);
        enemyControlSystem.process(gameData, world);
        collisionControlSystem.process(gameData, world);

        // Verify player entity is removed
        assertTrue(world.getEntities(Player.class).isEmpty());

        // Verify other entities still exist and game is functional
        assertFalse(world.getEntities(CommonAsteroidEntity.class).isEmpty());
        assertFalse(world.getEntities(CommonEnemyEntity.class).isEmpty());
    }
}
