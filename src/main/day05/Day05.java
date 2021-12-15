package main.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day05 {

    private List<Point> points = new ArrayList<>();

    public void getPointsFromInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        Pattern pattern = Pattern.compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                Matcher matcher = pattern.matcher(currentLine);
                if (matcher.matches()) {
                    int x1 = Integer.parseInt(matcher.group(1));
                    int y1 = Integer.parseInt(matcher.group(2));
                    int x2 = Integer.parseInt(matcher.group(3));
                    int y2 = Integer.parseInt(matcher.group(4));

                    if (x1 == x2) {
                        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                            points.add(new Point(x1, y));
                        }
                    }

                    if (y1 == y2) {
                        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                            points.add(new Point(x, y1));
                        }
                    }
                }

                currentLine = reader.readLine();
            }
        }
    }

    public void getAllPointsFromInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        Pattern pattern = Pattern.compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                Matcher matcher = pattern.matcher(currentLine);
                if (matcher.matches()) {
                    int x1 = Integer.parseInt(matcher.group(1));
                    int y1 = Integer.parseInt(matcher.group(2));
                    int x2 = Integer.parseInt(matcher.group(3));
                    int y2 = Integer.parseInt(matcher.group(4));

                    if (x1 == x2) {
                        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                            points.add(new Point(x1, y));
                        }
                    } else if (y1 == y2) {
                        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                            points.add(new Point(x, y1));
                        }
                    } else {
                        int y = x1 < x2 ? y1 : y2;
                        int toY = x1 > x2 ? y1 : y2;
                        int yDirection = y < toY ? 1 : -1;
                        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                            points.add(new Point(x, y));
                            y = y + yDirection;
                        }
                    }
                }

                currentLine = reader.readLine();
            }
        }
    }

    public void countDangerousAreas() {
        Map<String, List<Point>> pointsMap = points.stream().collect(Collectors.groupingBy(point -> String.format("%d,%d", point.getX(), point.getY())));
        long count = pointsMap.values().stream().filter(points -> points.size() > 1).count();
        System.out.println(count);
    }
}
