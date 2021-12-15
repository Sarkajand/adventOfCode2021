package main.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Day12 {

    public void countFirstPart(String filePath) throws IOException {
        Map<String, Cave> caves = readInput(filePath);

        Cave startCave = caves.get("start");
        Set<List<Cave>> routesFromStart = new HashSet<>();
        List<Cave> route = new ArrayList<>();
        route.add(startCave);
        writeRoutes(routesFromStart, route, startCave);

        System.out.println(routesFromStart.size());
    }

    public void countSecondPart(String filePath) throws IOException {
        Map<String, Cave> caves = readInput(filePath);

        Cave startCave = caves.get("start");
        Set<List<Cave>> routesFromStart = new HashSet<>();
        List<Cave> route = new ArrayList<>();
        route.add(startCave);
        writeSecondRoutes(routesFromStart, route, startCave, false);

        System.out.println(routesFromStart.size());
    }

    private void writeSecondRoutes(Set<List<Cave>> routesFromStart, List<Cave> route, Cave cave, boolean containSmallCaveTwice) {

        if (cave.getValue().equals("end")) {
            route.add(cave);
            routesFromStart.add(route);
        } else {
            for (Cave nextCave : cave.getNextCaves()) {
                if (!nextCave.isSmall() || !route.contains(nextCave)) {
                    List<Cave> continuingRoute = new ArrayList<>(route);
                    continuingRoute.add(nextCave);
                    writeSecondRoutes(routesFromStart, continuingRoute, nextCave, containSmallCaveTwice);
                } else if (nextCave.isSmall() && !containSmallCaveTwice && !nextCave.getValue().equals("start")) {
                    List<Cave> continuingRoute = new ArrayList<>(route);
                    continuingRoute.add(nextCave);
                    writeSecondRoutes(routesFromStart, continuingRoute, nextCave, true);
                }
            }
        }
    }

    private void writeRoutes(Set<List<Cave>> routesFromStart, List<Cave> route, Cave cave) {

        if (cave.getValue().equals("end")) {
            route.add(cave);
            routesFromStart.add(route);
        }

        for (Cave nextCave : cave.getNextCaves()) {
            if (!nextCave.isSmall() || !route.contains(nextCave)) {
                List<Cave> continuingRoute = new ArrayList<>(route);
                continuingRoute.add(nextCave);
                writeRoutes(routesFromStart, continuingRoute, nextCave);
            }
        }
    }

    private Map<String, Cave> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        Map<String, Cave> caves = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] caveNames = currentLine.split("-");

                Cave firstCave = caves.getOrDefault(caveNames[0], new Cave());
                Cave secondCave = caves.getOrDefault(caveNames[1], new Cave());

                if (firstCave.getValue() == null) {
                    firstCave.setValue(caveNames[0]);
                    firstCave.setSmall(caveNames[0].matches("[a-z]+"));
                    caves.put(caveNames[0], firstCave);
                }

                if (secondCave.getValue() == null) {
                    secondCave.setValue(caveNames[1]);
                    secondCave.setSmall(caveNames[1].matches("[a-z]+"));
                    caves.put(caveNames[1], secondCave);
                }

                firstCave.addNextCave(secondCave);
                secondCave.addNextCave(firstCave);

                currentLine = reader.readLine();
            }
        }

        return caves;
    }
}
