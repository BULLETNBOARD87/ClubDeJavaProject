/**
 * JavaFX App
 */
package com.mycompany.clubdejava;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class App extends GameApplication {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    
    private static final double RADIUS = 20;
    private static final double POWER = 4.0;

    private BallComponent ballComponent;
    private boolean dragging = false;


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
            Parent root = FXMLLoader.load(getClass().getResource("testing.fxml"));
            getGameScene().addUINode(root);
        } catch (Exception ex) {
            throw new RuntimeException("Could not load testing.fxml", ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}