package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Bishop(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation){
        if (Math.abs((oldLocation.getRowCoordinate() - newLocation.getRowCoordinate()))
                == Math.abs((oldLocation.getColumnCoordinate() - newLocation.getColumnCoordinate()))) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        int numberOfSteps = getNumberOfDiagonalSteps(oldLocation, newLocation);
        if (oldLocation.getRowCoordinate() > newLocation.getRowCoordinate() && oldLocation.getColumnCoordinate() > newLocation.getColumnCoordinate()){
            return leftUpperDirection(oldLocation, newLocation, numberOfSteps);
        }
        else if (oldLocation.getRowCoordinate() > newLocation.getRowCoordinate() && oldLocation.getColumnCoordinate() < newLocation.getColumnCoordinate()){
            return rightUpperDirection(oldLocation, newLocation, numberOfSteps);
        }
        else if (oldLocation.getRowCoordinate() < newLocation.getRowCoordinate() && oldLocation.getColumnCoordinate() > newLocation.getColumnCoordinate()){
            return leftLowerDirection(oldLocation, newLocation, numberOfSteps);
        }
        return rightLowerDirection(oldLocation, newLocation, numberOfSteps);
    }

    private int getNumberOfDiagonalSteps(Location oldLocation, Location newLocation){
        return Math.abs((oldLocation.getRowCoordinate() - newLocation.getRowCoordinate()));
    }

    private ArrayList<Location> leftUpperDirection(Location oldLocation, Location newLocation, int numberOfSteps){
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() - i, oldLocation.getColumnCoordinate() - i));
        }
        return tilesInBetween;
    }

    private ArrayList<Location> rightUpperDirection(Location oldLocation, Location newLocation, int numberOfSteps){
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() - i, oldLocation.getColumnCoordinate() + i));
        }
        return tilesInBetween;
    }

    private ArrayList<Location> leftLowerDirection(Location oldLocation, Location newLocation, int numberOfSteps){
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() + i, oldLocation.getColumnCoordinate() - i));
        }
        return tilesInBetween;
    }

    private ArrayList<Location> rightLowerDirection(Location oldLocation, Location newLocation, int numberOfSteps){
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        for (int i = 1; i < numberOfSteps; i++) {
            tilesInBetween.add(
                    new Location(oldLocation.getRowCoordinate() + i, oldLocation.getColumnCoordinate() + i));
        }
        return tilesInBetween;
    }
}
