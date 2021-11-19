package adaptersPackage.gui;

import domainPackage.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Tile extends JLabel {

    private String imagePath = "src/main/resources/chesspieces/";

    private static final Color tileColor1 = Color.darkGray;
    private static final Color tileColor2 = Color.WHITE;

    private int rowCoordinate;
    private int columnCoordinate;

    private Piece piece;

    private Board board;

    public Tile(Color tileColor, String tileIcon, String pieceColor, Board board, int rowCoordinate, int columnCoordinate) {
        this.setBackground(tileColor);
        this.setIcon(loadIcon(pieceColor + tileIcon));
        this.piece = generatePiece(this.board,this, tileIcon, pieceColor);
        this.board = board;
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;

        this.setMinimumSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    private ImageIcon loadIcon(String fileName) {
        ImageIcon icon = new ImageIcon(imagePath + fileName + ".png");
        return icon;
    }

    public Piece generatePiece(Board board, Tile tile, String imageIcon, String pieceColorAsString){
        Color pieceColor = setColorFromString(pieceColorAsString);
        try {
            switch(imageIcon){
                case "Pawn": return new Pawn(tile, this.board, pieceColor);
                case "King": return new King(tile, this.board, pieceColor);
                case "Queen": return new Queen(tile, this.board, pieceColor);
                case "Bishop": return new Bishop(tile, this.board, pieceColor);
                case "Knight": return new Knight(tile, this.board, pieceColor);
                case "Rock": return new Rock(tile, this.board, pieceColor);
                default: return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    private Color setColorFromString(String colorName){
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(colorName);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
        return color;
    }

    public static Color getColor1() {
        return tileColor1;
    }

    public static Color getTileColor2() {
        return tileColor2;
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public int getColumnCoordinate() {
        return columnCoordinate;
    }

    public boolean isOccupied() {
        if (this.getPiece() == null) {
            return false;
        }
        return true;
    }

    public Piece getPiece() {
        return piece;
    }

    public void removePiece(){
        this.piece = null;
    }

    public void removeIcon(){
        this.setIcon(null);
    }
}
