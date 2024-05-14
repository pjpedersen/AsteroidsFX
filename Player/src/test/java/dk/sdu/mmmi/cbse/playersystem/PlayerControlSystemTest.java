package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerControlSystemTest {

    private World world;
    private GameData gameData;
    private PlayerControlSystem playerControlSystem;
    private Player playerMock;
    private GameKeys gameKeys;

    @Before
    public void setUp() {
        world = mock(World.class);
        gameData = mock(GameData.class);
        gameKeys = mock(GameKeys.class);
        playerMock = mock(Player.class);
        playerControlSystem = new PlayerControlSystem();

        // Ensure the gameData returns the gameKeys
        when(gameData.getKeys()).thenReturn(gameKeys);

        // Ensure the world returns the player entity
        when(world.getEntities(Player.class)).thenReturn(Collections.singletonList(playerMock));
    }

    @Test
    public void testPlayerMovement() {
        // Create a mock Player entity
        when(playerMock.getX()).thenReturn(0.0);
        when(playerMock.getY()).thenReturn(0.0);
        when(playerMock.getRotation()).thenReturn(0.0);
        Mockito.doNothing().when(playerMock).setX(Mockito.anyDouble());
        Mockito.doNothing().when(playerMock).setY(Mockito.anyDouble());

        // Add the player to the world
        world.addEntity(playerMock);

        // Mock the GameKeys to simulate the UP key being pressed
        when(gameData.getKeys().isDown(GameKeys.UP)).thenReturn(true);

        // Call the process method to update the player's position
        playerControlSystem.process(gameData, world);

        // Capture the arguments passed to setX and setY
        ArgumentCaptor<Double> captorX = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> captorY = ArgumentCaptor.forClass(Double.class);
        verify(playerMock).setX(captorX.capture());
        verify(playerMock).setY(captorY.capture());

        // Assert the captured values
        assertEquals(1.0, captorX.getValue(), 0.01); // Expected to be 1.0 because of cos(0) = 1
        assertEquals(0.0, captorY.getValue(), 0.01); // Expected to be 0.0 because of sin(0) = 0
    }

    @Test
    public void testPlayerRotationLeft() {
        // Create a mock Player entity
        when(playerMock.getRotation()).thenReturn(0.0);
        Mockito.doNothing().when(playerMock).setRotation(Mockito.anyDouble());

        // Add the player to the world
        world.addEntity(playerMock);

        // Mock the GameKeys to simulate the LEFT key being pressed
        when(gameData.getKeys().isDown(GameKeys.LEFT)).thenReturn(true);

        // Call the process method to update the player's rotation
        playerControlSystem.process(gameData, world);

        // Capture the arguments passed to setRotation
        ArgumentCaptor<Double> captorRotation = ArgumentCaptor.forClass(Double.class);
        verify(playerMock).setRotation(captorRotation.capture());

        // Assert the captured value
        assertEquals(-5.0, captorRotation.getValue(), 0.01); // Expected to be -5.0 because of -5 degrees rotation
    }

    @Test
    public void testPlayerRotationRight() {
        // Create a mock Player entity
        when(playerMock.getRotation()).thenReturn(0.0);
        Mockito.doNothing().when(playerMock).setRotation(Mockito.anyDouble());

        // Add the player to the world
        world.addEntity(playerMock);

        // Mock the GameKeys to simulate the RIGHT key being pressed
        when(gameData.getKeys().isDown(GameKeys.RIGHT)).thenReturn(true);

        // Call the process method to update the player's rotation
        playerControlSystem.process(gameData, world);

        // Capture the arguments passed to setRotation
        ArgumentCaptor<Double> captorRotation = ArgumentCaptor.forClass(Double.class);
        verify(playerMock).setRotation(captorRotation.capture());

        // Assert the captured value
        assertEquals(5.0, captorRotation.getValue(), 0.01); // Expected to be -5.0 because of -5 degrees rotation
    }
}
