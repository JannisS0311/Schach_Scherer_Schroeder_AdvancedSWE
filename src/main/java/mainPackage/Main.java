package mainPackage;

import domainPackage.Board;
import adaptersPackage.gui.GameFrame;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        GameFrame gameFrame = new GameFrame(board);
    }
}
