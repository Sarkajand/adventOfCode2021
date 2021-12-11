package main;

import main.day04.Day04;
import main.day05.Day05;
import main.day06.Day06;
import main.day07.Day07;
import main.day08.Day08;
import main.day09.Day09;
import main.day10.Day10;
import main.day11.Day11;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        day04();
//        day05();
//        day06();
//        day07();
//        day08();
//        day09();
//        day10();
        day11();
    }

    private static void day11() throws IOException {
        Day11 day11 = new Day11();

//        day11.countFirstPart("day11/day11test.txt");
//        day11.countFirstPart("day11/day11input.txt");
//
//        day11.countSecondPart("day11/day11test.txt");
        day11.countSecondPart("day11/day11input.txt");
    }

    private static void day10() throws IOException {
        Day10 day10 = new Day10();

//        day10.countFirstPart("day10/day10test.txt");
//        day10.countFirstPart("day10/day10input.txt");

//        day10.countSecondPart("day10/day10test.txt");
        day10.countSecondPart("day10/day10input.txt");

    }

    private static void day09() throws IOException {
        Day09 day09 = new Day09();
//        day09.countFirstPart("day09/day09test.txt");
//        day09.countFirstPart("day09/day09input.txt");
//
//        day09.countSecondPart("day09/day09test.txt");
        day09.countSecondPart("day09/day09input.txt");
    }

    private static void day08() throws IOException{
        Day08 day08 = new Day08();
//        day08.countDigitWithUniqueSegmentsNumber("day08/day08test.txt");
//        day08.countDigitWithUniqueSegmentsNumber("day08/day08input.txt");

//        day08.countSecondPart("day08/day08test.txt");
        day08.countSecondPart("day08/day08input.txt");
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
