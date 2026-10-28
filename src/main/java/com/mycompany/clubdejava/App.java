package com.mycompany.clubdejava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.SpawnData;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Club De Java: a turn-based, top-down golf game.
 */
public class App extends GameApplication {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    
 
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
     * Builds the Easy course.
     */
    private static void buildCourse() {
        spawn("floor");
       
        spawnWall(0, 0, 1100, 40);
        spawnWall(0, 660, 1100, 40);
        spawnWall(0, 36, 51, 627);
        spawnWall(1049, 36, 51, 627);
        
    
        spawnWall(430, 212, 670, 257);
        
        
        spawn("goal", 948, 115);
    }
    
    
    /**
     * Will spawns a wall at the given x,y coordinate and also with the giben size.
     */
    private static void spawnWall(double x, double y, double width, double height) {
        spawn("wall", new SpawnData(x, y)
                .put("width", width)
                .put("height", height));
    }
}