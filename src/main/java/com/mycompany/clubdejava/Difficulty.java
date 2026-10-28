/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubdejava;

/**
 *
 * @author antho
 */
public enum Difficulty {
    EASY("Easy", "easy.txt"),
    MEDIUM("Medium", "medium.txt"),
    HARD("Hard", "hard.txt");

    private final String displayName;
    private final String mapFile;

    Difficulty(String displayName, String mapFile) {
        this.displayName = displayName;
        this.mapFile = mapFile;
    }

    public String getDisplayName() {
        return displayName;
    }

    
    public String getMapFile() {
        return mapFile;
    }
}
