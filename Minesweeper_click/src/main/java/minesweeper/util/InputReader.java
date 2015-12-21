package minesweeper.util;

import java.util.Scanner;

public class InputReader {
    private Scanner scan;

    public InputReader(Scanner scan) {
        this.scan = scan;
    }

    public InputReader() {
        this(new Scanner(System.in));
    }
    
    public String readInput(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }
}
