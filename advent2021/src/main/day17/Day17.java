package main.day17;

import java.util.HashSet;
import java.util.Set;

public class Day17 {

    public void secondPart(int xMin, int xMax, int yMin, int yMax) {

        Set<Velocity> starts = new HashSet<>();
        for (int x = 0; x < 300; x++) {
            for (int y = 100; y > -100; y--) {
                if (getToTarget(x, y, xMin, xMax, yMin, yMax)) {
                    Velocity velocity = new Velocity();
                    velocity.x = x;
                    velocity.y = y;
                    starts.add(velocity);
                }
            }
        }

        System.out.println(starts.size());
    }

    private boolean getToTarget(int x, int y, int xMin, int xMax, int yMin, int yMax) {
        int yVelocity = y;
        int xVelocity = x;
        int xChange = x > 0 ? -1 : +1;

        while (y > yMin*2) {
            if (xMin <= x && x <= xMax && yMin <= y && y <= yMax) {
                return true;
            }
            yVelocity--;
            xVelocity = xVelocity == 0 ? 0 : xVelocity + xChange;
            y = y + yVelocity;
            x = x + xVelocity;
        }
        return false;
    }

    private static class Velocity {
        int x;
        int y;
    }
}
