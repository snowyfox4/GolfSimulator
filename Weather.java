package main;

import java.awt.Color;
import java.awt.Graphics;

public class Weather {
    private double windSpeed;     // 0 to 20 mph
    private double windAngle;     // in degrees, 0-360 (0 = East, 90 = North)
    private boolean isRainy;
    private boolean isWindy;

    public Weather() {
        // Randomize wind speed 0 to 20 mph
        windSpeed = Math.random() * 20;

        // Randomize wind direction angle 0 to 360 degrees
        windAngle = Math.random() * 360;

        isWindy = windSpeed > 5;  // Let's say >5 mph counts as windy
        isRainy = Math.random() < 0.3;  // 30% chance of rain
    }

    public void drawWeather(Graphics g, int width, int height) {
        if (isWindy) {
            g.setColor(new Color(255, 255, 255, 150)); // semi-transparent white
            for (int i = 0; i < 50; i++) {
                int x1 = (int) (Math.random() * width);
                int y1 = (int) (Math.random() * height);
                // Calculate wind vector endpoint based on angle and random length
                double length = 10 + Math.random() * 20;
                int x2 = (int) (x1 + length * Math.cos(Math.toRadians(windAngle)));
                int y2 = (int) (y1 - length * Math.sin(Math.toRadians(windAngle))); // minus because y-axis down

                g.drawLine(x1, y1, x2, y2);
            }
        }

        if (isRainy) {
            g.setColor(new Color(0, 0, 255, 150)); // semi-transparent blue
            for (int i = 0; i < 200; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                int size = 1 + (int) (Math.random() * 3);
                g.fillOval(x, y, size, size * 2);
            }
        }
    }

    // Wind speed getter
    public double getWindSpeed() {
        return windSpeed;
    }

    // Wind angle getter (0-360 degrees)
    public double getWindAngle() {
        return windAngle;
    }

    public boolean isRainy() {
        return isRainy;
    }

    public boolean isWindy() {
        return isWindy;
    }

    /**
     * Returns wind effect multiplier for shot distance reduction or deflection,
     * e.g., based on windSpeed and maybe angle relative to shot direction.
     * For now, simple: windSpeed * factor.
     */
    public double getWindEffect() {
        return windSpeed * 2;  // arbitrary scaling factor
    }
}

