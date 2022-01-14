package _2_domainPackage;

import java.util.ArrayList;

public interface Piece {

    String imagePath = "src/main/resources/chesspieces/";

    boolean isMoveOkay(Location oldLocation, Location newLocation);

    ArrayList<Location> areTilesBetweenEmpty(Location oldLocation, Location newLocation);
    
    private boolean areTilesBetweenEmtpy(ArrayList<Tile> tilesBetween){
        for (Tile tile: tilesBetween
        ) {
            if(!(tile.isEmpty()))
                return false;
        }
        return true;
    };

}
