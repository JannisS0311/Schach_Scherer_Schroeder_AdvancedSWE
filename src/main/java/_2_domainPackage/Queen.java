package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Queen implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    public Queen(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation){
        return false;
    }

    @Override
    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation) {
        return null;
    }
}