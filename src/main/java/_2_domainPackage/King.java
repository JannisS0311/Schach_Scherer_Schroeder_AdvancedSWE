package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class King implements Piece {

    Tile actualTile;
    Board board;
    Color pieceColor;
    private boolean hasMoved;
    private ArrayList<Location> checkableFields = new ArrayList<>();

    public King(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
        this.hasMoved = false;
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

    private boolean checkIfRowOrColStep(Tile oldTile, Tile newTile) {
        int oldRow = oldTile.getLocation().getRowCoordinate();
        int oldCol = oldTile.getLocation().getColumnCoordinate();
        int newRow = newTile.getLocation().getRowCoordinate();
        int newCol = newTile.getLocation().getColumnCoordinate();

        if (oldCol == newCol && oldRow == newRow + 1) {
            return true;
        } else if (oldCol == newCol && oldRow == newRow - 1) {
            return true;
        } else if (oldRow == newRow && oldCol == newCol + 1) {
            return true;
        } else if (oldRow == newRow && oldCol == newCol - 2) {
            return checkShortRochade(oldTile, newTile);
        } else if (oldRow == newRow && oldCol == newCol + 2) {
            return checkLongRochade(oldTile, newTile);
        } else return oldRow == newRow && oldCol == newCol - 1;
    }

    private boolean checkShortRochade(Tile oldTile, Tile newTile) {
        if (
                //King has not moved yet
                !this.hasMoved
                //Rook has not moved yet
                && !this.board.getSquareFromLocation(new Location(oldTile.getLocation().getRowCoordinate(), 8)).getTile().getPiece().getHasMoved()
                //new field is empty
                && newTile.isEmpty()
                //next to king is empty
                &&this.board.getSquareFromLocation(new Location(oldTile.getLocation().getRowCoordinate(), oldTile.getLocation().getColumnCoordinate() + 1)).getTile().isEmpty()
        ){
            this.board.changeBoard(new Location(oldTile.getLocation().getRowCoordinate(),8), new Location(oldTile.getLocation().getRowCoordinate(), 6));
            return true;
        }
        else return false;
    }

    private boolean checkLongRochade(Tile oldTile, Tile newTile) {
        if (
            //King has not moved yet
                !this.hasMoved
                //Rook has not moved yet
                && !this.board.getSquareFromLocation(new Location(oldTile.getLocation().getRowCoordinate(), 1)).getTile().getPiece().getHasMoved()
                //new field is empty
                && newTile.isEmpty()
                //bishop field is empty
                &&this.board.getSquareFromLocation(new Location(oldTile.getLocation().getRowCoordinate(), oldTile.getLocation().getColumnCoordinate() - 1)).getTile().isEmpty()
                //knight field is empty
                &&this.board.getSquareFromLocation(new Location(oldTile.getLocation().getRowCoordinate(), oldTile.getLocation().getColumnCoordinate() - 2)).getTile().isEmpty()
        ){
            this.board.changeBoard(new Location(oldTile.getLocation().getRowCoordinate(),1), new Location(oldTile.getLocation().getRowCoordinate(), 4));
            return true;
        }
        else return false;
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
    public boolean isMoveOkay(Tile oldTile, Tile newTile) {
        return checkIfRowOrColStep(oldTile, newTile)
                || checkIfDiagonalStep(oldTile.getLocation(), newTile.getLocation());
        //TODO add check logic
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
}
