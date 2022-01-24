package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;
    private boolean hasMoved;

    public Bishop(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
        this.hasMoved = false;
    }


    @Override
    public boolean isMoveOkay(Tile oldTile, Tile newTile) {
        return Math.abs((oldTile.getLocation().getRowCoordinate() - newTile.getLocation().getRowCoordinate()))
                == Math.abs((oldTile.getLocation().getColumnCoordinate() - newTile.getLocation().getColumnCoordinate()));
    }

    @Override
    public ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation) {
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

    @Override
    public Color getPieceColor() {
        return pieceColor;
    }

    @Override
    public boolean getHasMoved() {
        return hasMoved;
    }

    @Override
    public void setHasMoved() {
        this.hasMoved = true;
    }

    @Override
    public boolean getEnPassant() {
        return false;
    }

    @Override
    public void setEnPassant(boolean enPassant) {

    }

    private int getNumberOfDiagonalSteps(Location oldLocation, Location newLocation) {
        return Math.abs((oldLocation.getRowCoordinate() - newLocation.getRowCoordinate()));
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
