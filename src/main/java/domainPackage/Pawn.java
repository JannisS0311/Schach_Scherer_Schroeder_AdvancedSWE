package domainPackage;

import adaptersPackage.gui.Board;
import adaptersPackage.gui.Tile;
import javax.swing.*;
import java.awt.*;

public class Pawn implements Piece{

    Tile actualTile;
    Board board;
    Color pieceColor;

    private String imagePath = "src/main/resources/chesspieces/";

    public Pawn(Tile actualTile, Board board, Color pieceColor) {
        this.actualTile = actualTile;
        this.board = board;
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean setLocation(int newRowCoordinate, int newColumnCoordinate, Board board) {
        Tile newTile = board.getTile(newRowCoordinate, newColumnCoordinate);
        String color = (this.pieceColor.toString().equals("java.awt.Color[r=255,g=255,b=255]") ? "WHITE" : "BLACK");
        if (checkIfMoveIsAllowed(newTile)) {
            board.getTile(newRowCoordinate, newColumnCoordinate).setIcon(loadIcon(color + "Pawn"));
            board.getTile(newRowCoordinate, newColumnCoordinate).setPiece(this.actualTile.getPiece());
            this.actualTile.removeIcon();
            this.actualTile.removePiece();
            this.actualTile.setLocation(newRowCoordinate, newColumnCoordinate);
            return true;
        }
        return false;
    }

    private ImageIcon loadIcon(String fileName) {
        ImageIcon icon = new ImageIcon(imagePath + fileName + ".png");
        return icon;
    }

    public boolean checkIfMoveIsAllowed(Tile newTile) {
        // normal move, don't beat your enemies piece
        if (checkIfInTheSameColumn(newTile) && checkIfMoveIsForwards(newTile) && checkIfStepNumberIsAllowed(newTile)) {
            return checkIfNewTileIsEmpty(newTile);
        }
        // beat your enemies piece TODO
        else if(true){
            return false;
        }
        return false;
    }

    private boolean checkIfMoveIsForwards(Tile newTile){
        if(pieceColor == Color.BLACK && (actualTile.getRowCoordinate() > newTile.getRowCoordinate())){
            return true;
        }
        else if(pieceColor == Color.WHITE && (actualTile.getRowCoordinate() < newTile.getRowCoordinate())){
            return true;
        }
        return false;
    }

    private boolean checkIfStepNumberIsAllowed(Tile newTile){
        if(checkIfInitiallyTwoSteps(newTile)){
            return true;
        }
        else if(checkIfOneStep(newTile)){
            return true;
        }
        return false;
    }

    private boolean checkIfInitiallyTwoSteps(Tile newTile){
        if(actualTile.getRowCoordinate() == 1 && newTile.getRowCoordinate() == 3){
            return true;
        }
        else if(actualTile.getRowCoordinate() == 6 && newTile.getRowCoordinate() == 4){
            return true;
        }
        return false;
    }

    private boolean checkIfOneStep(Tile newTile){
        if(actualTile.getRowCoordinate() == newTile.getRowCoordinate() + 1){
            return true;
        }
        else if(actualTile.getRowCoordinate() == newTile.getRowCoordinate() - 1){
            return true;
        }
        return false;
    }

    private boolean checkIfInTheSameColumn(Tile newTile) {
        Integer oldColumnNumber = this.actualTile.getColumnCoordinate();
        Integer newColumnNumber = newTile.getColumnCoordinate();
        if (oldColumnNumber.equals(newColumnNumber)) {
            return true;
        }
        return false;
    }

    public boolean checkIfNewTileIsEmpty(Tile newTile) {
        if (newTile.getPiece() == null) {
            return true;
        }
        return false;
    }
}
