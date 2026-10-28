
package com.mycompany.clubdejava;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class TitleController {

    @FXML
    private Button easyButton;
    
    @FXML
    private Button mediumButton;
    
    @FXML
    private Button hardButton;

    @FXML
    private Button players1;
    
    @FXML
    private Button players2;
    
    @FXML
    private Button players3;
    
    @FXML
    private Button players4;
    
    @FXML
    private Label playersHint;
    

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
        Button clicked = (Button) event.getSource();
        GameSetup.setPlayerCount(Integer.parseInt(clicked.getText()));
    }
    
    @FXML
    private void onStart() {
        App.startGame();
    }
}