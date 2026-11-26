/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubdejava;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author super
 */
public class GolfGameProperties {

    private final IntegerProperty launches = new SimpleIntegerProperty(0);
    private final IntegerProperty par = new SimpleIntegerProperty(0);
    
    private final IntegerProperty turn = new SimpleIntegerProperty(1);

    
    public void recordLaunch() {
        launches.set(launches.get() + 1);
    }
    
    public void startCourse(int coursePar) {
        launches.set(0);
        par.set(coursePar);
    }
    
    public int getPar() {
        return par.get();
    }

    public IntegerProperty parProperty() {
        return par;
    }

    public int getLaunches() {
        return launches.get();
    }
    
    public IntegerProperty launchesProperty() {
        return launches;
    }
    
    public void addStroke(int player) {
        strokes[player]++;
    }

    public int getStrokes(int player) {
        return strokes[player];
    }
    
    public void nextTurn() {
        turn.set(turn.get() + 1);
    }

    public int getTurn() {
        return turn.get();
    }

    public IntegerProperty turnProperty() {
        return turn;
    }

    public void reset() {
        launches.set(0);
        
        turn.set(1);
        
        for (int i = 0; i < strokes.length; i++) {
            strokes[i] = 0;
        }
    }
    
    public String getScoreName() {
        int strokes = launches.get();
        if (strokes == 1) return "Hole in one";

        int diff = strokes - par.get();

        if (diff <= -3) { 
            return "Albatross";
        }
        if (diff == -2) {
            return "Eagle";
        }
        if (diff == -1) {
            return "Birdie";
        }
        if (diff == 0)  {
            return "Par";
        }
        if (diff == 1)  {
            return "Bogey";
        }
        if (diff == 2)  {
            return "Double bogey";
        }
        return "+" + diff;
    }
    // AMOUNT OF STROKES DOR EACH PLAYER
    
    private final int[] strokes = new int[GameSetup.MAX_PLAYERS];
}