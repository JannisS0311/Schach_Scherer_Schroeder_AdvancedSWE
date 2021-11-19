package mainPackage;

import adaptersPackage.gui.Board;

public class ChessGame {

    public static void main(String[] args) {
        Board board = new Board();
        board.getTile(6,0).getPiece().setLocation(4, 0, board);
        board.getTile(7,0).getPiece().setLocation(5, 0, board);
        //System.out.println(board.getTile(5,0).getPiece().setLocation(5, 4, board));
    }
}
