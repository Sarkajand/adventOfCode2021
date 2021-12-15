package main.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12WithoutRecursion {

    public void getAnswer(String filePath, boolean canBeTwice) throws IOException {
        Map<String, Set<String>> caves = readInput(filePath);

        StepInPath start = new StepInPath("start", Stream.of("start").collect(Collectors.toCollection(HashSet::new)), false);

        Queue<StepInPath> steps = new LinkedList<>();
        steps.add(start);
        int pathsToEnd = 0;

        while (!steps.isEmpty()) {
            StepInPath step = steps.remove();

            if (step.caveName.equals("end")) {
                pathsToEnd++;
            }

            for (String nextCave : caves.get(step.caveName)) {
                if (!step.smallsInPath.contains(nextCave)) {
                    Set<String> newSmalls = new HashSet<>(step.smallsInPath);
                    if (nextCave.matches("[a-z]+")) {
                        newSmalls.add(nextCave);
                    }
                    steps.add(new StepInPath(nextCave, newSmalls, step.containSmallCaveTwice));
                } else if (canBeTwice && !step.containSmallCaveTwice && !nextCave.equals("start") && !nextCave.equals("end")) {
                    Set<String> newSmalls = new HashSet<>(step.smallsInPath);
                    newSmalls.add(nextCave);
                    steps.add(new StepInPath(nextCave, newSmalls, true));

                }
            }
        }

        System.out.println(pathsToEnd);
    }

    private Map<String, Set<String>> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        Map<String, Set<String>> caves = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] caveNames = currentLine.split("-");

                Set<String> firstCaveConnections = caves.getOrDefault(caveNames[0], new HashSet<>());
                Set<String> secondCaveConnections = caves.getOrDefault(caveNames[1], new HashSet<>());

                firstCaveConnections.add(caveNames[1]);
                secondCaveConnections.add(caveNames[0]);

                caves.putIfAbsent(caveNames[0], firstCaveConnections);
                caves.putIfAbsent(caveNames[1], secondCaveConnections);

                currentLine = reader.readLine();
            }
        }
        return caves;
    }

    private static class StepInPath {

        private final String caveName;
        private final Set<String> smallsInPath;
        private final boolean containSmallCaveTwice;

        public StepInPath(String caveName, Set<String> smallsInPath, boolean containSmallCaveTwice) {
            this.caveName = caveName;
            this.smallsInPath = smallsInPath;
            this.containSmallCaveTwice = containSmallCaveTwice;
        }
    }
}
