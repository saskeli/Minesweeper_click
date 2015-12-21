
package minesweeper.main;

import minesweeper.textUI.TextGame;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(16, 16, 40);
        TextGame tg = new TextGame(g);
        tg.run();
    }
}
