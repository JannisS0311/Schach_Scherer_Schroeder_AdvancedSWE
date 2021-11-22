package _2_domainPackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Rock implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Rock(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation){
        if (oldLocation.getRowCoordinate() == newLocation.getRowCoordinate()) {
            return true;
        }
        else if(oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate()){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        ArrayList<Location> location = new ArrayList<>();
        if(oldLocation.getColumnCoordinate() == newLocation.getColumnCoordinate()){
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
