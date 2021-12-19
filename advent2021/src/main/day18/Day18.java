package main.day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day18 {

    public void firstPart(String filePath) throws IOException {
        Queue<String> input = readInput(filePath);

        String number = input.remove();
        number = reduce(number);

        while (!input.isEmpty()) {
            String numberToAdd = input.remove();
            numberToAdd = reduce(numberToAdd);

            number = String.format("[%s,%s]", number, numberToAdd);
            number = reduce(number);
        }

        System.out.println(getMagnitude(number));
    }

    public void secondPart(String filePath) throws IOException {
        Queue<String> input = readInput(filePath);

        int maxMagnitude = Integer.MIN_VALUE;

        for (String number : input) {
            for (String add : input) {
                if (!number.equals(add)) {
                    String result = String.format("[%s,%s]", number, add);
                    result = reduce(result);
                    int magnitude = getMagnitude(result);
                    maxMagnitude = Math.max(magnitude, maxMagnitude);
                }
            }
        }
        System.out.println(maxMagnitude);
    }

    private int getMagnitude(String number) {
        Pattern pattern = Pattern.compile("\\[(\\d+),(\\d+)]");
        Matcher matcher = pattern.matcher(number);
        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            String newNumber = String.valueOf((a * 3) + (b * 2));
            number = number.replaceFirst("\\[(\\d+),(\\d+)]", newNumber);
            matcher = pattern.matcher(number);
        }
        return Integer.parseInt(number);
    }

    private String reduce(String number) {
        StringBuilder reducedNumber = new StringBuilder(number);
        boolean reductionIsFinished = false;

        while (!reductionIsFinished) {

            List<Character> characters = reducedNumber.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            int index = 0;
            int deep = 0;
            int previousDigitIndex = -1;

            reductionIsFinished = true;
            boolean splitIsFinished = true;

            for (Character character : characters) {
                if (character == '[') {
                    deep++;
                    if (deep == 5) {
                        Pattern expressionToExplodePattern = Pattern.compile("(\\d+),(\\d+)");
                        Matcher expressionToExplodeMatcher = expressionToExplodePattern.matcher(reducedNumber.substring(index));
                        if (expressionToExplodeMatcher.find()) {
                            String expressionToExplode = expressionToExplodeMatcher.group();
                            int expressionLength = expressionToExplode.length();
                            int leftDigit = Integer.parseInt(expressionToExplodeMatcher.group(1));
                            int rightDigit = Integer.parseInt(expressionToExplodeMatcher.group(2));
                            Pattern pattern = Pattern.compile("\\d+");

                            if (previousDigitIndex != -1) {
                                Matcher leftMatcher = pattern.matcher(reducedNumber.substring(previousDigitIndex - 1));
                                if (leftMatcher.find()) {
                                    int firstToLeft = Integer.parseInt(leftMatcher.group());
                                    previousDigitIndex = firstToLeft > 9 ? previousDigitIndex - 1 : previousDigitIndex;
                                    int sum = firstToLeft + leftDigit;
                                    if (sum > 9 && firstToLeft < 10) {
                                        index++;
                                    }
                                    reducedNumber.insert(previousDigitIndex + leftMatcher.group().length(), sum);
                                    reducedNumber.delete(previousDigitIndex, previousDigitIndex + leftMatcher.group().length());
                                }
                            }

                            Matcher rightMatcher = pattern.matcher(reducedNumber.substring(index + expressionLength + 1));
                            if (rightMatcher.find()) {
                                int nextDigitIndex = rightMatcher.start() + index + expressionLength + 1;
                                int firstToRight = Integer.parseInt(rightMatcher.group());

                                reducedNumber.insert(nextDigitIndex + rightMatcher.group().length(), (firstToRight + rightDigit));
                                reducedNumber.delete(nextDigitIndex, nextDigitIndex + rightMatcher.group().length());
                            }

                            reducedNumber.delete(index, index + expressionLength + 2);
                            reducedNumber.insert(index, '0');

                        }
                        reductionIsFinished = false;
                        splitIsFinished = false;
                        break;
                    }
                } else if (character == ']') {
                    deep--;
                } else if (Character.isDigit(character)) {
                    previousDigitIndex = index;
                }

                index++;
            }

            if (splitIsFinished) {
                index = 0;
                for (Character character : characters) {
                    if (Character.isDigit(character) && Character.isDigit(reducedNumber.charAt(index + 1))) {
                        int numberToSplit = Integer.parseInt(reducedNumber.substring(index, index + 2));
                        int leftNumber = numberToSplit / 2;
                        int rightNumber = numberToSplit / 2 + numberToSplit % 2;
                        reducedNumber.insert(index + 2, String.format("[%d,%d]", leftNumber, rightNumber));
                        reducedNumber.delete(index, index + 2);
                        reductionIsFinished = false;
                        break;
                    }
                    index++;
                }
            }
        }
        return reducedNumber.toString();
    }


    private Queue<String> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        Queue<String> input = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                input.add(currentLine);
                currentLine = reader.readLine();
            }
        }
        return input;
    }
}
