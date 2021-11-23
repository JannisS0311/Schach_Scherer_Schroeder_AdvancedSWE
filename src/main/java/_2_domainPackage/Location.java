package _2_domainPackage;

import java.util.Objects;

public class Location {

    int rowCoordinate;
    int columnCoordinate;

    public Location(int rowCoordinate, int columnCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    public Location() {
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public int getColumnCoordinate() {
        return columnCoordinate;
    }

    public void setLocation(int rowCoordinate, int columnCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Location))
            return false;

        Location location = (Location) object;
        return location.getRowCoordinate() == this.getRowCoordinate()
                && location.getColumnCoordinate() == this.getColumnCoordinate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowCoordinate, columnCoordinate);
    }
}
