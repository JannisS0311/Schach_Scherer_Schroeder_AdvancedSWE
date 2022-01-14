package _1_adaptersPackage;

import _2_domainPackage.Tile;

import javax.swing.*;
import java.awt.*;

public class Square extends JLabel {

    private Tile tile = null;

    private String labeling;


    public Square(Tile tile) {
        this.tile = tile;
        this.setBackground(tile.getBackgroundColor());
        this.setIcon(tile.getIcon());
        this.setMinimumSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    public Square(String labeling) {
        this.labeling = labeling;
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setText(labeling);
        this.setFont(this.getFont().deriveFont(18f));

        this.setMinimumSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    public Tile getTile() {
        return tile;
    }
}
