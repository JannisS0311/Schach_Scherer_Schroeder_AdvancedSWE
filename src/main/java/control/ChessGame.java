package control;

import gui.Board;

public class ChessGame {

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.getTile(1,2).getPiece());
        //board.getTile(1,0).getPiece().setLocation(0,2);
    }
}
