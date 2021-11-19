package _2_domainPackage;

import javax.swing.*;

public class PieceIcon extends ImageIcon {

    private String imagePath = "src/main/resources/chesspieces/";

    public PieceIcon() {
    }

    public ImageIcon loadIcon(String pieceColor, String pieceType) {
        String pieceName = pieceColor + pieceType;
        ImageIcon icon = new ImageIcon(imagePath + pieceName + ".png");
        return icon;
    }

}
