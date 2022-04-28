package _0_mainPackage;

import _1_adaptersPackage.GameFrame;
import _2_domainPackage.Board;
import _2_domainPackage.Game;
import _2_domainPackage.Location;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game();
        GameFrame gameFrame = new GameFrame(board, game);
        gameFrame.init();
    }
}
