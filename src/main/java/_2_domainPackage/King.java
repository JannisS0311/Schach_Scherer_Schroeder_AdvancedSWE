package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class King implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;
    private ArrayList<Location> checkableFields = new ArrayList<>();

    public King(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
        //this.fillCheckableFields();
    }

    private void fillCheckableFields(){
        Location kingLocation = actualTile.getLocation();
        int cRow = kingLocation.getRowCoordinate();
        int cCol = kingLocation.getRowCoordinate();

        //All vertical locations
        for (int i = kingLocation.getRowCoordinate()+1; i<10;i++){
            checkableFields.add(new Location(i,cCol));
        }
        for (int ii = kingLocation.getRowCoordinate()-1; ii>0;ii--){
            checkableFields.add(new Location(ii,cCol));
        }

        //All horizontal locations
        for (int j = kingLocation.getColumnCoordinate()+1; j<10;j++){
            checkableFields.add(new Location(j,cRow));
        }
        for (int jj = kingLocation.getColumnCoordinate()-1; jj>0;jj--){
            checkableFields.add(new Location(jj,cRow));
        }


        for (Location currLoc : checkableFields){
            System.out.println(currLoc.getColumnCoordinate() + "," +  currLoc.getRowCoordinate());
        }
    }

    public boolean isMoveOkay(Location oldLocation, Location newLocation) {
        return checkIfRowOrColStep(oldLocation, newLocation)
                || checkIfDiagonalStep(oldLocation, newLocation);
        //TODO add check logic
    }

    private boolean checkIfRowOrColStep(Location oldLocation, Location newLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        if (oldCol == newCol && oldRow == newRow + 1) {
            return true;
        } else if (oldCol == newCol && oldRow == newRow - 1) {
            return true;
        } else if (oldRow == newRow && oldCol == newCol + 1) {
            return true;
        } else return oldRow == newRow && oldCol == newCol - 1;
    }

    private boolean checkIfDiagonalStep(Location oldLocation, Location newLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        if (oldCol == newCol + 1 && oldRow == newRow + 1) {
            return true;
        } else if (oldCol == newCol + 1 && oldRow == newRow - 1) {
            return true;
        } else if (oldCol == newCol - 1 && oldRow == newRow + 1) {
            return true;
        } else return oldCol == newCol - 1 && oldRow == newRow - 1;
    }

    @Override
    public ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation) {
        ArrayList<Location> tilesInBetween = new ArrayList<>();
        return tilesInBetween;
    }

    @Override
    public Color getPieceColor() {
        return pieceColor;
    }
}
