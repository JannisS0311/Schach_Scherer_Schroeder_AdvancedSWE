package _2_domainPackage;

import java.awt.*;
import java.util.Arrays;

public class BoardState extends Object {

    private FourSided[][] squaresOfState;
    private Color turn;

    public BoardState(FourSided[][] squares, Color turn) {
        this.turn = turn;
        squaresOfState = new FourSided[9][0];
        for (int i = 0; i < 9; i++) {
            squaresOfState[i] = Arrays.copyOf(squares[i], squares[i].length);
        }
    }

    public FourSided getSquare (int row, int col){
        return this.squaresOfState[row][col];
    }

    public Color getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return "Saved BoardState No. ";
    }
}
