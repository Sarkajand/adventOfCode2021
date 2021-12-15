package main.day12;

import java.util.HashSet;
import java.util.Set;

public class Cave {

    private String value;
    private boolean isSmall;
    private Set<Cave> nextCaves = new HashSet<>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSmall() {
        return isSmall;
    }

    public void setSmall(boolean small) {
        isSmall = small;
    }

    public Set<Cave> getNextCaves() {
        return nextCaves;
    }

    public void setNextCaves(Set<Cave> nextCaves) {
        this.nextCaves = nextCaves;
    }

    public void addNextCave(Cave cave) {
        this.nextCaves.add(cave);
    }
}
