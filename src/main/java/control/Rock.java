package control;

import gui.Board;
import gui.Tile;

import java.awt.*;

public class Rock implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Rock(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean setLocation(int newRowCoordinate, int newColumnCoordinate) {
        Tile newTile = board.getTile(newColumnCoordinate, newRowCoordinate);
        if (checkIfMoveIsAllowed(newTile)) {
            return true;
        }
        return false;
    }

    public boolean checkIfMoveIsAllowed(Tile newTile) {
        if (checkIfInTheSameRow(newTile) && checkIfNewTileIsEmpty(newTile)) {
            return checkIfTheTilesBetweenAreEmptyRowDirection(newTile);
        } else if (checkIfInTheSameColumn(newTile) && checkIfNewTileIsEmpty(newTile)) {
            return checkIfTheTilesBetweenAreEmptyColumnDirection(newTile);
        }
        return false;
    }

    private boolean checkIfInTheSameColumn(Tile newTile) {
        Integer oldColumnCoordinate = this.actualTile.getColumnCoordinate();
        Integer newColumnCoordinate = newTile.getColumnCoordinate();
        if (oldColumnCoordinate.equals(newColumnCoordinate)) {
            return true;
        }
        return false;
    }

    private boolean checkIfInTheSameRow(Tile newTile) {
        Integer oldRowCoordinate = this.actualTile.getColumnCoordinate();
        Integer newRowCoordinate = newTile.getColumnCoordinate();
        if (oldRowCoordinate.equals(newRowCoordinate)) {
            return true;
        }
        return false;
    }

    // ToDo: don't just check on image but also on figure of the tile!
    public boolean checkIfNewTileIsEmpty(Tile newTile) {
        if (newTile.getPiece() == null) {
            return true;
        }
        return false;
    }

    private boolean checkIfTheTilesBetweenAreEmptyRowDirection(Tile newTile) {
        int actualLowerRowValue, higherXValue;
        if (this.actualTile.getColumnCoordinate() > newTile.getColumnCoordinate()) {
            actualLowerRowValue = newTile.getColumnCoordinate();
            higherXValue = this.actualTile.getColumnCoordinate();
        } else {
            actualLowerRowValue = this.actualTile.getColumnCoordinate();
            higherXValue = newTile.getColumnCoordinate();
        }
        for (int i = actualLowerRowValue; i < higherXValue; i++) {
            if (board.tileIsEmpty(actualLowerRowValue, this.actualTile.getRowCoordinate())) {
                continue;
            }
            else return false;
        }
        return true;
    }


    private boolean checkIfTheTilesBetweenAreEmptyColumnDirection(Tile newTile){
        int actualLowerYValue, higherYValue;
        if (this.actualTile.getRowCoordinate() > newTile.getRowCoordinate()) {
            actualLowerYValue = newTile.getRowCoordinate();
            higherYValue = this.actualTile.getRowCoordinate();
        } else {
            actualLowerYValue = this.actualTile.getRowCoordinate();
            higherYValue = newTile.getRowCoordinate();
        }
        for (int i = actualLowerYValue; i < higherYValue; i++) {
            if (board.tileIsEmpty(this.actualTile.getRowCoordinate(), actualLowerYValue)) {
                continue;
            }
            else return false;
        }
        return true;
    }

}
