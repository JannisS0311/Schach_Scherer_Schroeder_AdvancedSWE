package _0_mainPackage;

import _2_domainPackage.Board;
import _1_adaptersPackage.GameFrame;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        GameFrame gameFrame = new GameFrame(board);
    }
}
