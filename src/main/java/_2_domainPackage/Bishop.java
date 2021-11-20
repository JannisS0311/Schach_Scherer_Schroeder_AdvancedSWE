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
        int numberOfSteps = getNumberOfSteps(oldLocation, newLocation);
        if(numberOfSteps != 0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        int numberOfSteps = getNumberOfSteps(oldLocation, newLocation);
        Location locWithLowerRowCoordinate = newLocation;
        if(oldLocation.getRowCoordinate() < newLocation.getRowCoordinate()){
            locWithLowerRowCoordinate = oldLocation;
        }
        for (int i = 0; i < numberOfSteps; i++) {
            tilesInBetween.add(new Location(locWithLowerRowCoordinate.getRowCoordinate() + i,
                    locWithLowerRowCoordinate.getColumnCoordinate() + i));
        }
        return tilesInBetween;
    }

    private int getNumberOfSteps(Location oldLocation, Location newLocation){
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        for (int i = -8; i < 9; i++) {
            if (oldRow == newRow + i && oldCol == newCol + i){
                return i;
            }
        }
        return 0; // no fitting i found, move is not okay
    }
}
