package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Rook implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;
    private boolean hasMoved;

    public Rook(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
        this.hasMoved = false;
    }

    @Override
    public boolean isMoveOkay(Tile oldTile, Tile newTile) {
        if (oldTile.getLocation().getRowCoordinate() == newTile.getLocation().getRowCoordinate()) {
            return true;
        } else return oldTile.getLocation().getColumnCoordinate() == newTile.getLocation().getColumnCoordinate();
    }

    @Override
    public ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation) {
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

    @Override
    public Color getPieceColor() {
        return pieceColor;
    }

    @Override
    public boolean getHasMoved() {
        return this.hasMoved;
    }

    @Override
    public void setHasMoved() {
        this.hasMoved = true;
    }
}
