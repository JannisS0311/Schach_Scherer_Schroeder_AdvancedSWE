package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    private Tile[][] tiles = new Tile[8][8];

    private static final int a = 1, b = 2, c = 3, d = 4, e = 5, f = 6, g = 7, h = 8; //columns of the board

    private static final String[] pieceOrder = {"Rock", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rock"};

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
        Piece chosenPiece = this.getTile(oldLocation.getRowCoordinate(), oldLocation.getColumnCoordinate()).getPiece();

        ArrayList<Location> tilesInBetween = chosenPiece.getTilesInBetween(oldLocation, newLocation);

        if (validNewLocation(newLocation)
                && tilesBetweenAreEmpty(tilesInBetween)
                && chosenPiece.isMoveOkay(oldLocation, newLocation)
                && (newTileIsEmpty(newLocation) || newTileHasEnemiesPiece(oldLocation, newLocation)))
        {
            changeBoard(oldLocation, newLocation);
        }
    }

    private boolean newTileIsEmpty(Location newLocation) {
        boolean newTileIsEmpty = (tiles[newLocation.getRowCoordinate()][newLocation.getColumnCoordinate()].getPiece() == null);
        return newTileIsEmpty;
    }

    private boolean newTileHasEnemiesPiece(Location oldLocation, Location newLocation) {
        String attackingPieceColor = tiles[oldLocation.getRowCoordinate()][oldLocation.getColumnCoordinate()].getPieceColorAsString();
        String beatenPieceColor = tiles[newLocation.getRowCoordinate()][newLocation.getColumnCoordinate()].getPieceColorAsString();
        if (!attackingPieceColor.equals(beatenPieceColor)) {
            return true;
        }
        return false;
    }

    private boolean tilesBetweenAreEmpty(ArrayList<Location> tilesBetween) {
        while (tilesBetween.size() != 0) {
            if (tiles[tilesBetween.get(0).getRowCoordinate()][tilesBetween.get(0).getColumnCoordinate()].tileIsEmpty()) {
                tilesBetween.remove(0);
                continue;
            }
            return false;
        }
        return true;
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
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return false;
        }
        return true;
    }

}