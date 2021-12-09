package main.day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day08 {
//    private List<List<String>> input = new ArrayList<>();
//    private List<String> values = new ArrayList<>();

    public void countDigitWithUniqueSegmentsNumber(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        List<String> values = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] digits = currentLine.split(" ");
                values.addAll(Arrays.asList(digits).subList(11, 15));
                currentLine = reader.readLine();
            }
        }

        long count = values.stream().filter(v -> v.length() == 2 || v.length() == 3 || v.length() == 4 || v.length() == 7).count();
        System.out.println(count);
    }

    public void countSecondPart(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                List<String> digitCodes = Arrays.stream(currentLine.split(" ")).filter(x -> !x.equals("|")).collect(Collectors.toList());
                rows.add(digitCodes);
                currentLine = reader.readLine();
            }
        }

        List<Integer> decodedNumbers = new ArrayList<>();

        for (List<String> row : rows) {
            List<String> rowCopy = new ArrayList<>(row);

            Map<Integer, List<String>> digitCodeMap = new HashMap<>();

            digitCodeMap.put(1, rowCopy.stream().filter(x -> x.length() == 2).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(1));
            digitCodeMap.put(4, rowCopy.stream().filter(x -> x.length() == 4).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(4));
            digitCodeMap.put(7, rowCopy.stream().filter(x -> x.length() == 3).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(7));
            digitCodeMap.put(8, rowCopy.stream().filter(x -> x.length() == 7).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(8));

            digitCodeMap.put(9, rowCopy.stream().filter(x -> x.length() == 6
                    && x.contains(digitCodeMap.get(4).get(0).subSequence(0, 1))
                    && x.contains(digitCodeMap.get(4).get(0).subSequence(1, 2))
                    && x.contains(digitCodeMap.get(4).get(0).subSequence(2, 3))
                    && x.contains(digitCodeMap.get(4).get(0).subSequence(3, 4))).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(9));

            digitCodeMap.put(0, rowCopy.stream().filter(x -> x.length() == 6
                    && x.contains(digitCodeMap.get(1).get(0).subSequence(0, 1))
                    && x.contains(digitCodeMap.get(1).get(0).subSequence(1, 2))).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(0));

            digitCodeMap.put(6, rowCopy.stream().filter(x -> x.length() == 6).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(6));

            digitCodeMap.put(3, rowCopy.stream().filter(x -> x.length() == 5
                    && x.contains(digitCodeMap.get(1).get(0).subSequence(0, 1))
                    && x.contains(digitCodeMap.get(1).get(0).subSequence(1, 2))).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(3));

            String leftBottomLetter = digitCodeMap.get(6).get(0).replace(digitCodeMap.get(9).get(0).subSequence(0, 1), "")
                    .replace(digitCodeMap.get(9).get(0).subSequence(1, 2), "")
                    .replace(digitCodeMap.get(9).get(0).subSequence(2, 3), "")
                    .replace(digitCodeMap.get(9).get(0).subSequence(3, 4), "")
                    .replace(digitCodeMap.get(9).get(0).subSequence(4, 5), "")
                    .replace(digitCodeMap.get(9).get(0).subSequence(5, 6), "");
            digitCodeMap.put(2, rowCopy.stream().filter(x -> x.length() == 5 && x.contains(leftBottomLetter.subSequence(0, 1))).collect(Collectors.toList()));
            rowCopy.removeAll(digitCodeMap.get(2));

            digitCodeMap.put(5, new ArrayList<>(rowCopy));
            rowCopy.removeAll(digitCodeMap.get(5));

            List<String> secondPartOfRow = row.subList(10, 14);
            Map<Integer, List<String>> secondPartCodesMap = digitCodeMap.entrySet().stream()
                    .filter(entrySet -> entrySet.getValue().size() > 1)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            List<Integer> digits = new ArrayList<>();
            for (String code : secondPartOfRow) {
                secondPartCodesMap.forEach((key, value) -> {
                    if (value.contains(code)) {
                        digits.add(key);
                    }
                });
            }

            String number = "";
            for (Integer digit : digits) {
                number = number.concat(digit.toString());
            }
            decodedNumbers.add(Integer.parseInt(number));

        }
        System.out.println(decodedNumbers.stream().flatMapToInt(x -> IntStream.of((int) x)).sum());
    }
}
