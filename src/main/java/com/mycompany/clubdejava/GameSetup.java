
package com.mycompany.clubdejava;

/**
 *
 * @author antho
 */
public final class GameSetup {
    
    
    public static final int MIN_PLAYERS = 1;
    public static final int MAX_PLAYERS = 4;

    private static Difficulty difficulty = null;
    
    private static int playerCount = 0;

    private GameSetup() {
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty value) {
        difficulty = value;
    }
    
    
    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int value) {
        if (value < MIN_PLAYERS || value > MAX_PLAYERS) {
            throw new IllegalArgumentException("Player count must be between 1 and 4, cannot be " + value);
        }
        playerCount = value;
    }

    
    public static boolean isReady() {
        return difficulty != null && playerCount >= MIN_PLAYERS;
    }
}
