package _2_domainPackage;

import java.awt.*;
import java.util.ArrayList;

public interface Piece {

    boolean isMoveOkay(Tile oldTile, Tile newTile);

    ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation);

    Color getPieceColor();

    boolean getHasMoved();
    void setHasMoved();

    boolean getEnPassant();
    void setEnPassant(boolean enPassant);

}
