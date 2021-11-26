package _2_domainPackage;

import javax.swing.*;

public class PieceIcon extends ImageIcon {

    private final String imagePath = "src/main/resources/chesspieces/";
    private final String pieceName;

    public PieceIcon(String pieceColor, String pieceType) {
        pieceName = pieceColor + pieceType;
    }

    public ImageIcon loadIcon() {
        ImageIcon icon = new ImageIcon(imagePath + pieceName + ".png");
        return icon;
    }

    public String getIconPath(){
        return imagePath + pieceName + ".png";
    }

}
