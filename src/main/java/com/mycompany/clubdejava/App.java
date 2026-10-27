package com.mycompany.clubdejava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

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
    protected void initUI() {
        try {
            
            titleScreen = FXMLLoader.load(getClass().getResource("title.fxml"));
            titleScreen.getStylesheets().add(getClass().getResource("style.css").toString());
            getGameScene().addUINode(titleScreen);
        } catch (IOException e) {
            throw new RuntimeException("Could not load title.fxml", e);
        }
    }
}