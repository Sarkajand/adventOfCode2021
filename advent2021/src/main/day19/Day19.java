package main.day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

    public void getAnswers(String filePath) throws IOException {
        Queue<Scanner> scanners = readInput(filePath);
        computeDistances(scanners);
        TrenchMap trenchMap = new TrenchMap();
        Scanner scanner = scanners.remove();
        trenchMap.setFirsScanner(scanner);
        trenchMap.addScanner(scanner);
        trenchMap.addBeacons(scanner.getBeacons());

        while (!scanners.isEmpty()) {
            scanner = scanners.remove();
            Map<Beacon, Beacon> sameBeacons = getSameBeacons(scanner, trenchMap.getBeacons());

            if (sameBeacons.size() > 7 ) {
                List<Beacon> beaconsToAdd = new ArrayList<>(scanner.getBeacons());
                beaconsToAdd.removeAll(sameBeacons.values());
                calculatedCoordinatesForScanner(scanner, sameBeacons);

                Map.Entry<Beacon, Beacon> entry = sameBeacons.entrySet().iterator().next();
                setReverseAxis(scanner, entry.getKey(), entry.getValue());
                trenchMap.addScanner(scanner);

                for (Beacon beacon : beaconsToAdd) {
                    Beacon recalculateBeacon = recalculateBeacon(scanner, beacon);
                    trenchMap.addBeacon(recalculateBeacon);
                }

                removeDistances(trenchMap.getBeacons());
                computeDistance(trenchMap.getBeacons());

            } else {
                scanners.add(scanner);
            }
        }

        System.out.println(trenchMap.getBeacons().size());

        int maxDistance = Integer.MIN_VALUE;

        for (Scanner scanner1 : trenchMap.getScanners()) {
            for (Scanner scanner2 : trenchMap.getScanners()) {
                int distanceX = Math.max(scanner1.getX(), scanner2.getX()) - Math.min(scanner1.getX(), scanner2.getX());
                int distanceY = Math.max(scanner1.getY(), scanner2.getY()) - Math.min(scanner1.getY(), scanner2.getY());
                int distanceZ = Math.max(scanner1.getZ(), scanner2.getZ()) - Math.min(scanner1.getZ(), scanner2.getZ());
                int distance = distanceX + distanceY + distanceZ;
                maxDistance = Math.max(maxDistance, distance);
            }
        }

        System.out.println(maxDistance);
    }


    private Beacon recalculateBeacon(Scanner scanner, Beacon beacon) {
        Beacon recalculatedBeacon = new Beacon();
        recalculatedBeacon.setX(calculateCoordinates(scanner.getRecalculatedX(), scanner.getX(), beacon, scanner.isReverseX()));
        recalculatedBeacon.setY(calculateCoordinates(scanner.getRecalculatedY(), scanner.getY(), beacon, scanner.isReverseY()));
        recalculatedBeacon.setZ(calculateCoordinates(scanner.getRecalculatedZ(), scanner.getZ(), beacon, scanner.isReverseZ()));

        return recalculatedBeacon;
    }

    private int calculateCoordinates(String calculateRule, int ScannerValue, Beacon beacon, boolean reverse) {
        switch (calculateRule) {
            case "-x":
                return reverse ? (ScannerValue + beacon.getX()) * -1 : ScannerValue + beacon.getX();
            case "+x":
                return reverse ? (ScannerValue - beacon.getX()) * -1 : ScannerValue - beacon.getX();
            case "-y":
                return reverse ? (ScannerValue + beacon.getY()) * -1 : ScannerValue + beacon.getY();
            case "+y":
                return reverse ? (ScannerValue - beacon.getY()) * -1 : ScannerValue - beacon.getY();
            case "-z":
                return reverse ? (ScannerValue + beacon.getZ()) * -1 : ScannerValue + beacon.getZ();
            case "+z":
                return reverse ? (ScannerValue - beacon.getZ()) * -1 : ScannerValue - beacon.getZ();
            default:
                return 0;
        }
    }

    private void setReverseAxis(Scanner scanner, Beacon mapBeacon, Beacon sameBeacon) {
        Beacon recalculatedBeacon = recalculateBeacon(scanner, sameBeacon);
        if (mapBeacon.getX() != recalculatedBeacon.getX()) {
            scanner.setReverseX(true);
        }
        if (mapBeacon.getY() != recalculatedBeacon.getY()) {
            scanner.setReverseY(true);
        }
        if (mapBeacon.getZ() != recalculatedBeacon.getZ()) {
            scanner.setReverseZ(true);
        }
    }

    private void calculatedCoordinatesForScanner(Scanner scanner, Map<Beacon, Beacon> beaconMap) {
        Map<Beacon, List<Integer>> beaconListXMap = new HashMap<>();
        Map<Beacon, List<Integer>> beaconListYMap = new HashMap<>();
        Map<Beacon, List<Integer>> beaconListZMap = new HashMap<>();

        for (Map.Entry<Beacon, Beacon> entry : beaconMap.entrySet()) {
            List<Integer> possibleX = new ArrayList<>();
            possibleX.add(entry.getKey().getX() - entry.getValue().getX());
            possibleX.add(entry.getKey().getX() + entry.getValue().getX());
            possibleX.add(entry.getKey().getX() - entry.getValue().getY());
            possibleX.add(entry.getKey().getX() + entry.getValue().getY());
            possibleX.add(entry.getKey().getX() - entry.getValue().getZ());
            possibleX.add(entry.getKey().getX() + entry.getValue().getZ());
            beaconListXMap.put(entry.getKey(), possibleX);

            List<Integer> possibleY = new ArrayList<>();
            possibleY.add(entry.getKey().getY() - entry.getValue().getX());
            possibleY.add(entry.getKey().getY() + entry.getValue().getX());
            possibleY.add(entry.getKey().getY() - entry.getValue().getY());
            possibleY.add(entry.getKey().getY() + entry.getValue().getY());
            possibleY.add(entry.getKey().getY() - entry.getValue().getZ());
            possibleY.add(entry.getKey().getY() + entry.getValue().getZ());
            beaconListYMap.put(entry.getKey(), possibleY);

            List<Integer> possibleZ = new ArrayList<>();
            possibleZ.add(entry.getKey().getZ() - entry.getValue().getX());
            possibleZ.add(entry.getKey().getZ() + entry.getValue().getX());
            possibleZ.add(entry.getKey().getZ() - entry.getValue().getY());
            possibleZ.add(entry.getKey().getZ() + entry.getValue().getY());
            possibleZ.add(entry.getKey().getZ() - entry.getValue().getZ());
            possibleZ.add(entry.getKey().getZ() + entry.getValue().getZ());
            beaconListZMap.put(entry.getKey(), possibleZ);
        }

        Map.Entry<Beacon, List<Integer>> entryX = beaconListXMap.entrySet().iterator().next();
        List<Integer> possibleX = entryX.getValue();
        for (Integer x : possibleX) {
            int xCount = 0;
            for (Map.Entry<Beacon, List<Integer>> entry : beaconListXMap.entrySet()) {
                if (entry.getValue().contains(x)) {
                    xCount++;
                }
            }

            if (xCount > 11) {
                scanner.setX(x);
                break;
            }
        }

        Map.Entry<Beacon, List<Integer>> entryY = beaconListYMap.entrySet().iterator().next();
        List<Integer> possibleY = entryY.getValue();
        for (Integer y : possibleY) {
            int xCount = 0;
            for (Map.Entry<Beacon, List<Integer>> entry : beaconListYMap.entrySet()) {
                if (entry.getValue().contains(y)) {
                    xCount++;
                }
            }

            if (xCount  > 11) {
                scanner.setY(y);
                break;
            }
        }

        Map.Entry<Beacon, List<Integer>> entryZ = beaconListZMap.entrySet().iterator().next();
        List<Integer> possibleZ = entryZ.getValue();
        for (Integer z : possibleZ) {
            int xCount = 0;
            for (Map.Entry<Beacon, List<Integer>> entry : beaconListZMap.entrySet()) {
                if (entry.getValue().contains(z)) {
                    xCount++;
                }
            }

            if (xCount > 11) {
                scanner.setZ(z);
                break;
            }
        }

        Map.Entry<Beacon, Beacon> entryBeacons = beaconMap.entrySet().iterator().next();
        if (entryBeacons.getKey().getX() - entryBeacons.getValue().getX() == scanner.getX()) {
            scanner.setRecalculatedX("-x");
        }
        if (entryBeacons.getKey().getX() + entryBeacons.getValue().getX() == scanner.getX()) {
            scanner.setRecalculatedX("+x");
        }
        if (entryBeacons.getKey().getX() - entryBeacons.getValue().getY() == scanner.getX()) {
            scanner.setRecalculatedX("-y");
        }
        if (entryBeacons.getKey().getX() + entryBeacons.getValue().getY() == scanner.getX()) {
            scanner.setRecalculatedX("+y");
        }
        if (entryBeacons.getKey().getX() - entryBeacons.getValue().getZ() == scanner.getX()) {
            scanner.setRecalculatedX("-z");
        }
        if (entryBeacons.getKey().getX() + entryBeacons.getValue().getZ() == scanner.getX()) {
            scanner.setRecalculatedX("+z");
        }


        if (entryBeacons.getKey().getY() - entryBeacons.getValue().getX() == scanner.getY()) {
            scanner.setRecalculatedY("-x");
        }
        if (entryBeacons.getKey().getY() + entryBeacons.getValue().getX() == scanner.getY()) {
            scanner.setRecalculatedY("+x");
        }
        if (entryBeacons.getKey().getY() - entryBeacons.getValue().getY() == scanner.getY()) {
            scanner.setRecalculatedY("-y");
        }
        if (entryBeacons.getKey().getY() + entryBeacons.getValue().getY() == scanner.getY()) {
            scanner.setRecalculatedY("+y");
        }
        if (entryBeacons.getKey().getY() - entryBeacons.getValue().getZ() == scanner.getY()) {
            scanner.setRecalculatedY("-z");
        }
        if (entryBeacons.getKey().getY() + entryBeacons.getValue().getZ() == scanner.getY()) {
            scanner.setRecalculatedY("+z");
        }


        if (entryBeacons.getKey().getZ() - entryBeacons.getValue().getX() == scanner.getZ()) {
            scanner.setRecalculatedZ("-x");
        }
        if (entryBeacons.getKey().getZ() + entryBeacons.getValue().getX() == scanner.getZ()) {
            scanner.setRecalculatedZ("+x");
        }
        if (entryBeacons.getKey().getZ() - entryBeacons.getValue().getY() == scanner.getZ()) {
            scanner.setRecalculatedZ("-y");
        }
        if (entryBeacons.getKey().getZ() + entryBeacons.getValue().getY() == scanner.getZ()) {
            scanner.setRecalculatedZ("+y");
        }
        if (entryBeacons.getKey().getZ() - entryBeacons.getValue().getZ() == scanner.getZ()) {
            scanner.setRecalculatedZ("-z");
        }
        if (entryBeacons.getKey().getZ() + entryBeacons.getValue().getZ() == scanner.getZ()) {
            scanner.setRecalculatedZ("+z");
        }
    }

    private Map<Beacon, Beacon> getSameBeacons(Scanner scanner, List<Beacon> beacons) {
        Map<Beacon, Beacon> map = new HashMap<>();

        for (Beacon scannerBeacon : scanner.getBeacons()) {
            for (Beacon mapBeacon : beacons) {
                int sameValueCount = 0;
                for (Integer distance : scannerBeacon.getDistances()) {
                    if (mapBeacon.getDistances().contains(distance)) {
                        sameValueCount++;
                    }
                    if (sameValueCount == 11) {
                        map.put(mapBeacon, scannerBeacon);
                    }
                }
            }
        }
        return map;
    }

    private void computeDistances(Queue<Scanner> scanners) {
        for (Scanner scanner : scanners) {
            computeDistance(scanner.getBeacons());
        }
    }

    private void computeDistance(List<Beacon> beacons) {
        for (Beacon beacon : beacons) {
            for (Beacon toBeacon : beacons) {
                int distanceX = Math.max(beacon.getX(), toBeacon.getX()) - Math.min(beacon.getX(), toBeacon.getX());
                int distanceY = Math.max(beacon.getY(), toBeacon.getY()) - Math.min(beacon.getY(), toBeacon.getY());
                int distanceZ = Math.max(beacon.getZ(), toBeacon.getZ()) - Math.min(beacon.getZ(), toBeacon.getZ());
                int distance = distanceX + distanceY + distanceZ;
                if (distance != 0)
                    beacon.addDistance(distance);
            }
        }
    }

    private void removeDistances(List<Beacon> beacons) {
        for (Beacon beacon : beacons) {
            beacon.setDistances(new ArrayList<>());
        }
    }

    private Queue<Scanner> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        Pattern coordinatesPattern = Pattern.compile("(-?\\d+),(-?\\d+),(-?\\d+)");
        Pattern scannerPattern = Pattern.compile("--- (scanner \\d+) ---");

        Queue<Scanner> scanners = new LinkedList<>();
        Scanner currentScanner = new Scanner();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                Matcher scannerMatcher = scannerPattern.matcher(currentLine);
                Matcher coordinatesMatcher = coordinatesPattern.matcher(currentLine);

                if (scannerMatcher.find()) {
                    Scanner scanner = new Scanner(scannerMatcher.group(1));
                    currentScanner = scanner;
                    scanners.add(scanner);
                } else if (coordinatesMatcher.find()) {
                    Beacon beacon = new Beacon(currentScanner, Integer.parseInt(coordinatesMatcher.group(1)), Integer.parseInt(coordinatesMatcher.group(2)), Integer.parseInt(coordinatesMatcher.group(3)));
                    currentScanner.addBeacon(beacon);
                }

                currentLine = reader.readLine();
            }
        }
        return scanners;
    }
}
