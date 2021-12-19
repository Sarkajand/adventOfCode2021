package main.day19;

import java.util.ArrayList;
import java.util.List;

public class TrenchMap {

    private Scanner firsScanner;
    private List<Scanner> scanners = new ArrayList<>();
    private List<Beacon> beacons = new ArrayList<>();

    public Scanner getFirsScanner() {
        return firsScanner;
    }

    public void setFirsScanner(Scanner firsScanner) {
        this.firsScanner = firsScanner;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public void addScanner(Scanner scanner) {
        this.scanners.add(scanner);
    }

    public void addBeacons(List<Beacon> beacons) {
        this.beacons.addAll(beacons);
    }

    public void addBeacon(Beacon beacon) {
        this.beacons.add(beacon);
    }

    public List<Scanner> getScanners() {
        return scanners;
    }
}
