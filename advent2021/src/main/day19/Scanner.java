package main.day19;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private String name;
    private int x;
    private int y;
    private int z;
    private String recalculatedX;
    private String recalculatedY;
    private String recalculatedZ;
    private boolean reverseX = false;
    private boolean reverseY = false;
    private boolean reverseZ = false;


    private List<Beacon> beacons = new ArrayList<>();

    public Scanner() {
    }

    public Scanner(String name) {
        this.name = name;
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

    public String getRecalculatedX() {
        return recalculatedX;
    }

    public void setRecalculatedX(String recalculatedX) {
        this.recalculatedX = recalculatedX;
    }

    public String getRecalculatedY() {
        return recalculatedY;
    }

    public void setRecalculatedY(String recalculatedY) {
        this.recalculatedY = recalculatedY;
    }

    public String getRecalculatedZ() {
        return recalculatedZ;
    }

    public void setRecalculatedZ(String recalculatedZ) {
        this.recalculatedZ = recalculatedZ;
    }

    public boolean isReverseX() {
        return reverseX;
    }

    public void setReverseX(boolean reverseX) {
        this.reverseX = reverseX;
    }

    public boolean isReverseY() {
        return reverseY;
    }

    public void setReverseY(boolean reverseY) {
        this.reverseY = reverseY;
    }

    public boolean isReverseZ() {
        return reverseZ;
    }

    public void setReverseZ(boolean reverseZ) {
        this.reverseZ = reverseZ;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void addBeacon(Beacon beacon) {
        beacons.add(beacon);
    }
}
