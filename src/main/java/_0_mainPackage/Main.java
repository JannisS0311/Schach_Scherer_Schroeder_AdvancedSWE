package _0_mainPackage;

import _2_domainPackage.*;
import _1_adaptersPackage.GameFrame;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        //board.movePiece(new Location(6, 1), new Location(5, 1));
        board.movePiece(new Location(6, 0), new Location(5, 0));
        //board.movePiece(new Location(3, 0), new Location(2, 0));
        //board.movePiece(new Location(2, 0), new Location(1, 1));
        GameFrame gameFrame = new GameFrame(board);
    }
}
