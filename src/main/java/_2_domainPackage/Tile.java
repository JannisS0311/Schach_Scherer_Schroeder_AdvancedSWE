package _2_domainPackage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Tile extends JLabel {

    private Location location = new Location();

    private Piece piece;

    private Board board;

    public Tile(Color tileColor, String pieceType, String pieceColor, Board board, int rowCoordinate, int columnCoordinate) {
        this.setBackground(tileColor);

        setIcon(pieceColor, pieceType);

        this.piece = generatePiece(this.board, this, pieceType, pieceColor);

        this.board = board;

        this.location.setLocation(rowCoordinate, columnCoordinate);

        this.setMinimumSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    public Piece generatePiece(Board board, Tile tile, String imageIcon, String pieceColorAsString) {
        Color pieceColor = setColorFromString(pieceColorAsString);
        try {
            switch (imageIcon) {
                case "Pawn":
                    return new Pawn(tile, this.board, pieceColor);
                case "King":
                    return new King(tile, this.board, pieceColor);
                case "Queen":
                    return new Queen(tile, this.board, pieceColor);
                case "Bishop":
                    return new Bishop(tile, this.board, pieceColor);
                case "Knight":
                    return new Knight(tile, this.board, pieceColor);
                case "Rock":
                    return new Rock(tile, this.board, pieceColor);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private Color setColorFromString(String colorName) {
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(colorName);
            color = (Color) field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
        return color;
    }

    public void setIcon(String pieceColor, String pieceType) {
        this.setIcon(new PieceIcon().loadIcon(pieceColor, pieceType));
    }

    public static Color getColor(int colorNumber) {
        if(colorNumber == 0 || colorNumber == 1) {
            return TileColor.values()[colorNumber].awtColor;
        }
        return null;
    }

    public int getRowCoordinate() {
        return this.location.getRowCoordinate();
    }

    public int getColumnCoordinate() {
        return this.location.getColumnCoordinate();
    }

    public Piece getPiece() {
        return piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public void removeIcon() {
        this.setIcon(null);
    }

    public boolean tileIsEmpty(){
        if(this.piece == null){
            return true;
        }
        return false;
    }
}
