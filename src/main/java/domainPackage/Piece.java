package domainPackage;

import adaptersPackage.gui.Board;

public interface Piece {
    
    public boolean setLocation(int newRowCoordinate, int newColumnCoordinate, Board board);
}
