
package com.mycompany.clubdejava;

/**
 *
 * @author antho
 */
public final class GameSetup {

    private static Difficulty difficulty = null;

    private GameSetup() {
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty value) {
        difficulty = value;
    }
}
