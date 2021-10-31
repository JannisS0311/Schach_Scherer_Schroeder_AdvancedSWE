package gui;

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {

    private static final Color color1 = Color.darkGray;
    private static final Color color2 = Color.WHITE;

    private int xCoordinate;
    private int yCoordinate;

    public Tile(Color tileColor, ImageIcon tileIcon) {
        this.setBackground(tileColor);
        this.setIcon(tileIcon);
        System.out.println(tileIcon);
        this.setMinimumSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
       this. setOpaque(true);
    }

    public static Color getColor1() {
        return color1;
    }

    public static Color getColor2() {
        return color2;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
}
