package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class King implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;

    public King(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation) {
        return checkIfRowOrColStep(oldLocation, newLocation)
                || checkIfDiagonalStep(oldLocation, newLocation);
    }

    private boolean checkIfRowOrColStep(Location oldLocation, Location newLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        if (oldCol == newCol && oldRow == newRow + 1) {
            return true;
        } else if (oldCol == newCol && oldRow == newRow - 1) {
            return true;
        } else if (oldRow == newRow && oldCol == newCol + 1) {
            return true;
        } else return oldRow == newRow && oldCol == newCol - 1;
    }

    private boolean checkIfDiagonalStep(Location oldLocation, Location newLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        if (oldCol == newCol + 1 && oldRow == newRow + 1) {
            return true;
        } else if (oldCol == newCol + 1 && oldRow == newRow - 1) {
            return true;
        } else if (oldCol == newCol - 1 && oldRow == newRow + 1) {
            return true;
        } else return oldCol == newCol - 1 && oldRow == newRow - 1;
    }

    @Override
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        return tilesInBetween;
    }
}
