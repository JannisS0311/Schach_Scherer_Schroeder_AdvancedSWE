package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Knight implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;
    private boolean hasMoved;

    public Knight(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
        this.hasMoved = false;
    }

    private int getRowSteps(Location oldLocation, Location newLocation) {
        int rowSteps = Math.abs((oldLocation.getRowCoordinate() - newLocation.getRowCoordinate()));
        System.out.println(rowSteps);
        return rowSteps;
    }

    private int getColumnSteps(Location oldLocation, Location newLocation) {
        int columnSteps = Math.abs((oldLocation.getColumnCoordinate()) - newLocation.getColumnCoordinate());
        System.out.println(columnSteps);
        return columnSteps;
    }

    @Override
    public boolean isMoveOkay(Tile oldTile, Tile newTile) {
        if (getRowSteps(oldTile.getLocation(), newTile.getLocation()) == 2 && getColumnSteps(oldTile.getLocation(), newTile.getLocation()) == 1) {
            return true;
        } else return getRowSteps(oldTile.getLocation(), newTile.getLocation()) == 1 && getColumnSteps(oldTile.getLocation(), newTile.getLocation()) == 2;
    }

    @Override
    public ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation) {
        // doesn't matter because knight is allowed to jump over other pieces
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        return tilesInBetween;
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
