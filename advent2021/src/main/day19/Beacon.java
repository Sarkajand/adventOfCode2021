package main.day19;

import java.util.ArrayList;
import java.util.List;

public class Beacon {

    private Scanner scanner;
    private int x;
    private int y;
    private int z;
    private List<Integer> distances = new ArrayList<>();

    public Beacon() {
    }

    public Beacon(Scanner scanner, int x, int y, int z) {
        this.scanner = scanner;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void addDistance(Integer distance) {
        distances.add(distance);
    }

    public List<Integer> getDistances() {
        return distances;
    }

    public void setDistances(List<Integer> distances) {
        this.distances = distances;
    }
}
