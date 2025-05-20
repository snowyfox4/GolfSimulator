package main;

import java.util.ArrayList;

public class Hole {
    private int par;
    private int distance; // yards
    private ArrayList<Obstacle> obstacles;

    public Hole(int par, int distance) {
        this.par = par;
        this.distance = distance;
        obstacles = new ArrayList<>();
        // Obstacles can be added later
    }

    public int getPar() {
        return par;
    }

    public int getDistance() {
        return distance;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean isInHazard(Ball ball) {
        for (Obstacle o : obstacles) {
            if (o.isColliding(ball)) {
                return true;
            }
        }
        return false;
    }
}
