package _2_domainPackage;

import _1_adaptersPackage.Square;

import java.util.Arrays;

public class BoardState extends Object {

    private Square[][] squaresOfState;

    public BoardState(Square[][] squares) {
        squaresOfState = new Square[9][0];
        for (int i = 0; i < 9; i++) {
            squaresOfState[i] = Arrays.copyOf(squares[i], squares[i].length);
        }
    }

    public Square getSquare (int row, int col){
        return this.squaresOfState[row][col];
    }

    @Override
    public String toString() {
        return "Saved BoardState No. ";
    }
}
