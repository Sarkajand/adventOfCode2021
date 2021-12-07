package main.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {

    private List<Integer> fish = new ArrayList<>(Arrays.asList(1, 3, 1, 5, 5, 1, 1, 1, 5, 1, 1, 1, 3, 1, 1, 4, 3, 1, 1, 2, 2, 4, 2, 1, 3, 3, 2, 4, 4, 4, 1, 3, 1, 1, 4, 3, 1, 5, 5, 1, 1, 3, 4, 2, 1, 5, 3, 4, 5, 5, 2, 5, 5, 1, 5, 5, 2, 1, 5, 1, 1, 2, 1, 1, 1, 4, 4, 1, 3, 3, 1, 5, 4, 4, 3, 4, 3, 3, 1, 1, 3, 4, 1, 5, 5, 2, 5, 2, 2, 4, 1, 2, 5, 2, 1, 2, 5, 4, 1, 1, 1, 1, 1, 4, 1, 1, 3, 1, 5, 2, 5, 1, 3, 1, 5, 3, 3, 2, 2, 1, 5, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 5, 3, 5, 2, 5, 2, 2, 2, 1, 1, 1, 5, 5, 2, 2, 1, 1, 3, 4, 1, 1, 3, 1, 3, 5, 1, 4, 1, 4, 1, 3, 1, 4, 1, 1, 1, 1, 2, 1, 4, 5, 4, 5, 5, 2, 1, 3, 1, 4, 2, 5, 1, 1, 3, 5, 2, 1, 2, 2, 5, 1, 2, 2, 4, 5, 2, 1, 1, 1, 1, 2, 2, 3, 1, 5, 5, 5, 3, 2, 4, 2, 4, 1, 5, 3, 1, 4, 4, 2, 4, 2, 2, 4, 4, 4, 4, 1, 3, 4, 3, 2, 1, 3, 5, 3, 1, 5, 5, 4, 1, 5, 1, 2, 4, 2, 5, 4, 1, 3, 3, 1, 4, 1, 3, 3, 3, 1, 3, 1, 1, 1, 1, 4, 1, 2, 3, 1, 3, 3, 5, 2, 3, 1, 1, 1, 5, 5, 4, 1, 2, 3, 1, 3, 1, 1, 4, 1, 3, 2, 2, 1, 1, 1, 3, 4, 3, 1, 3));


    public void countLanternFish() {
        for (int i = 0; i < 80; i++) {
            List<Integer> newFish = new ArrayList<>();
            for (Integer counter : fish) {
                if (counter == 0) {
                    newFish.add(8);
                    newFish.add(6);
                } else {
                    newFish.add(counter - 1);
                }
            }
            this.fish = newFish;
        }

        System.out.println(fish.size());
    }

    public void lanternFishTest(int days) {
        this.fish = new ArrayList<>(Arrays.asList(3, 4, 3, 1, 2));
//        countLanternFish();
        countFish(days);
    }

    public void countFish(int days) {
        long fishCount = fish.size();

        long counter0 = fish.stream().filter(counter -> counter == 0).count();
        long counter1 = fish.stream().filter(counter -> counter == 1).count();
        long counter2 = fish.stream().filter(counter -> counter == 2).count();
        long counter3 = fish.stream().filter(counter -> counter == 3).count();
        long counter4 = fish.stream().filter(counter -> counter == 4).count();
        long counter5 = fish.stream().filter(counter -> counter == 5).count();
        long counter6 = fish.stream().filter(counter -> counter == 6).count();
        long counter7 = fish.stream().filter(counter -> counter == 7).count();
        long counter8 = fish.stream().filter(counter -> counter == 8).count();
        long newFish = 0;

        for (int i = 0; i < days; i++) {
            newFish = counter0;
            counter0 = counter1;
            counter1 = counter2;
            counter2 = counter3;
            counter3 = counter4;
            counter4 = counter5;
            counter5 = counter6;
            counter6 = counter7 + newFish;
            counter7 = counter8;
            counter8 = newFish;
        }

        long sum = counter0 + counter1 + counter2 + counter3 + counter4 + counter5 + counter6 + counter7 + counter8;
        System.out.println(sum);
    }
}
