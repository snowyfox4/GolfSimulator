package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Weather {
    private double windSpeed;  
    // 0 to 20 mph
    
    private double windDirection; // 0 to 360 degrees (0 = East, 90 = North)
    private boolean isRainy;
    private boolean isWindy;
    private double windAngle;

    public Weather() {
    	windAngle = Math.random() * 360;
        windSpeed = Math.random() * 20;
        windDirection = Math.random() * 360;  // full circle
        isWindy = windSpeed > 10;
        isRainy = Math.random() < 0.3;        // 30% chance of rain
    }
    public double getWindAngle() {
        return windAngle;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public boolean isRainy() {
        return isRainy;
    }

    public boolean isWindy() {
        return isWindy;
    }

    public void drawWeather(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (isWindy) {
            g2d.setColor(new Color(255, 255, 255, 100)); // translucent white for wind
            for (int i = 0; i < 50; i++) {
                int x1 = (int)(Math.random() * 800);
                int y1 = (int)(Math.random() * 1000);
                // wind pushes lines horizontally roughly in windDirection angle

                // Convert wind direction to vector
                double rad = Math.toRadians(windDirection);
                int length = 20;
                int x2 = (int)(x1 + length * Math.cos(rad));
                int y2 = (int)(y1 - length * Math.sin(rad)); // minus because y-axis downwards in screen coords

                g2d.drawLine(x1, y1, x2, y2);
            }
        }

        if (isRainy) {
            g2d.setColor(new Color(0, 191, 255, 150));  // translucent blue rain drops
            for (int i = 0; i < 200; i++) {
                int x = (int)(Math.random() * 800);
                int y = (int)(Math.random() * 1000);
                int width = 2 + (int)(Math.random() * 2);
                int height = 8 + (int)(Math.random() * 5);
                g2d.fillOval(x, y, width, height);
            }
        }
    }
}