package control;

import gui.Board;
import gui.Tile;

import java.awt.*;

public class King implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public King(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean setLocation(int newRowCoordinate, int newColumnCoordinate) {
        Tile newTile = board.getTile(newColumnCoordinate, newRowCoordinate);
        return false;
    }
}
