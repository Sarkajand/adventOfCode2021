package main.day24;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alu {

    private int w = 0;
    private int x = 0;
    private int y = 0;
    private int z = 0;

    List<String> monadProgram = new ArrayList<>();

    public Alu(List<String> monadProgram) {
        this.monadProgram = monadProgram;
    }

    public void runMonad(int[] number) {
        int digitIndex = 0;
        Pattern pattern = Pattern.compile("([a-z]{3}) ([wxyz]) ?([wxyz0-9]?)");

//        Pattern pattern = Pattern.compile("([a-z]{3}) ([wxyz]) ?.?");

        for (String instruction : monadProgram) {
            Matcher matcher = pattern.matcher(instruction);
            if (matcher.matches()) {
                switch (matcher.group(1)) {
                    case "inp":
                        inp(matcher.group(2), number[digitIndex]);
                        digitIndex++;
                        break;
                    case "add":
                        add(matcher.group(2), matcher.group(3));
                    case "mul":
                        mul(matcher.group(2), matcher.group(3));
                    case "div":
                        div(matcher.group(2), matcher.group(3));
                    case "mod":
                        mod(matcher.group(2), matcher.group(3));
                    case "eql":
                        eql(matcher.group(2), matcher.group(3));
                    default:
                        throw new RuntimeException("unknown instruction: " + instruction);
                }
            } else {
                throw new RuntimeException("unknown instruction: " + instruction);
            }
        }
    }

    private void inp(String variable, int a) {
        switch (variable) {
            case "w":
                this.w = a;
                break;
            case "x":
                this.x = a;
                break;
            case "y":
                this.y = a;
                break;
            case "z":
                this.z = a;
                break;
            default:
                throw new RuntimeException("unknown variable: " + variable);
        }
    }

    private void add(String variable, String b){
        switch (variable) {
            case "w":
                this.w = w + getValue(b);
                break;
            case "x":
                this.x = x + getValue(b);
                break;
            case "y":
                this.y = y + getValue(b);
                break;
            case "z":
                this.z = z + getValue(b);
                break;
            default:
                throw new RuntimeException("unknown variable: " + variable);
        }
    }

    private void mul(String variable, String b) {
        switch (variable) {
            case "w":
                this.w = w * getValue(b);
                break;
            case "x":
                this.x = x * getValue(b);
                break;
            case "y":
                this.y = y * getValue(b);
                break;
            case "z":
                this.z = z * getValue(b);
                break;
            default:
                throw new RuntimeException("unknown variable: " + variable);
        }
    }

    private void div(String variable, String b) {
        switch (variable) {
            case "w":
                this.w = w / getValue(b);
                break;
            case "x":
                this.x = x / getValue(b);
                break;
            case "y":
                this.y = y / getValue(b);
                break;
            case "z":
                this.z = z / getValue(b);
                break;
            default:
                throw new RuntimeException("unknown variable: " + variable);
        }
    }

    private void mod(String variable, String b) {
        switch (variable) {
            case "w":
                this.w = w % getValue(b);
                break;
            case "x":
                this.x = x % getValue(b);
                break;
            case "y":
                this.y = y % getValue(b);
                break;
            case "z":
                this.z = z % getValue(b);
                break;
            default:
                throw new RuntimeException("unknown variable: " + variable);
        }
    }

    private void eql(String variable, String b) {
        switch (variable) {
            case "w":
                this.w = w == getValue(b) ? 1 : 0;
                break;
            case "x":
                this.x = x == getValue(b) ? 1 : 0;
                break;
            case "y":
                this.y = y == getValue(b) ? 1 : 0;
                break;
            case "z":
                this.z = z == getValue(b) ? 1 : 0;
                break;
            default:
                throw new RuntimeException("unknown variable: " + variable);
        }
    }

    private int getValue(String b) {
        switch (b) {
            case "w":
                return this.w;
            case "x":
                return this.x;
            case "y":
                return this.y;
            case "z":
                return this.z;
            default:
                return Integer.parseInt(b);
        }
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
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

    public List<String> getMonadProgram() {
        return monadProgram;
    }

    public void setMonadProgram(List<String> monadProgram) {
        this.monadProgram = monadProgram;
    }
}
