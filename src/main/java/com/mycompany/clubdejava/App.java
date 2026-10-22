package com.mycompany.clubdejava;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

/**
 * Club De Java: a turn-based, top-down golf game.
 */
public class App extends GameApplication {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;

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
}