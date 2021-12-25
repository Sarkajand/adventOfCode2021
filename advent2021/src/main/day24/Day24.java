package main.day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day24 {

    public void firstPart(String filePath) throws IOException {
        List<String> monadProgram = readInput(filePath);
        Alu alu = new Alu(monadProgram);

        List<Long> evaluatedNumbers = new ArrayList<>();

        for (int a = 9; a > 0; a--) {
            for (int b = 9; b > 0; b--) {
                for (int c = 9; c > 0; c--) {
                    for (int d = 9; d > 0; d--) {
                        for (int e = 9; e > 0; e--) {
                            for (int f = 9; f > 0; f--) {
                                for (int g = 9; g > 0; g--) {
                                    for (int h = 9; h > 0; h--) {
                                        for (int i = 9; i > 0; i--) {
                                            for (int j = 9; j > 0; j--) {
                                                for (int k = 9; k > 0; k--) {
                                                    for (int l = 9; l > 0; l--) {
                                                        for (int m = 9; m > 0; m--) {
                                                            for (int n = 9; n > 0; n--) {
                                                                int[] number = new int[]{a,b,c,d,e,f,g,h,i,j,k,l,m,n};
                                                                try {
                                                                    alu.runMonad(number);
                                                                    if (alu.getZ() == 0) {
                                                                        String numberStr = Arrays.toString(number);
                                                                        numberStr = numberStr.substring(1).replaceAll("|]|,", "");
                                                                        System.out.println(numberStr);
                                                                        evaluatedNumbers.add(Long.parseLong(numberStr));
                                                                    }
                                                                } catch (RuntimeException ignored) {
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private List<String> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        List<String> input = new ArrayList<>();

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
