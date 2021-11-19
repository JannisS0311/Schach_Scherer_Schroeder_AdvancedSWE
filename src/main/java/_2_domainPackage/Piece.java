package _2_domainPackage;

import java.util.ArrayList;

public interface Piece {

    public String imagePath = "src/main/resources/chesspieces/";

    public boolean isMoveOkay(Location oldLocation, Location newLocation);

    public ArrayList<Location> getTilesInBetween(Location oldLocation, Location newLocation);

}
