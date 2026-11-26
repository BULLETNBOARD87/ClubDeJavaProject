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
    EASY("Easy", "easy.txt", 5),
    MEDIUM("Medium", "medium.txt", 8),
    HARD("Hard", "hard.txt", 12);

    private final String displayName;
    private final String mapFile;
    private final int par;

    Difficulty(String displayName, String mapFile, int par) {
        this.displayName = displayName;
        this.mapFile = mapFile;
        this.par = par;

    }

    public String getDisplayName() {
        return displayName;
    }

    
    public String getMapFile() {
        return mapFile;
    }
}
