package gui;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private String imagePath = "src/main/resources/chesspieces/";

    private Tile[][] tiles = new Tile[8][8];

    private static final int a=1, b=2, c=3, d=4, e=5, f=6, g=7, h=8; //columns of the board

    private static final String[] pieceOrder = {"Rock", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rock"};

    /**
    * 	 8	r n b q k b n r
    *	 7	p p p p p p p p
    *	 6	. . . . . . . .
    * 	 5	. . . . . . . .
    *	 4	. . . . . . . .
    *	 3	. . . . . . . .
    *	 2	P P P P P P P P
    *	 1  R N B Q K B N R
    *
    *    	a b c d e f g h
    *
    * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
    * Uppercase is white
    **/

    private Board(){
        init();
    }

    private void init(){
        this.setLayout(new GridLayout(8,8));
        fillBoardInitially();
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillBoardInitially(){
        fillAllPiecesInitially("White");
        // fill the empty spaces:
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile((i+j)%2==1 ? Tile.getColor1(): Tile.getColor2(), null);
                this.add(tiles[i][j]);
            }
        }
        fillAllPiecesInitially("Black");
    }

    private void fillAllPiecesInitially(String pieceColor){
        if(pieceColor == "White"){
            fillLineWithTilesExceptPawnsInitially(pieceColor);
            fillLineWithPawnsInitially(pieceColor);
        }
        else{
            fillLineWithPawnsInitially(pieceColor);
            fillLineWithTilesExceptPawnsInitially(pieceColor);
        }

    }

    private void fillLineWithPawnsInitially(String colorOfTheTiles){
        int lineNumber;
        if (colorOfTheTiles == "White") {
            lineNumber = 1;
        }
        else{
            lineNumber = 6;
        }
        for (int i = 0; i < 8; i++) {
            ImageIcon icon = loadIcon(colorOfTheTiles + "Pawn");
            tiles[lineNumber][i] = new Tile((i+lineNumber) % 2 == 1 ? Tile.getColor1(): Tile.getColor2(), icon);
            this.add(tiles[lineNumber][i]);
        }
    }

    private void fillLineWithTilesExceptPawnsInitially(String colorOfTheTiles){
        int lineNumber;
        if (colorOfTheTiles == "White") {
            lineNumber = 0;
        }
        else{
            lineNumber = 7;
        }
        for (int i = 0; i < 8; i++) {
            ImageIcon icon = loadIcon(colorOfTheTiles + pieceOrder[i]);
            tiles[lineNumber][i] = new Tile((i+lineNumber) % 2 == 1 ? Tile.getColor1(): Tile.getColor2(), icon);
            this.add(tiles[lineNumber][i]);
        }
    }

    private ImageIcon loadIcon(String fileName){
        ImageIcon icon = new ImageIcon(imagePath+ fileName + ".png");
        return icon;
    }

    private void removePiece(int xCoordinate, int yCoordinate){
        this.tiles[xCoordinate][yCoordinate].setIcon(null);
        this.repaint();
    }

    private void setPiece(String pieceColor, String piece, int xCoordinate, int yCoordinate){
        this.tiles[xCoordinate][yCoordinate].setIcon(loadIcon(pieceColor + piece));
        this.repaint();
    }

    public static void main(String[] args) {
        Board board = new Board();
        //board.removePiece(1,1);
        //board.setPiece("White", "King", 1, 1);
    }
}
