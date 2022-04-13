package _2_domainPackage;

import _1_adaptersPackage.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Board {

    private static final int a = 1, b = 2, c = 3, d = 4, e = 5, f = 6, g = 7, h = 8; //columns of the board
    private static final String[] pieceOrder = {null, "Rock", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rock"};
    private final Square[][] squares = new Square[9][9];

    /**
     * 0    1 2 3 4 5 6 7 8
     * 1    r n b q k b n r
     * 2	p p p p p p p p
     * 3	. . . . . . . .
     * 4	. . . . . . . .
     * 5	. . . . . . . .
     * 6	. . . . . . . .
     * 7	P P P P P P P P
     * 8    R N B Q K B N R
     *
     * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
     * Uppercase is white
     **/

    public Board() {
        this.fillBoardInitially();
    }

    private void fillBoardInitially() {
        fillColumnLabeling();
        // fill the white pieces initially:
        fillAllPiecesInitially("BLACK");
        // fill the empty spaces:
        for (int row = 3; row < 7; row++) {
            squares[row][0] =
                    new Square(String.valueOf(row));
            for (int col = 1; col < 9; col++) {
                squares[row][col] =
                        new Square(new Tile(null, null, this, new Location(row, col)));
            }
        }
        // fill the black pieces initially
        fillAllPiecesInitially("WHITE");
    }

    private void fillColumnLabeling(){
        squares[0][0] = new Square("");
        for (int col = 1; col < 9; col++) {
            squares[0][col] = new Square(String.valueOf((char) (col+96)));
        }
    }

    private void fillAllPiecesInitially(String pieceColor) {
        if (pieceColor == "WHITE") {
            fillLineWithTilesExceptPawnsInitially(pieceColor);
            fillLineWithPawnsInitially(pieceColor);
        } else {
            fillLineWithPawnsInitially(pieceColor);
            fillLineWithTilesExceptPawnsInitially(pieceColor);
        }

    }

    private void fillLineWithPawnsInitially(String pieceColor) {
        int rowNumber = 7;
        if (pieceColor == "BLACK") {
            rowNumber = 2;
        }
        squares[rowNumber][0] =
                new Square(String.valueOf(rowNumber));
        for (int columnCounter = 1; columnCounter < 9; columnCounter++) {
            squares[rowNumber][columnCounter] = new Square(new Tile("Pawn", pieceColor, Board.this, new Location(rowNumber, columnCounter)));
        }
    }

    private void fillLineWithTilesExceptPawnsInitially(String pieceColor) {
        int rowNumber = 8;
        if (pieceColor == "BLACK") {
            rowNumber = 1;
        }
        squares[rowNumber][0] =
                new Square(String.valueOf(rowNumber));
        for (int columnCounter = 1; columnCounter < 9; columnCounter++) {
            squares[rowNumber][columnCounter] = new Square(new Tile(pieceOrder[columnCounter], pieceColor, Board.this, new Location(rowNumber, columnCounter)));
        }
    }

    ///////////////////////////////////////////////

    public boolean movePiece(Location oldLocation, Location newLocation, Game game) {
        Tile oldTile = getSquareFromLocation(oldLocation).getTile();
        Tile newTile = getSquareFromLocation(newLocation).getTile();
        Piece chosenPiece = oldTile.getPiece();

        ArrayList<Location> tilesInBetween = chosenPiece.areTilesBetweenEmpty(oldLocation, newLocation);
        //TODO neue Klasse Move einführen?
        if (
                game.getRunning()
                &&playersTurn(chosenPiece, game)
                &&validNewLocation(newLocation)
                && areLocationsEmpty(tilesInBetween)
                && chosenPiece.isMoveOkay(oldTile, newTile)
                && (newTile.isEmpty() || newTileHasEnemiesPiece(oldTile, newTile, game))
            )
        {
            changeBoard(oldLocation, newLocation);
            chosenPiece.setHasMoved();
            resetEnPassant(chosenPiece.getPieceColor(), newTile);
            checkQueen(oldLocation, newLocation);
            return true;
        }
        return false;
    }

    private void checkQueen(Location oldLocation, Location newLocation){
        if ((newLocation.getRowCoordinate() == 1 || newLocation.getRowCoordinate() == 8)
            && Objects.equals(this.getSquareFromLocation(newLocation).getTile().getPieceType(), "Pawn")
            ){
            this.makeQueen(newLocation);
        }
    }

    private void resetEnPassant(Color pieceColor, Tile newTile) {
        int row = 4;
        Location currLocation;

        if (pieceColor == Color.WHITE){
            row = 5;
        }
        for (int i = 1; i<9;i++){
            currLocation = new Location(row, i);
            Tile currTile = this.getSquareFromLocation(currLocation).getTile();
            if (Objects.equals(currTile.getPieceType(), "Pawn")
                && !currTile.getLocation().equals(newTile.getLocation())
            ){
                Pawn enPassantPawn = (Pawn) this.getSquareFromLocation(currLocation).getTile().getPiece();
                enPassantPawn.setEnPassant(false);
            }
        }
    }

    public void checkGameOver(Game game, String pieceType) {
        if (pieceType == "King"){
            game.setRunning(false);
        }
    }

    public Square getSquareFromLocation(Location location) {
        return squares[location.getRowCoordinate()][location.getColumnCoordinate()];
    }

    private boolean newTileHasEnemiesPiece(Tile oldTile, Tile newTile, Game game) {
        String attackingPieceColor = oldTile.getPieceColorAsString();
        String beatenPieceColor = newTile.getPieceColorAsString();
        String beatenPieceType = newTile.getPieceType();

        if ((beatenPieceColor != null) && !(attackingPieceColor.equals(beatenPieceColor))){
            this.checkGameOver(game,beatenPieceType);
            return true;
        }else {
            return false;
        }
    }

    //TODO neue Klasse move einführen?

    public boolean areLocationsEmpty(ArrayList<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            if (!getSquareFromLocation(locations.get(i)).getTile().isEmpty())
                return false;
        }
        return true;

        /*
        return locations.stream()
            .map(location -> {
                return getTileFromLocation(location);
            })
            .allMatch(tile -> {
                return tile.isEmpty();
            })
        ;
        */

        /*
        return locations.stream()
                .map(this::getTileFromLocation)
                .allMatch(Tile::isEmpty);
        */

        /*
        for(Location location : locations) {
            if(!getTileFromLocation(location).isEmpty())
                return false;
        }
        return true;
        */
    }

    public void changeBoard(Location oldLocation, Location newLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        Tile oldTile = this.getSquareFromLocation(oldLocation).getTile();
        this.squares[newRow][newCol] = new Square(new Tile(oldTile.getPieceType(),
                oldTile.getPieceColorAsString(),
                this,
                newLocation,
                oldTile.getPiece()));
        this.squares[oldRow][oldCol] = new Square(new Tile(null,
                null,
                this,
                oldLocation));
    }

    private boolean validNewLocation(Location newLocation) {
        int row = newLocation.getRowCoordinate();
        int column = newLocation.getColumnCoordinate();
        return row >= 1 && row <= 8 && column >= 1 && column <= 8;
    }

    private boolean playersTurn(Piece piece, Game game){
        return piece.getPieceColor() == game.getTurn();
    }

    public void disappearPiece(Location oldLocation){
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();

        this.squares[oldRow][oldCol] = new Square(new Tile(null,
                null,
                this,
                oldLocation));
    }

    public void makeQueen(Location newLocation) {
        this.squares[newLocation.getRowCoordinate()][newLocation.getColumnCoordinate()] = new Square(new Tile("Queen",
                this.getSquareFromLocation(newLocation).getTile().getPieceColorAsString(),
                this,
                newLocation));
    }

    public void moveBack(Location oldLocation, Location newLocation){
        changeBoard(oldLocation, newLocation);
    }
}