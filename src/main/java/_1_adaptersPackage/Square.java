package _1_adaptersPackage;

import _2_domainPackage.FourSided;
import _2_domainPackage.Tile;

import javax.swing.*;
import java.awt.*;

public class Square extends JLabel implements FourSided {

    private Tile tile = null;

    private String labeling;
    private final Dimension dimension = new Dimension(20,20);


    public Square(Tile tile) {
        this.tile = tile;
        this.setBackground(tile.getBackgroundColor());
        this.setIcon(tile.getIcon());
        this.setMinimumSize(dimension);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    public Square(String labeling) {
        this.labeling = labeling;
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setText(labeling);
        this.setFont(this.getFont().deriveFont(18f));

        this.setMinimumSize(dimension);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public String getLabeling() {
        return labeling;
    }
}
