package _1_adaptersPackage;

import _2_domainPackage.Board;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private final Board board;

    public GameFrame(Board board) throws HeadlessException {
        this.board = board;
        this.init();
    }

    private void init() {
        this.setLayout(new GridLayout(8, 8));
        this.setSize(new Dimension(500, 500));

        this.fillTiles();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void fillTiles() {
        for (int i = 0; i < 64; i++) {
            int row = i / 8;
            int col = i % 8;
            this.add(this.board.getTile(row, col));
        }
    }
}
