package main.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day09 {

    public void countFirstPart(String filePath) throws IOException {
        HeatMap heatMap = readInput(filePath);

        List<Location> lowLocations = heatMap.getLowLocations();

        int riskSum = lowLocations.stream().map(location -> location.getValue() + 1).mapToInt(Integer::intValue).sum();
        System.out.println(riskSum);
    }

    public void countSecondPart(String filePath) throws IOException {
        HeatMap heatMap = readInput(filePath);

        List<Location> lowLocations = heatMap.getLowLocations();

        List<Integer> basinSizes = new ArrayList<>();

        for (Location lowLocation : lowLocations) {
            Set<Location> basinHeatMap = new HashSet<>();
            lowLocation.addToBasin(basinHeatMap, lowLocation);
            basinSizes.add(basinHeatMap.size());
        }

        basinSizes.sort(Collections.reverseOrder());

        System.out.println(basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2));
    }

    private HeatMap readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        HeatMap heatMap = new HeatMap();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                int[] values = currentLine.chars().map(Character::getNumericValue).toArray();
                boolean newLine = true;

                for (int value : values) {
                    heatMap.add(value, newLine);
                    if (newLine) {
                        newLine = false;
                    }
                }

                currentLine = reader.readLine();
            }
        }

        return heatMap;
    }
}
