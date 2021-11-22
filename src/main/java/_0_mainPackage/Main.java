package _0_mainPackage;

import _1_adaptersPackage.GameFrame;
import _2_domainPackage.Board;
import _2_domainPackage.Location;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.movePiece(new Location(6, 3), new Location(5, 3));
        board.movePiece(new Location(7, 3), new Location(6, 3));
        board.movePiece(new Location(6, 3), new Location(2, 7));
        board.movePiece(new Location(2, 7), new Location(1, 6));
        board.movePiece(new Location(1, 6), new Location(0, 6));
        board.movePiece(new Location(0, 6), new Location(1, 7));
        board.movePiece(new Location(7, 2), new Location(5, 4));
        board.movePiece(new Location(5, 4), new Location(3, 2));
        board.movePiece(new Location(3, 2), new Location(5, 4));
        GameFrame gameFrame = new GameFrame(board);
    }
}
