package _2_domainPackage;

public class Location {

    int rowCoordinate;
    int columnCoordinate;

    public void setLocation(int newRowLocation, int newColumnLocation){
        this.rowCoordinate = newRowLocation;
        this.columnCoordinate = newColumnLocation;
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public int getColumnCoordinate() {
        return columnCoordinate;
    }
}
