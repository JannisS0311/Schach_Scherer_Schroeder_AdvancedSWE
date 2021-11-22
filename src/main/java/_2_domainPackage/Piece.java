package _2_domainPackage;

import java.util.ArrayList;

public interface Piece {

    String imagePath = "src/main/resources/chesspieces/";

    boolean isMoveOkay(Location oldLocation, Location newLocation);

    ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation);

}
