package minesweeper.textUI;

import minesweeper.main.Game;
import minesweeper.util.InputReader;

public class TextGame {

    private InputReader reader;
    private Game game;

    public TextGame(Game game) {
        this(game, new InputReader());
    }

    public TextGame(Game game, InputReader reader) {
        this.game = game;
        this.reader = reader;
    }

    public void run() {
        while (true) {
            System.out.println(gameState());
            String input = reader.readInput("input: ");
            if (input.equals("x")) {
                break;
            }
            execute(input);
        }
    }

    private String gameState() {
        StringBuilder sb = new StringBuilder();
        sb.append(tilesLeftString());
        sb.append("\n");
        sb.append(columnLegend()).append("\n");
        sb.append(horizontalBorder()).append("\n");
        for (int i = 0; i < game.gameHeight(); i++) {
            sb.append(rowVisualization(i)).append("\n");
        }
        sb.append(horizontalBorder()).append("\n");
        sb.append(columnLegend()).append("\n");
        sb.append("\n");
        return sb.toString();
    }

    private String tilesLeftString() {
        return "Tiles left to clear: " + game.getRemainingTiles() + "\n";
    }

    private String columnLegend() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < game.gameWidth(); i++) {
            sb.append(" ").append(i % 10);
        }
        return sb.toString();
    }

    private String rowVisualization(int row) {
        StringBuilder sb = new StringBuilder();
        sb.append(row % 10).append("|");
        for (int i = 0; i < game.gameWidth(); i++) {
            sb.append(tileString(row, i));
        }
        sb.append(" |").append(row % 10);
        return sb.toString();
    }

    private String horizontalBorder() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append("-");
        for (int i = 0; i < game.gameWidth(); i++) {
            sb.append("--");
        }
        sb.append("--");
        return sb.toString();
    }

    private String tileString(int row, int column) {
        int value = game.getTileState(row, column);
        if (value == -1) {
            return " #";
        } else if (value == 0) {
            return "  ";
        } else if (value == 9) {
            return " ¤";
        }
        return " " + value;
    }

    private void execute(String input) {
        if (input.matches("^\\d+\\s\\d+$")) {
            String[] parts = input.split("\\s");
            game.clear(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
    }
}
