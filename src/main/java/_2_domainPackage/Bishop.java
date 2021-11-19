package _2_domainPackage;

import java.awt.*;

public class Bishop implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Bishop(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean setLocation(int newRowCoordinate, int newColumnCoordinate, Board board) {
        Tile newTile = board.getTile(newColumnCoordinate, newRowCoordinate);
        return false;
    }
}
