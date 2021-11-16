package control;

import gui.Board;
import gui.Tile;
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
    public boolean setLocation(int newRowCoordinate, int newColumnCoordinate) {
        Tile newTile = this.board.getTile(newColumnCoordinate, newRowCoordinate);
        if (checkIfMoveIsAllowed(newTile)) {
            this.actualTile.removeIcon();
            this.actualTile.removePiece();
            newTile.setIcon(loadIcon(pieceColor + "Pawn"));
            newTile.setPiece( this.board, newTile,"Pawn", "WHITE");
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
        System.out.println(checkIfMoveIsForwards(newTile));
        System.out.println(checkIfStepNumberIsAllowed(newTile));
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
        System.out.println(pieceColor == Color.WHITE);
        System.out.println(actualTile.getColumnCoordinate());
        System.out.println(newTile.getColumnCoordinate());
        System.out.println(actualTile.getRowCoordinate());
        System.out.println(newTile.getRowCoordinate());
        if(pieceColor == Color.BLACK && (actualTile.getColumnCoordinate() > newTile.getColumnCoordinate())){
            return true;
        }
        else if(pieceColor == Color.WHITE && (actualTile.getColumnCoordinate() < newTile.getColumnCoordinate())){
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
        if(actualTile.getColumnCoordinate() == 0 && newTile.getColumnCoordinate() == 2){
            return true;
        }
        else if(actualTile.getColumnCoordinate() == 7 && newTile.getColumnCoordinate() == 5){
            return true;
        }
        return false;
    }

    private boolean checkIfOneStep(Tile newTile){
        if(actualTile.getColumnCoordinate() == newTile.getColumnCoordinate() + 1){
            return true;
        }
        else if(actualTile.getColumnCoordinate() == newTile.getColumnCoordinate() - 1){
            return true;
        }
        return false;
    }

    private boolean checkIfInTheSameColumn(Tile newTile) {
        Integer oldYCoordinate = this.actualTile.getColumnCoordinate();
        Integer newYCoordinate = newTile.getColumnCoordinate();
        if (oldYCoordinate.equals(newYCoordinate)) {
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
