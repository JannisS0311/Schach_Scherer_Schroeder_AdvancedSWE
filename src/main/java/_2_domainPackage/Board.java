package _2_domainPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final int a = 1, b = 2, c = 3, d = 4, e = 5, f = 6, g = 7, h = 8; //columns of the board
    private static final String[] pieceOrder = {"Rock", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rock"};
    private final Tile[][] tiles = new Tile[8][8];

    /**
     * 8	r n b q k b n r
     * 7	p p p p p p p p
     * 6	. . . . . . . .
     * 5	. . . . . . . .
     * 4	. . . . . . . .
     * 3	. . . . . . . .
     * 2	P P P P P P P P
     * 1    R N B Q K B N R
     * <p>
     * a b c d e f g h
     * <p>
     * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
     * Uppercase is white
     **/

    public Board() {
        fillBoardInitially();
    }

    private void fillBoardInitially() {
        // fill the white pieces initially:
        fillAllPiecesInitially("BLACK");
        // fill the empty spaces:
        for (int i = 16; i < 48; i++) {
            int rowCounter = i / 8;
            int columnCounter = i % 8;
            tiles[rowCounter][columnCounter] =
                    new Tile(Tile.getColor((rowCounter + columnCounter) % 2), null, null, this, rowCounter, columnCounter);
        }
        // fill the black pieces initially
        fillAllPiecesInitially("WHITE");
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
        int rowNumber = 6;
        if (pieceColor == "BLACK") {
            rowNumber = 1;
        }
        for (int columnCounter = 0; columnCounter < 8; columnCounter++) {
            tiles[rowNumber][columnCounter] = new Tile(Tile.getColor((rowNumber + columnCounter) % 2), "Pawn", pieceColor, Board.this, rowNumber, columnCounter);
        }
    }

    private void fillLineWithTilesExceptPawnsInitially(String pieceColor) {
        int rowNumber = 7;
        if (pieceColor == "BLACK") {
            rowNumber = 0;
        }
        for (int columnCounter = 0; columnCounter < 8; columnCounter++) {
            tiles[rowNumber][columnCounter] = new Tile(Tile.getColor((rowNumber + columnCounter) % 2), pieceOrder[columnCounter], pieceColor, Board.this, rowNumber, columnCounter);
        }
    }

    public Tile getTile(int rowCoordinate, int columnCoordinate) {
        return tiles[rowCoordinate][columnCoordinate];
    }

    ///////////////////////////////////////////////

    public void movePiece(Location oldLocation, Location newLocation) {
        Tile oldTile = getTileFromLocation(oldLocation);
        Tile newTile = getTileFromLocation(newLocation);
        Piece chosenPiece = oldTile.getPiece();

        ArrayList<Location> tilesInBetween = chosenPiece.getTilesInBetween(oldLocation, newLocation);
        //TODO neue Klasse Move einführen?
        if (validNewLocation(newLocation)
                && areLocationsEmpty(tilesInBetween)
                && chosenPiece.isMoveOkay(oldLocation, newLocation)
                && (newTile.isEmpty() || newTileHasEnemiesPiece(oldTile, newTile))) {
            changeBoard(oldLocation, newLocation);
        }
    }

    private Tile getTileFromLocation(Location location) {
        return tiles[location.getRowCoordinate()][location.getColumnCoordinate()];
    }

    private boolean newTileHasEnemiesPiece(Tile oldTile, Tile newTile) {
        String attackingPieceColor = oldTile.getPieceColorAsString();
        String beatenPieceColor = newTile.getPieceColorAsString();
        return (beatenPieceColor != null) && !(attackingPieceColor.equals(beatenPieceColor));
    }

    //TODO neue Klasse move einführen?

    private boolean areLocationsEmpty(ArrayList<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            if (!getTileFromLocation(locations.get(i)).isEmpty())
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

    private void changeBoard(Location oldLocation, Location newLocation) {
        int oldRow = oldLocation.getRowCoordinate();
        int oldCol = oldLocation.getColumnCoordinate();
        int newRow = newLocation.getRowCoordinate();
        int newCol = newLocation.getColumnCoordinate();

        Tile oldTile = this.getTile(oldRow, oldLocation.getColumnCoordinate());
        this.tiles[newRow][newCol].setPiece(oldTile.getPiece());
        this.tiles[newRow][newCol].setIcon(oldTile.getIcon());
        this.tiles[newRow][newCol].setPieceColorString(oldTile.getPieceColorAsString());
        this.tiles[oldRow][oldCol].setIcon(null);
        this.tiles[oldRow][oldCol].removePiece();
        this.tiles[oldRow][oldCol].setPieceColor(null);
    }

    private boolean validNewLocation(Location newLocation) {
        int row = newLocation.getRowCoordinate();
        int col = newLocation.getColumnCoordinate();
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }

}