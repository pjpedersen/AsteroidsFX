package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.asteroid.CommonAsteroidEntity;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class GameLoop {


    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IGamePluginService> iGamePluginServices;
    private final List<IEntityProcessingService> iEntityProcessingServices;
    private final List<IPostEntityProcessingService> iPostEntityProcessingServices;
    private final URI scoreEndpointUrl;
    private final HttpClient requestClient;
    private Text scoreCounter;


    public GameLoop(List<IGamePluginService> iGamePluginServices, List<IEntityProcessingService> iEntityProcessingServices, List<IPostEntityProcessingService> iPostEntityProcessingServices) {
        this.iGamePluginServices = iGamePluginServices;
        this.iEntityProcessingServices = iEntityProcessingServices;
        this.iPostEntityProcessingServices = iPostEntityProcessingServices;
        //Setup base API url and httpclient for requests
        try {
            this.scoreEndpointUrl = new URI("http://localhost:8080/");
            requestClient = HttpClient.newHttpClient();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(Stage window) throws Exception {
        scoreCounter = new Text(10, 20, "Destroyed asteroids: 0");
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(scoreCounter);

        Scene scene = new Scene(gameWindow);

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        render();

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();

    }

    public void render() {
        new AnimationTimer() {
            private long then = 0;

            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
                removeUnusedDraws();
            }

        }.start();
    }

    private void update() {

        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }

        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }

        updateScoreCounter();
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);

            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }

            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }

    }

    private void removeUnusedDraws() {
        for (Entity entity : polygons.keySet()) {
            if (!world.getEntities().contains(entity)) {
                if(gameWindow.getChildren().contains(polygons.get(entity))) {
                    gameWindow.getChildren().remove(polygons.get(entity));
                }
                polygons.remove(entity);

                if(entity instanceof CommonAsteroidEntity) {
                    incrementScore();
                    updateScoreCounter();
                }
            }
        }
    }

    private long getScore() {
        return requestClient.sendAsync(java.net.http.HttpRequest.newBuilder()
                .uri(scoreEndpointUrl.resolve("score"))
                .GET()
                .build(), java.net.http.HttpResponse.BodyHandlers.ofString())
                .thenApply(java.net.http.HttpResponse::body)
                .thenApply(Long::parseLong)
                .join();
    }

    private void incrementScore() {
        requestClient.sendAsync(java.net.http.HttpRequest.newBuilder()
                .uri(scoreEndpointUrl.resolve("score/increment"))
                .PUT(java.net.http.HttpRequest.BodyPublishers.noBody())
                .build(), java.net.http.HttpResponse.BodyHandlers.discarding())
                .join();
    }

    private void updateScoreCounter() {
        scoreCounter.setText("Destroyed asteroids: " + getScore());
    }


    //instead of using serviceloader directly, we use the list of services we got from the dependency injection via spring configuration

    private Collection<? extends IGamePluginService> getPluginServices() {
        return iGamePluginServices;
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return iEntityProcessingServices;
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return iPostEntityProcessingServices;
    }
}