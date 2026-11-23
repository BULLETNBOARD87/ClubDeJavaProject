package com.mycompany.clubdejava;



import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import com.almasb.fxgl.entity.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Club De Java: a turn-based, top-down golf game.
 */
public class App extends GameApplication {
  
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    
    private static int INITIAL_X = 0;
    private static int INITIAL_Y = 0;
    
    private static final double RADIUS = 20;
    private static final double POWER = 4.0;
    
    private static final double SINK_DISTANCE = 15;
    private static final double SINK_SPEED = 300;

    private static BallComponent ballComponent;
    private static final List<BallComponent> balls = new ArrayList<>();
    private static int currentPlayer = 0;
    
    private boolean dragging = false;
    private boolean shotInProgress = false;
    
    private final GolfGameProperties properties = new GolfGameProperties();

    private static Parent titleScreen;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("Club De Java");
        settings.setIntroEnabled(false);
    }
    
        @Override
    protected void initGame() {
        // GolfFactory creates the entities when we call spawn("name")
        getGameWorld().addEntityFactory(new GolfFactory());
    }
    
        @Override
    protected void initPhysics() {
        // the game is seen from the top so we shouldnt need any gravity
        getPhysicsWorld().setGravity(0, 0);
        
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.GOAL) {
            @Override
            protected void onCollision(Entity ball, Entity goal) {
                BallComponent b = ball.getComponent(BallComponent.class);
                if (b.isSunk()) {
                    return;
                }

                double distance = b.getCenter().distance(goal.getCenter());
                if (distance <= SINK_DISTANCE && b.getSpeed() < SINK_SPEED) {
                    b.sink();
                }
            }
        });
    }

    @Override
    protected void initInput() {
        getInput().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            // NO BALL YET WHILE TITLE SCREEN IS SHOWN IF NOT IT INSTANTLY CRASHES WHEN U CLICK SOMEWHERE
            if (ballComponent == null) {
                return;
            }
            
            // STOPS SHOOTING WHILE BALL IS ROLLING
            if (shotInProgress) {
                return;
            }
            
            if (ballComponent.isSunk()) {
                return;
            }
            
            Point2D mouse = getInput().getMousePositionWorld();
            dragging = ballComponent.getCenter().distance(mouse) <= RADIUS;
        });

        getInput().addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!dragging) return;
            dragging = false;

            Point2D drag = getInput().getMousePositionWorld().subtract(ballComponent.getCenter());
            ballComponent.launch(drag.multiply(-POWER));
            properties.recordLaunch();
            
            properties.addStroke(currentPlayer);
            
            shotInProgress = true;
        });
    }
    
    @Override
    protected void onUpdate(double tpf) {
        // WHEN THE BALL THAT WAS SHOT STOPS ROLLING THE SHOT IS OVER
        
        if (shotInProgress && ballComponent.isStopped()) {
            shotInProgress = false;
            
            nextPlayer();
        }
    }
    
    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % balls.size();
        
        if (currentPlayer == 0) {
            properties.nextTurn();
        }
        
        ballComponent = balls.get(currentPlayer);
        
        showCurrentBallOnTop();
    }
    
    @Override
    protected void initUI() {
        try {
            
            titleScreen = FXMLLoader.load(getClass().getResource("title.fxml"));
            titleScreen.getStylesheets().add(getClass().getResource("style.css").toString());
            getGameScene().addUINode(titleScreen);
        } catch (IOException e) {
            throw new RuntimeException("Could not load title.fxml", e);
        }
    }
    
  
        public static void startGame() {
        getGameScene().removeUINode(titleScreen);
        buildCourse();
    }

    /**
     * Builds the courses that the players will play on.
     */
    private static void buildCourse() {
        spawn("floor");
        
        String[] defaultWalls = {
            "0, 0, 1100, 40",
            "0, 660, 1100, 40",
            "0, 36, 51, 627",
            "1049, 36, 51, 627",
        };
        placeWalls(defaultWalls);
                
        if (GameSetup.getDifficulty() == Difficulty.EASY) {
            INITIAL_X = 955;
            INITIAL_Y = 520;
            String[] walls = {
                "430, 212, 670, 257",   
            };
            placeWalls(walls);
            spawn("goal", 948, 115);
            spawnBalls(INITIAL_X, INITIAL_Y);
        } else if (GameSetup.getDifficulty() == Difficulty.MEDIUM) {
            INITIAL_X = 955;
            INITIAL_Y = 520;
            
            String [] walls = {
                "130,173,165,36",   
                "295,172,57,488",   
                "147,278,63,117",   
                "52,465,164,36",   
                
                "552,42,57,488",    
                "455,484,233,45",  

                "701,173,233,45", 
                "791,218,57,444",
            };
            placeWalls(walls);
            spawn("goal", 69, 601);
            spawnBalls(INITIAL_X, INITIAL_Y);
        } else if (GameSetup.getDifficulty() == Difficulty.HARD) {
            INITIAL_X = 110;
            INITIAL_Y = 90;
            String[] walls = {
                "200, 40, 40, 530",
                "400, 130, 40, 530",
                "600, 40, 40, 530",
                "800, 130, 40, 530",
                "285, 300, 70, 70",
                "485, 200, 70, 70",
                "685, 430, 70, 70",
                "880, 520, 120, 20",
            };
            placeWalls(walls);
            spawn("goal", 940, 580);
            spawnBalls(INITIAL_X, INITIAL_Y);
        }
       
    }
    
    private static void spawnBalls(int initialX, int initialY) {
    int players = GameSetup.getPlayerCount();
    if (players == 0) {
        players = 1;
    }
    
    balls.clear();
    currentPlayer = 0;

    for (int i = 0; i < players; i++) {
        Entity ball = spawn("ball", new SpawnData(initialX, initialY).put("color", GolfFactory.PLAYER_COLORS[i]));

        balls.add(ball.getComponent(BallComponent.class));
    }
    
    ballComponent = balls.get(0);
    
    showCurrentBallOnTop();
    }
    
    private static void showCurrentBallOnTop() {
        for (BallComponent ball : balls) {
            ball.getEntity().setZIndex(0);
        }
        ballComponent.getEntity().setZIndex(1);
    }
    
    
    /**
     * Will spawns a wall at the given x,y coordinate and also with the giben size.
     */
    private static void spawnWall(double x, double y, double width, double height) {
        spawn("wall", new SpawnData(x, y)
                .put("width", width)
                .put("height", height));
    }
    
    /**
     * Places walls in a course.
     * @param walls the coordinates of the walls to place.
     */
    private static void placeWalls(String[] walls) {
        for (String wall : walls) {
                String[] p = wall.split(",");
                spawnWall(
                    Double.parseDouble(p[0].trim()),
                    Double.parseDouble(p[1].trim()),
                    Double.parseDouble(p[2].trim()),
                    Double.parseDouble(p[3].trim())
                );
            }
    }
}