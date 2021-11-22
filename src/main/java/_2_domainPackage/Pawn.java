package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Pawn implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Pawn(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        if(checkIfOneStep(oldLocation, newLocation)){
            return tilesInBetween;
        }
        if(oldLocation.getRowCoordinate() == 1){
            tilesInBetween.add(new Location(2, oldLocation.getColumnCoordinate()));
            return tilesInBetween;
        }
        tilesInBetween.add(new Location(5, oldLocation.getColumnCoordinate()));
        return tilesInBetween;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation){
        if (checkIfInTheSameColumn(oldLocation, newLocation)
                && checkIfMoveIsForwards(oldLocation, newLocation)
                && checkIfStepNumberIsAllowed(oldLocation, newLocation)) {
            return true;
        }
        else if(checkIfMoveIsDiagonal(oldLocation, newLocation)
                && checkIfNewTileIsOccupied(newLocation)){
            return true;
        }
        return false;
    }

    private boolean checkIfInTheSameColumn(Location oldLocation, Location newLocation) {
        Integer oldColumnNumber = oldLocation.getColumnCoordinate();
        Integer newColumnNumber = newLocation.getColumnCoordinate();
        if (oldColumnNumber.equals(newColumnNumber)) {
            return true;
        }
        return false;
    }

    private boolean checkIfMoveIsForwards(Location oldLocation, Location newLocation){
        if(pieceColor == Color.WHITE && (oldLocation.getRowCoordinate() > newLocation.getRowCoordinate())){
            return true;
        }
        else if(pieceColor == Color.BLACK && (oldLocation.getRowCoordinate() < newLocation.getRowCoordinate())){
            return true;
        }
        return false;
    }

    private boolean checkIfStepNumberIsAllowed(Location oldLocation, Location newLocation){
        if(checkIfInitiallyTwoSteps(oldLocation, newLocation)){
            return true;
        }
        else if(checkIfOneStep(oldLocation, newLocation)){
            return true;
        }
        return false;
    }

    private boolean checkIfInitiallyTwoSteps(Location oldLocation, Location newLocation){
        if(oldLocation.getRowCoordinate() == 1 && newLocation.getRowCoordinate() == 3){
            return true;
        }
        else if(oldLocation.getRowCoordinate() == 6 && newLocation.getRowCoordinate() == 4){
            return true;
        }
        return false;
    }

    private boolean checkIfOneStep(Location oldLocation, Location newLocation){
        if(oldLocation.getRowCoordinate() == newLocation.getRowCoordinate() + 1){
            return true;
        }
        else if(oldLocation.getRowCoordinate() == newLocation.getRowCoordinate() - 1){
            return true;
        }
        return false;
    }

    private boolean checkIfMoveIsDiagonal(Location oldLocation, Location newLocation){
        if(oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate() + 1 ||
                oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate() - 1){
            return true;
        }
        return false;
    }

    private boolean checkIfNewTileIsOccupied(Location newLocation){
        boolean newTileIsOccupied = this.board.getTile(newLocation.getRowCoordinate(), newLocation.getColumnCoordinate()).tileIsEmpty();
        return !newTileIsOccupied;
    }
}
