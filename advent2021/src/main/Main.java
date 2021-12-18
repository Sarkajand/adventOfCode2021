package main;

import main.day04.Day04;
import main.day05.Day05;
import main.day06.Day06;
import main.day07.Day07;
import main.day08.Day08;
import main.day09.Day09;
import main.day10.Day10;
import main.day11.Day11;
import main.day12.Day12;
import main.day12.Day12WithoutRecursion;
import main.day13.Day13;
import main.day14.Day14;
import main.day15.Day15;
import main.day16.Day16;
import main.day17.Day17;

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
//        day11();
//        day12();
//        day13();
//        day14();
//        day15();
//        day16();
        day17();
    }

    private static void day17() {
        Day17 day17 = new Day17();


        day17.secondPart(20,30,-10,-5);
        day17.secondPart(248,285,-85,-56);

    }

    private static void day16() throws IOException {
        Day16 day16 = new Day16();

        day16.firstPart("day16/day16test.txt");
        day16.firstPart("day16/day16test2.txt");
        day16.firstPart("day16/day16test3.txt");
        day16.firstPart("day16/day16test4.txt");
        day16.firstPart("day16/day16input.txt");

        day16.secondPart("day16/day16test5.txt");
        day16.secondPart("day16/day16test6.txt");
        day16.secondPart("day16/day16test7.txt");
        day16.secondPart("day16/day16test8.txt");
        day16.secondPart("day16/day16test9.txt");
        day16.secondPart("day16/day16test10.txt");
        day16.secondPart("day16/day16test11.txt");
        day16.secondPart("day16/day16test12.txt");
        day16.secondPart("day16/day16input.txt");

    }

    private static void day15() throws IOException {
        Day15 day15 = new Day15();

        day15.firstPart("day15/day15test.txt");
        day15.firstPart("day15/day15input.txt");

        day15.secondPart("day15/day15test.txt");
        day15.secondPart("day15/day15input.txt");

    }

    private static void day14() throws IOException {
        Day14 day14 = new Day14();

        day14.getAnswer("day14/day14test.txt", 10);
        day14.getAnswer("day14/day14test.txt", 40);

        day14.getAnswer("day14/day14input.txt", 10);
        day14.getAnswer("day14/day14input.txt", 40);


//        day14.firstPart("day14/day14test.txt", 10);
//        day14.firstPart("day14/day14input.txt", 10);
    }

    private static void day13() throws IOException {
        Day13 day13 = new Day13();

        day13.getAnswers("day13/day13test.txt");
        day13.getAnswers("day13/day13input.txt");
    }

    private static void day12() throws IOException {
        Day12 day12 = new Day12();

//        day12.countFirstPart("day12/day12test1.txt");
//        day12.countFirstPart("day12/day12test2.txt");
//        day12.countFirstPart("day12/day12test3.txt");
//        day12.countFirstPart("day12/day12input.txt");

//        day12.countSecondPart("day12/day12test1.txt");
//        day12.countSecondPart("day12/day12test2.txt");
//        day12.countSecondPart("day12/day12test3.txt");
//        day12.countSecondPart("day12/day12input.txt");

        Day12WithoutRecursion day12WithoutRecursion = new Day12WithoutRecursion();
        day12WithoutRecursion.getAnswer("day12/day12test1.txt", false);
        day12WithoutRecursion.getAnswer("day12/day12test2.txt", false);
        day12WithoutRecursion.getAnswer("day12/day12test3.txt", false);
        day12WithoutRecursion.getAnswer("day12/day12input.txt", false);

        day12WithoutRecursion.getAnswer("day12/day12test1.txt", true);
        day12WithoutRecursion.getAnswer("day12/day12test2.txt", true);
        day12WithoutRecursion.getAnswer("day12/day12test3.txt", true);
        day12WithoutRecursion.getAnswer("day12/day12input.txt", true);

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
