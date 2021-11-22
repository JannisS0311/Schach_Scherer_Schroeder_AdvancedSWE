package _2_domainPackage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Tile extends JLabel {

    private Location location = new Location();

    private Piece piece;
    private String pieceType;
    private Color pieceColor;

    private Board board;

    public Tile(Color tileColor, String pieceType, String pieceColor, Board board, int rowCoordinate, int columnCoordinate) {
        this.setBackground(tileColor);
        this.pieceColor = setColorFromString(pieceColor);
        this.pieceType = pieceType;
        setIcon(pieceColor, pieceType);
        this.board = board;
        this.piece = generatePiece(this.board, this, pieceType, pieceColor);
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
                    return new Rook(tile, this.board, pieceColor);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void updateTileAfterMove(String pieceType, String pieceColor){
        this.setPiece(generatePiece(this.board, this, pieceType, pieceColor));
        this.setIcon(pieceType, pieceColor);
        this.setPieceColor(setColorFromString(pieceColor));
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

    public String getPieceColorAsString(){
        if(this.pieceColor == Color.WHITE){
            return "WHITE";
        }
        return "BLACK";
    }

    public Piece getPiece() {
        return piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public boolean tileIsEmpty(){
        if(this.piece == null){
            return true;
        }
        return false;
    }

    public void setPieceColor(Color pieceColor){
        this.pieceColor = pieceColor;
    }

    public void setPieceColorString(String pieceColor){
        this.pieceColor = setColorFromString(pieceColor);
    }

    public String getPieceType() {
        return pieceType;
    }

    public Board getBoard() {
        return board;
    }
}
