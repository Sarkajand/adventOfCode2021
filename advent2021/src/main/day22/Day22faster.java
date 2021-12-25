package main.day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day22faster {

    public void secondPart(String filePath) throws IOException {
        List<Cuboid> input = readInput(filePath);

        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        List<Integer> zs = new ArrayList<>();
        for (Cuboid cuboid : input) {
            xs.add(cuboid.x1);
            xs.add(cuboid.x2);
            ys.add(cuboid.y1);
            ys.add(cuboid.y2);
            zs.add(cuboid.z1);
            zs.add(cuboid.z2);
        }
        xs = xs.stream().distinct().sorted().collect(Collectors.toList());
        ys = ys.stream().distinct().sorted().collect(Collectors.toList());
        zs = zs.stream().distinct().sorted().collect(Collectors.toList());

        boolean[][][] g = new boolean[xs.size()][ys.size()][zs.size()];

        for (Cuboid cuboid : input) {
            for (int x = xs.indexOf(cuboid.x1); x < xs.indexOf(cuboid.x2); x++) {
                for (int y = ys.indexOf(cuboid.y1); y < ys.indexOf(cuboid.y2); y++) {
                    for (int z = zs.indexOf(cuboid.z1); z < zs.indexOf(cuboid.z2); z++) {
                        g[x][y][z] = cuboid.on;
                    }
                }
            }
        }

        long lightsOn = 0;
        for (int x = 0; x < xs.size(); x++) {
            for (int y = 0; y < ys.size(); y++) {
                for (int z = 0; z < zs.size(); z++) {
                    if (g[x][y][z]) {
                        lightsOn = lightsOn + ((long) (xs.get(x + 1) - xs.get(x)) * (ys.get(y+1) -  ys.get(y)) * (zs.get(z+1) - zs.get(z)));
                    }
                }
            }
        }

        System.out.println(lightsOn);
    }

    private List<Cuboid> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        Pattern pattern = Pattern.compile("^(on|off) x=(-?\\d+)[.][.](-?\\d+),y=(-?\\d+)[.][.](-?\\d+),z=(-?\\d+)[.][.](-?\\d+)$");
        List<Cuboid> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                Matcher matcher = pattern.matcher(currentLine);

                if (matcher.find()) {
                    Cuboid cuboid = new Cuboid();
                    cuboid.on = matcher.group(1).equals("on");
                    cuboid.x1 = Integer.parseInt(matcher.group(2));
                    cuboid.x2 = Integer.parseInt(matcher.group(3)) + 1;
                    cuboid.y1 = Integer.parseInt(matcher.group(4));
                    cuboid.y2 = Integer.parseInt(matcher.group(5)) + 1;
                    cuboid.z1 = Integer.parseInt(matcher.group(6));
                    cuboid.z2 = Integer.parseInt(matcher.group(7)) + 1;
                    input.add(cuboid);
                }
                currentLine = reader.readLine();
            }
        }
        return input;
    }

    private static class Cuboid {
        boolean on;
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        int z1 = 0;
        int z2 = 0;

        public Cuboid() {
        }
    }
}
