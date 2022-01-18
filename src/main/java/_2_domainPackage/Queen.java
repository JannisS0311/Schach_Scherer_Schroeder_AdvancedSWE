package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Queen implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Queen(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation) {
        if (moveIsDiagonal(oldLocation, newLocation)) {
            return true;
        } else return moveIsStraight(oldLocation, newLocation);
    }

    private boolean moveIsDiagonal(Location oldLocation, Location newLocation) {
        return Math.abs((oldLocation.getRowCoordinate() - newLocation.getRowCoordinate()))
                == Math.abs((oldLocation.getColumnCoordinate() - newLocation.getColumnCoordinate()));
    }

    private int getNumberOfDiagonalSteps(Location oldLocation, Location newLocation) {
        return Math.abs((oldLocation.getRowCoordinate() - newLocation.getRowCoordinate()));
    }

    private boolean moveIsStraight(Location oldLocation, Location newLocation) {
        if (oldLocation.getRowCoordinate() == newLocation.getRowCoordinate()) {
            return true;
        } else return oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate();
    }

    @Override
    public ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation) {
        if (moveIsDiagonal(oldLocation, newLocation)) {
            return getTilesInBetweenDiagonal(oldLocation, newLocation);
        }
        return getTilesInBetweenStraight(oldLocation, newLocation);
    }

    @Override
    public Color getPieceColor() {
        return pieceColor;
    }

    private ArrayList<Location> getTilesInBetweenStraight(Location oldLocation, Location newLocation) {
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

    private ArrayList<Location> getTilesInBetweenDiagonal(Location oldLocation, Location newLocation) {
        int numberOfSteps = getNumberOfDiagonalSteps(oldLocation, newLocation);
        if (oldLocation.getRowCoordinate() > newLocation.getRowCoordinate() && oldLocation.getColumnCoordinate() > newLocation.getColumnCoordinate()) {
            return leftUpperDirection(oldLocation, newLocation, numberOfSteps);
        } else if (oldLocation.getRowCoordinate() > newLocation.getRowCoordinate() && oldLocation.getColumnCoordinate() < newLocation.getColumnCoordinate()) {
            return rightUpperDirection(oldLocation, newLocation, numberOfSteps);
        } else if (oldLocation.getRowCoordinate() < newLocation.getRowCoordinate() && oldLocation.getColumnCoordinate() > newLocation.getColumnCoordinate()) {
            return leftLowerDirection(oldLocation, newLocation, numberOfSteps);
        }
        return rightLowerDirection(oldLocation, newLocation, numberOfSteps);
    }

    private ArrayList<Location> leftUpperDirection(Location oldLocation, Location newLocation, int numberOfSteps) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() - i, oldLocation.getColumnCoordinate() - i));
        }
        return tilesInBetween;
    }

    private ArrayList<Location> rightUpperDirection(Location oldLocation, Location newLocation, int numberOfSteps) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() - i, oldLocation.getColumnCoordinate() + i));
        }
        return tilesInBetween;
    }

    private ArrayList<Location> leftLowerDirection(Location oldLocation, Location newLocation, int numberOfSteps) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() + i, oldLocation.getColumnCoordinate() - i));
        }
        return tilesInBetween;
    }

    private ArrayList<Location> rightLowerDirection(Location oldLocation, Location newLocation, int numberOfSteps) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() + i, oldLocation.getColumnCoordinate() + i));
        }
        return tilesInBetween;
    }
}
