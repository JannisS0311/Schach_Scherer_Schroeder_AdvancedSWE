package _2_domainPackage;

import java.awt.Color;

public enum TileColor {
    WHITE(Color.WHITE),
    GRAY(Color.DARK_GRAY);

    public final Color awtColor;

    TileColor(Color awtColor) {
        this.awtColor = awtColor;
    }
}