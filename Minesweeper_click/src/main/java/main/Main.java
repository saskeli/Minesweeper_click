
package main;

import logic.*;

public class Main {
    public static void main(String[] args) {
        GameGrid g = new GameGrid(30, 16, 99);
        g.clear(8, 15);
        System.out.println(g);
    }
}
