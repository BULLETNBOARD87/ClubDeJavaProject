/**
 * JavaFX App
 */
package com.mycompany.clubdejava;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class App extends GameApplication {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;

    private static final double RADIUS = 20;
    private static final double POWER = 4.0;
    
    private Parent fxmlRoot;
    private Map<String, Object> fxmlNamespace;

    private BallComponent ballComponent;
    private boolean dragging = false;
    private final GolfGameProperties properties = new GolfGameProperties();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("Club De Java");
        settings.setIntroEnabled(false);
    }
    
    /**
     * Loads the provided FXML file.
     */
    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("easyPlaceholder.fxml"));
            fxmlRoot = loader.load();
            fxmlNamespace = loader.getNamespace();
        } catch (Exception ex) {
            throw new RuntimeException("Could not load easyPlaceholder.fxml", ex);
        }
    }
    
    /**
     * Detaches a specified node from the parent.
     * The parent must either be a Pane or a Group.
     * @param node the node to detach.
     */
    private void detachFromParent(Node node) {
        Parent parent = node.getParent();
        if (parent instanceof Pane) {
            ((Pane) parent).getChildren().remove(node);
        } else if (parent instanceof Group) {
            ((Group) parent).getChildren().remove(node);
        } else {
            throw new IllegalStateException(
                    "Unsupported parent for ball: " + (parent == null ? "null" : parent.getClass()));
        }
    }

    @Override
    protected void initGame() {
        ballComponent = new BallComponent(RADIUS);

        entityBuilder()
                .at(400 - RADIUS, 300 - RADIUS)
                .zIndex(1)
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
            properties.recordLaunch();
        });
    }

    @Override
    protected void initUI() {
        loadFxml();
        
        TextField launchField = (TextField) fxmlNamespace.get("launchField");
        launchField.textProperty().bind(properties.launchesProperty().asString("Launches: %d"));
        getGameScene().addUINode(launchField);
    }

    public static void main(String[] args) {
        launch(args);
    }
}