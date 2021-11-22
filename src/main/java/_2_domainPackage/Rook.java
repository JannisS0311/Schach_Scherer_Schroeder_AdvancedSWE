package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Rook implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Rook(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation) {
        if (oldLocation.getRowCoordinate() == newLocation.getRowCoordinate()) {
            return true;
        } else return oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate();
    }

    @Override
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        ArrayList<Location> location = new ArrayList<>();
        if (oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate()) {
            int lowerRowValue = Math.min(oldLocation.getRowCoordinate(), newLocation.getRowCoordinate());
            int higherRowValue = Math.max(oldLocation.getRowCoordinate(), newLocation.getRowCoordinate());
            for (int i = lowerRowValue + 1; i < higherRowValue; i++) {
                location.add(new Location(i, oldLocation.getColumnCoordinate()));
            }
            return location;
        }
        // if oldLocation.getRowCoordinate() == newLocation.getRowCoordinate():
        int lowerColumnValue = Math.min(oldLocation.getColumnCoordinate(), newLocation.getColumnCoordinate());
        int higherColumnValue = Math.max(oldLocation.getRowCoordinate(), newLocation.getRowCoordinate());
        for (int i = lowerColumnValue + 1; i < higherColumnValue; i++) {
            location.add(new Location(oldLocation.getRowCoordinate(), i));
        }
        return location;
    }
}
