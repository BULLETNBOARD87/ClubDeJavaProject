
package com.mycompany.clubdejava;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class TitleController {

    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    private ToggleButton easyButton;

    @FXML
    private ToggleButton hardButton;

    @FXML
    private ToggleButton mediumButton;

    @FXML
    private ToggleButton players1;

    @FXML
    private ToggleButton players2;

    @FXML
    private ToggleButton players3;

    @FXML
    private ToggleButton players4;

    @FXML
    private ToggleGroup playersGroup;

    @FXML
    private Button startButton;
    
    
    @FXML
    private void onEasy() {
        GameSetup.setDifficulty(Difficulty.EASY);
    }

    @FXML
    private void onMedium() {
        GameSetup.setDifficulty(Difficulty.MEDIUM);
    }

    @FXML
    private void onHard() {
        GameSetup.setDifficulty(Difficulty.HARD);
    }
    
    @FXML
    private void onPlayers(ActionEvent event) {
        ButtonBase clicked = (ButtonBase) event.getSource();
        GameSetup.setPlayerCount(Integer.parseInt(clicked.getText()));
        System.out.println(Integer.parseInt(clicked.getText()));
    }
    
    @FXML
    private void onStart() {
        App.startGame();
    }
}