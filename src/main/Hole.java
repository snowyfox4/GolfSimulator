package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Hole {
	private int par;
	private int distance, width; // yards
	private ArrayList<Obstacle> obstacles;
	private Weather weather;
	private int fairwayLocationX;
	private int fairwayLocationY;

	private int greenLocationX;
	private int greenLocationY;

	private int flagLocationX;
	private int flagLocationY;

	private int teeBoxX;
	private int teeBoxY;

	private Color difficulty;

	public Hole(int par, Weather weather) {
		this.par = par;
		this.width = (int) Math.random() * 30 + 30;
		obstacles = new ArrayList<>();
		this.weather = weather;
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

	public void drawFairway(Graphics g) {
		// NEED A RANDOM BASED ON DISTANCE :)
		// RANDOM CURVATURE TOO!!

		Graphics2D g2d = (Graphics2D) g;

		// Create the GeneralPath
		GeneralPath fairway = new GeneralPath();

		// Define fairway shape with curves
		float fairwayLocationX = (float) (Math.random() * 100);
		float fairwayLocationY = (float) (Math.random() * 100);
		fairway.moveTo(fairwayLocationX, fairwayLocationY);

		// Use curveTo to create fairway curves
		fairway.curveTo(fairwayLocationX + 50, fairwayLocationY - 50, fairwayLocationX + 150, fairwayLocationY - 50,
				fairwayLocationX + 200, fairwayLocationY);
		fairway.curveTo(fairwayLocationX + 200, fairwayLocationY, fairwayLocationX + 300, fairwayLocationY + 100,
				fairwayLocationX + 250, fairwayLocationY + 200);
		fairway.curveTo(fairwayLocationX + 250, fairwayLocationY + 200, fairwayLocationX + 175, fairwayLocationY + 300,
				fairwayLocationX + 300, fairwayLocationY + 300);
		fairway.curveTo(fairwayLocationX + 300, fairwayLocationY + 300, fairwayLocationX + 450, fairwayLocationY + 350,
				fairwayLocationX + 250, fairwayLocationY + 450);
		fairway.curveTo(fairwayLocationX + 250, fairwayLocationY + 450, fairwayLocationX - 100, fairwayLocationY + 500,
				fairwayLocationX, fairwayLocationY);

		// Draw and fill the fairway
		g2d.setColor(Color.GREEN);
		g2d.fill(fairway);
		g2d.setColor(Color.BLACK);
		g2d.draw(fairway);
		weather.drawWeather(g);
		// Make Bunker and Obstacle
	}

	public void drawGreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GeneralPath green = new GeneralPath();

		g2d.setColor(new Color(144, 238, 144));
		float greenLocationX = 300;
		float greenLocationY = 450;
		green.moveTo(greenLocationX, greenLocationY);
		green.curveTo(greenLocationX + 50, greenLocationY - 50, greenLocationX + 100, greenLocationY - 50,
				greenLocationX + 100, greenLocationY);
		green.curveTo(greenLocationX + 100, greenLocationY, greenLocationX + 50, greenLocationY + 50, greenLocationX,
				greenLocationY);
		green.curveTo(greenLocationX, greenLocationY, greenLocationY, greenLocationY, greenLocationX, greenLocationY);
		// g2d.setColor(Color.GREEN);
		g2d.fill(green);
		g2d.setColor(Color.BLACK);
		g2d.draw(green);

	}

	// Draw Flag
	public void drawFlag(Graphics g) {

		g.setColor(Color.GRAY);
		g.drawLine(par, fairwayLocationY, fairwayLocationX, distance);
		// Direction 6 (0, 5), Change angle 0 - 45, 1 - 0, 2 - -45
		// Wind Speed Depends on the Radius
		// x = wscos(45) --Radians
		// (+)

		// Change Color Depending on Location on Green
	}

	// Changes depending on what tee box
	public void drawTeeBox(Graphics g) {
		g.setColor(difficulty);
		if (difficulty == Color.red) {
			//Yardages from google
			if (par == 3) {
				distance = (int)(Math.random()*100 + 100);
			}
			if (par == 4) {
				distance = (int)(Math.random()*220 + 200);
			}
			if (par == 5) {
				distance = (int)(Math.random()*230 + 370);
			}
			// Find length, find about how long it should be compared to the length of the
			// hole
			g.drawRect(fairwayLocationY, fairwayLocationX, width, distance);
		}
		if (difficulty == Color.white) {
			if (par == 3) {
				distance = (int)(Math.random()*70 + 130);
			}
			if (par == 4) {
				distance = (int)(Math.random()*140 + 300);
			}
			if (par == 5) {
				distance = (int)(Math.random()*250 + 450);
			}
			g.drawRect(fairwayLocationY, fairwayLocationX, width, distance);
		}
		if (difficulty == Color.blue) {
			if (par == 3) {
				distance = (int)(Math.random()*220 + 200);
			}
			if (par == 4) {
				distance = (int)(Math.random()*220 + 200);
			}
			if (par == 5) {
				distance = (int)(Math.random()*220 + 200);
			}
			g.drawRect(fairwayLocationY, fairwayLocationX, width, distance);
		}
		if (difficulty == Color.black) {
			if (par == 3) {

			}
			if (par == 4) {

			}
			if (par == 5) {

			}
			g.drawRect(fairwayLocationY, fairwayLocationX, width, distance);
		}
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
