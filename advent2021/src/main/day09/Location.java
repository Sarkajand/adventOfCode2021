package main.day09;

import java.util.Set;

public class Location {
    private Location left;
    private Location up;
    private Location right;
    private Location down;
    private int value;

    Location(int value) {
        this.value = value;
    }

    public static int sortByRow(Location location, Location location1) {
        return location.rowNumber() - location1.rowNumber();
    }

    public boolean isLowLocation() {
        return (left == null || left.getValue() > value) &&
                (up == null || up.getValue() > value) &&
                (right == null || right.getValue() > value) &&
                (down == null || down.getValue() > value);
    }

    public void addToBasin (Set<Location> basinHeatMap, Location location) {
        if (location != null && location.getValue() != 9) {
            basinHeatMap.add(location);

            if (!basinHeatMap.contains(location.getLeft())) {
                addToBasin(basinHeatMap, location.getLeft());
            }
            if (!basinHeatMap.contains(location.getUp())) {
                addToBasin(basinHeatMap, location.getUp());
            }
            if (!basinHeatMap.contains(location.getRight())) {
                addToBasin(basinHeatMap, location.getRight());
            }
            if (!basinHeatMap.contains(location.getDown())) {
                addToBasin(basinHeatMap, location.getDown());
            }
        }
    }


    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    public Location getUp() {
        return up;
    }

    public void setUp(Location up) {
        this.up = up;
    }

    public Location getRight() {
        return right;
    }

    public void setRight(Location right) {
        this.right = right;
    }

    public Location getDown() {
        return down;
    }

    public void setDown(Location down) {
        this.down = down;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int rowNumber() {
        int rowNumber = 1;
        Location up = this.up;
        while (up != null) {
            rowNumber++;
            up = up.getUp();
        }
        return rowNumber;
    }
}
