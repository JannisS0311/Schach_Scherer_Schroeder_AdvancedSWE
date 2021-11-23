package _2_domainPackage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Tile extends JLabel {

    private Location location;

    private Piece piece;
    private Color pieceColor;

    private final Board board;

    public Tile(String pieceType, String pieceColor, Board board, Location location) {
        this.pieceColor = setColorFromString(pieceColor);
        setIcon(pieceColor, pieceType);
        this.board = board;
        this.piece = generatePiece(this, pieceType, pieceColor);
        this.location = location;

        this.setBackground(getColor());
        this.setMinimumSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    public Color getColor() {
        return TileColor.values()[(this.location.getRowCoordinate() + this.location.getColumnCoordinate()) % 2].awtColor;
    }

    public Piece generatePiece(Tile tile, String pieceType, String pieceColorAsString) {
        Color pieceColor = setColorFromString(pieceColorAsString);
        try {
            switch (pieceType) {
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
                    return new Rook(tile, this.board, pieceColor);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private Color setColorFromString(String colorName) {
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(colorName);
            color = (Color) field.get(null);
        } catch (Exception e) {
            color = null;
        }
        return color;
    }

    public void setIcon(String pieceColor, String pieceType) {
        this.setIcon(new PieceIcon().loadIcon(pieceColor, pieceType));
    }

    public String getPieceColorAsString() {
        if (this.pieceColor == Color.WHITE)
            return "WHITE";
        else if(this.pieceColor == Color.BLACK)
            return "BLACK";
        return null;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void setPieceColorString(String pieceColor) {
        this.pieceColor = setColorFromString(pieceColor);
    }
}
