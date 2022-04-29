package _2_domainPackage;

import _1_adaptersPackage.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {

    private final Square[][] squares = new Square[9][9];
    Color turn = Color.WHITE;
    private List<BoardState> boardStates = new ArrayList<>();

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
     * <p>
     * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
     * Uppercase is white
     **/

    public Board() {
        this.fillBoardInitially();
    }

    public Color getTurn() {
        return turn;
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }

    private void fillBoardInitially() {
        fillColumnLabeling();
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
        fillAllPiecesInitially("WHITE");
    }

    private void fillColumnLabeling() {
        squares[0][0] = new Square("");
        for (int col = 1; col < 9; col++) {
            squares[0][col] = new Square(String.valueOf((char) (col + 96)));
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
        if (pieceColor == "BLACK") rowNumber = 1;
        squares[rowNumber][0] =
                new Square(String.valueOf(rowNumber));
        for (int columnCounter = 1; columnCounter < 9; columnCounter++) {
            squares[rowNumber][columnCounter] = new Square(new Tile(PieceOrder.values()[columnCounter].getName(), pieceColor, Board.this, new Location(rowNumber, columnCounter)));
        }
    }

    ///////////////////////////////////////////////

    public Square getSquareFromLocation(Location location) {
        try{
            return squares[location.getRowCoordinate()][location.getColumnCoordinate()];
        }
        catch(ArrayIndexOutOfBoundsException ioobe){
            return null;
        }
    }

    public boolean movePiece(Location oldLocation, Location newLocation, Game game){
        try{
            Tile oldTile = getSquareFromLocation(oldLocation).getTile();
            Tile newTile = getSquareFromLocation(newLocation).getTile();
            Piece chosenPiece;

            chosenPiece =  oldTile.getPiece();

            ArrayList<Location> tilesInBetween = chosenPiece.areTilesBetweenEmpty(oldLocation, newLocation);
            if (game.getRunning()
                    && playersTurn(chosenPiece)
                    && validNewLocation(newLocation)
                    && areLocationsEmpty(tilesInBetween)
                    && chosenPiece.isMoveOkay(oldTile, newTile)
                    && (newTile.isEmpty() || newTileHasEnemiesPiece(oldTile, newTile, game))
            ) {
                changeBoard(oldLocation, newLocation);
                chosenPiece.setHasMoved();
                resetEnPassant(chosenPiece.getPieceColor(), newTile);
                checkQueen(newLocation);
                return true;
            }
            return false;
        }
        catch (NullPointerException nex){
            return false;
        }
    }

    private boolean playersTurn(Piece piece) {
        return piece.getPieceColor() == turn;
    }

    private boolean validNewLocation(Location newLocation) {
        int row = newLocation.getRowCoordinate();
        int column = newLocation.getColumnCoordinate();
        return row >= 1 && row <= 8 && column >= 1 && column <= 8;
    }

    public boolean areLocationsEmpty(ArrayList<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            if (!getSquareFromLocation(locations.get(i)).getTile().isEmpty()) return false;
        }
        return true;
    }

    private boolean newTileHasEnemiesPiece(Tile oldTile, Tile newTile, Game game) {
        String attackingPieceColor = oldTile.getPieceColorAsString();
        String beatenPieceColor = newTile.getPieceColorAsString();
        String beatenPieceType = newTile.getPieceType();

        if ((beatenPieceColor != null) && !(attackingPieceColor.equals(beatenPieceColor))) {
            this.checkGameOver(game, beatenPieceType);
            return true;
        } else {
            return false;
        }
    }

    public void checkGameOver(Game game, String pieceType) {
        if (pieceType == "King") game.setRunning(false);
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

    private void resetEnPassant(Color pieceColor, Tile newTile) {
        int row = 4;
        if (pieceColor == Color.WHITE) row = 5;
        Location currLocation;
        Tile currTile;
        for (int i = 1; i < 9; i++) {
            currLocation = new Location(row, i);
            currTile = this.getSquareFromLocation(currLocation).getTile();
            if (Objects.equals(currTile.getPieceType(), "Pawn")
                    && !currTile.getLocation().equals(newTile.getLocation())
            ) {
                Pawn enPassantPawn = (Pawn) this.getSquareFromLocation(currLocation).getTile().getPiece();
                enPassantPawn.setEnPassant(false);
            }
        }
    }

    private void checkQueen(Location newLocation) {
        if ((newLocation.getRowCoordinate() == 1 || newLocation.getRowCoordinate() == 8)
                && Objects.equals(this.getSquareFromLocation(newLocation).getTile().getPieceType(), "Pawn")
        ) {
            this.makeQueen(newLocation);
        }
    }

    public void makeQueen(Location newLocation) {
        this.squares[newLocation.getRowCoordinate()][newLocation.getColumnCoordinate()] = new Square(new Tile("Queen",
                this.getSquareFromLocation(newLocation).getTile().getPieceColorAsString(),
                this,
                newLocation));
    }

    public void moveBack(Location oldLocation, Location newLocation) {
        changeBoard(oldLocation, newLocation);
    }

    public void disappearPiece(Location oldLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();

        this.squares[oldRow][oldCol] = new Square(new Tile(null,
                null,
                this,
                oldLocation));
    }

    ////////////////////////////////

    public Square[][] getCurrentBoardState() {
        final Square[][] currentSquares = new Square[9][0];
        for (int i = 0; i < 9; i++) {
            currentSquares[i] = Arrays.copyOf(squares[i], squares[i].length);
        }
        return currentSquares;
    }

    public BoardState saveBoardState() {
        BoardState currentBoardState = new BoardState(getCurrentBoardState(), this.turn);
        this.boardStates.add(currentBoardState);
        return currentBoardState;
    }

    public void setBoardState(int i) {
        turn = boardStates.get(i).getTurn();
        for (int rowCounter = 0; rowCounter < 9; rowCounter++) {
            for (int columnCounter = 0; columnCounter < 9; columnCounter++) {
                Tile storedTile = boardStates.get(i).getSquare(rowCounter, columnCounter).getTile();
                if (storedTile != null) {
                    this.squares[rowCounter][columnCounter] = new Square(new Tile(storedTile.getPieceType(),
                            storedTile.getPieceColorAsString(),
                            this,
                            storedTile.getLocation(),
                            storedTile.getPiece()));
                    continue;
                }
                Square storedSquare = (Square) boardStates.get(i).getSquare(rowCounter, columnCounter);
                this.squares[rowCounter][columnCounter] = new Square(storedSquare.getLabeling());
            }
        }
    }

    public List<BoardState> getBoardStates() {
        return boardStates;
    }

}
