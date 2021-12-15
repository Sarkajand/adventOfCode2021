package main.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HeatMap {

    List<Location> locations;

    public HeatMap() {
        this.locations = new ArrayList<>();
    }

    public void add(int value, boolean newLine) {
        Location left;
        Location up;

        if (newLine) {
            left = null;
            up = getFirstFromLastRow();
        } else {
            left = getLastFromLastRow();
            up = left != null && left.getUp() != null ? left.getUp().getRight() : null;
        }

        Location location = new Location(value);
        location.setUp(up);
        location.setLeft(left);
        locations.add(location);

        if (up != null) {
            up.setDown(location);
        }
        if (left != null) {
            left.setRight(location);
        }
    }

    public List<Location> getLowLocations() {
        return locations.stream().filter(Location::isLowLocation).collect(Collectors.toList());
    }

    private Location getFirstFromLastRow() {
        return locations.stream().filter(location -> location.getDown() == null && location.getLeft() == null).findFirst().orElse(null);
    }

    private Location getLastFromLastRow() {
        return locations.stream().filter(location -> location.getDown() == null && location.getRight() == null).max(Location::sortByRow).orElse(null);
    }
}
