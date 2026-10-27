/**
 * JavaFX App
 */
package com.mycompany.clubdejava;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    protected void initGame() {
        ballComponent = new BallComponent(RADIUS);

        entityBuilder()
                .at(400 - RADIUS, 300 - RADIUS)
                .view(new Circle(RADIUS, RADIUS, RADIUS, Color.CORNFLOWERBLUE))
                .with(ballComponent)
                .buildAndAttach();
    }
    
    @Override
    protected void initInput() {
        getInput().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            Point2D mouse = getInput().getMousePositionWorld();
            dragging = ballComponent.getCenter().distance(mouse) <= RADIUS;
        });

        getInput().addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!dragging) return;
            dragging = false;

            Point2D drag = getInput().getMousePositionWorld().subtract(ballComponent.getCenter());
            ballComponent.launch(drag.multiply(-POWER));
        });
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