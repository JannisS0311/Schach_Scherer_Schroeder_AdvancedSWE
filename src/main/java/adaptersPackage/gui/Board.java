package adaptersPackage.gui;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

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
     *
     *      a b c d e f g h
     *
     * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
     * Uppercase is white
     **/

    public Board() {
        init();
    }

    private void init() {
        this.setLayout(new GridLayout(8, 8));

        fillBoardInitially();

        this.setSize(new Dimension(500, 500));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillBoardInitially() {
        // fill the white pieces initially:
        fillAllPiecesInitially("WHITE");
        // fill the empty spaces:
        for (int rowCounter = 2; rowCounter < 6; rowCounter++) {
            for (int columnCounter = 0; columnCounter < 8; columnCounter++) {
                tiles[rowCounter][columnCounter] =
                        new Tile((rowCounter + columnCounter) % 2 == 1 ? Tile.getColor1() : Tile.getTileColor2(), null, null, this, rowCounter, columnCounter);
                this.add(tiles[rowCounter][columnCounter]);
            }
        }
        // fill the black pieces initially
        fillAllPiecesInitially("BLACK");
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
        int rowNumber;
        if (pieceColor == "WHITE") {
            rowNumber = 1;
        } else {
            rowNumber = 6;
        }
        for (int columnCounter = 0; columnCounter < 8; columnCounter++) {
            tiles[rowNumber][columnCounter] = new Tile((columnCounter + rowNumber) % 2 == 1 ? Tile.getColor1() : Tile.getTileColor2(), "Pawn", pieceColor, Board.this, rowNumber, columnCounter);
            this.add(tiles[rowNumber][columnCounter]);
        }
    }

    private void fillLineWithTilesExceptPawnsInitially(String pieceColor) {
        int rowNumber;
        if (pieceColor == "WHITE") {
            rowNumber = 0;
        } else {
            rowNumber = 7;
        }
        for (int columnCounter = 0; columnCounter < 8; columnCounter++) {
            tiles[rowNumber][columnCounter] = new Tile((columnCounter + rowNumber) % 2 == 1 ? Tile.getColor1() : Tile.getTileColor2(), pieceOrder[columnCounter], pieceColor, Board.this, rowNumber, columnCounter);
            this.add(tiles[rowNumber][columnCounter]);
        }
    }

    /*public void removePiece(int xCoordinate, int yCoordinate) {
        this.tiles[xCoordinate][yCoordinate].removeIcon();
        this.tiles[xCoordinate][yCoordinate].removePiece();
        this.repaint();
    } */

    /*public void setPiece(String pieceColor, String piece, int xCoordinate, int yCoordinate) {
        this.tiles[xCoordinate][yCoordinate].setIcon(loadIcon(pieceColor + piece));
        this.tiles[xCoordinate][yCoordinate].setPiece(piece, pieceColor );
        this.repaint();
    } */

    public boolean tileIsEmpty(int xCoordinate, int yCoordinate) {
        if (this.tiles[xCoordinate][yCoordinate].getIcon() == null){
            return true;
        }
        return false;
    }

    public Tile getTile(int rowCoordinate, int columnCoordinate) {
        return tiles[rowCoordinate][columnCoordinate];
    }

}