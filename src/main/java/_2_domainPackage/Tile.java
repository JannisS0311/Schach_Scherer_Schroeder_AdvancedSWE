package _2_domainPackage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Tile {

    private Location location;

    private Piece piece;
    private String PieceType;
    private Color pieceColor;
    private Color backgroundColor;
    private ImageIcon icon;
    private final Board board;

    public Tile(String pieceType, String pieceColor, Board board, Location location) {
        this.pieceColor = setColorFromString(pieceColor);
        this.PieceType = pieceType;
        this.board = board;
        this.piece = generatePiece(this, pieceType, pieceColor);
        this.icon = new PieceIcon(pieceColor, pieceType).loadIcon();
        this.location = location;

        this.backgroundColor = getColor();
    }

    public Tile(String pieceType, String pieceColor, Board board, Location location, Piece piece) {
        this.pieceColor = setColorFromString(pieceColor);
        this.PieceType = pieceType;
        this.board = board;
        this.piece = piece;
        this.icon = new PieceIcon(pieceColor, pieceType).loadIcon();
        this.location = location;

        this.backgroundColor = getColor();
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

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getPieceType(){
        return PieceType;
    }

    public Location getLocation() {
        return this.location;
    }
}
