package main;

import java.util.ArrayList;

public class Player {
    private String name;
    private int totalStrokes;
    private ArrayList<Integer> strokesPerHole;

    public Player(String name) {
        this.name = name;
        this.totalStrokes = 0;
        strokesPerHole = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getTotalStrokes() { return totalStrokes; }

    public void addStroke(int strokes) {
        totalStrokes += strokes;
        strokesPerHole.add(strokes);
    }

    public void resetStrokes() {
        totalStrokes = 0;
        strokesPerHole.clear();
    }
}
