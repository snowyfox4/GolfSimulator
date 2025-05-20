package main;

public class Weather {
    private double windSpeed; // e.g., 0 to 20 mph
    private boolean isRainy;
    private boolean isWindy;

    public Weather() {
        // Randomize weather on creation
        windSpeed = Math.random() * 20;
        isWindy = windSpeed > 10;
        isRainy = Math.random() < 0.3;  // 30% chance rain
    }

    public double getWindEffect() {
        // Simplified: wind reduces shot distance
        return windSpeed * 2; // arbitrary factor
    }

    public boolean isRainy() {
        return isRainy;
    }

    public boolean isWindy() {
        return isWindy;
    }
}
