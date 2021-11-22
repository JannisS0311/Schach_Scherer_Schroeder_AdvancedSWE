package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Knight implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Knight(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation) {
        if (getRowSteps(oldLocation, newLocation) == 2 && getColumnSteps(oldLocation, newLocation) == 1) {
            return true;
        } else return getRowSteps(oldLocation, newLocation) == 1 && getColumnSteps(oldLocation, newLocation) == 2;
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
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        // doesn't matter because knight is allowed to jump over other pieces
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        return tilesInBetween;
    }
}
