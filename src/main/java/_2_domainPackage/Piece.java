package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public interface Piece {

    String imagePath = "src/main/resources/chesspieces/";

    boolean isMoveOkay(Tile oldTile, Tile newTile);

    ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation);

    Color getPieceColor();

    boolean getHasMoved();
    void setHasMoved();

    boolean getEnPassant();
    void setEnPassant(boolean enPassant);
    
    private boolean areTilesBetweenEmtpy(ArrayList<Tile> tilesBetween){
        for (Tile tile: tilesBetween
        ) {
            if(!(tile.isEmpty()))
                return false;
        }
        return true;
    };

}
