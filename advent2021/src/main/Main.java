package main;

import main.day04.Day04;
import main.day05.Day05;
import main.day06.Day06;
import main.day07.Day07;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        day04();
//        day05();
//        day06();
        day07();
    }

    private static void day04() throws IOException {
        Day04 day04 = new Day04();
//        day04.readInput("day04/day4test.txt");
        day04.readInput("day04/day4input.txt");

        day04.play();
    }

    private static void day05() throws IOException {
        Day05 day05 = new Day05();
//        day05.getPointsFromInput("day05/day05test.txt");
//        day05.getPointsFromInput("day05/day05input.txt");

//        day05.getAllPointsFromInput("day05/day05test.txt");
        day05.getAllPointsFromInput("day05/day05input.txt");

        day05.countDangerousAreas();
    }

    private static void day06() {
        Day06 day06 = new Day06();

//        day06.lanternFishTest();
//        day06.countLanternFish();

        day06.lanternFishTest(256);
        day06.countFish(256);
    }

    private static void day07() {
        Day07 day07 = new Day07();
        day07.getPosition();
        day07.optimizedFirstExample();
    }
}
