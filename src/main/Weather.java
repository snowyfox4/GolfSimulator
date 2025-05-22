package main;

import java.awt.Color;
import java.awt.Graphics;

public class Weather {
    private double windSpeed; // e.g., 0 to 20 mph
    private boolean isRainy;
    private boolean isWindy;

    public Weather() {
        // Randomize weather on creation, randomize if created too
        windSpeed = Math.random() * 20;
        isWindy = windSpeed > 10;
        isRainy = Math.random() < 0.9;  // 30% chance rain
        //Change Vectors, Wind Vector, Speed Vector
    }
    
    public void drawWeather(Graphics g) {
    	if (isWindy) {
    		for (int i = 0; i < 50; i++) {
    			g.setColor(Color.WHITE);
    			g.drawLine((int)(Math.random()*100), (int)(Math.random() * 800), (int)(Math.random() * 800), (int)(Math.random() * 1000));
    		}
    	}
    	if (isRainy) {
    		for (int i = 0; i < 1000; i++) {
    			g.setColor(Color.BLUE);
    			g.fillOval((int)(Math.random()*800), (int)(Math.random()*1000), (int)(Math.random()*5), (int)(Math.random()*5));
    		}
    	}
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
