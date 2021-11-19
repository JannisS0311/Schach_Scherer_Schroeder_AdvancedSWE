package domainPackage;

import java.awt.*;

public class Queen implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Queen(Tile actualTile, Board board, Color pieceColor) {
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
