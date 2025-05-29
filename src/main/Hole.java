package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Hole {
	private Course course;
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

	public Hole(int par, int distance, Weather weather) {
		this.distance = distance;
		this.par = par;
		this.width = (int) Math.random() * 30 + 30;
		obstacles = new ArrayList<>();
		this.weather = weather;
		this.difficulty = Color.white;
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
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    float startX = 400;
	    float startY = 300;

	    GeneralPath fairway = new GeneralPath();
	    fairway.moveTo(startX, startY);

	    int curves = (int) Math.max(3, distance / 100);
	    float segmentLength = distance / (float) curves;
	    float x = startX;
	    float y = startY;

	    ArrayList<Point2D.Float> fairwayPoints = new ArrayList<>();
	    fairwayPoints.add(new Point2D.Float(x, y));

	    for (int i = 0; i < curves; i++) {
	        float ctrl1X = x + segmentLength * 0.25f + (float) (Math.random() * segmentLength * 0.3f);
	        float ctrl1Y = y + (float) (Math.random() * 60 - 30); // reduced sway
	        float ctrl2X = x + segmentLength * 0.75f + (float) (Math.random() * segmentLength * 0.3f);
	        float ctrl2Y = y + (float) (Math.random() * 60 - 30);
	        x += segmentLength;
	        y += (float) (Math.random() * 60 - 30);
	        fairway.curveTo(ctrl1X, ctrl1Y, ctrl2X, ctrl2Y, x, y);
	        fairwayPoints.add(new Point2D.Float(x, y));
	    }

	    float strokeWidth = Math.min(70, Math.max(40, distance / 10f));
	    g2d.setColor(new Color(34, 139, 34));
	    g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	    g2d.draw(fairway);
	    g2d.setStroke(new BasicStroke(3));

	    // Tee Box
	    int teeBoxWidth = 40;
	    int teeBoxHeight = 20;
	    g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect((int) startX - teeBoxWidth / 2, (int) startY - teeBoxHeight / 2, teeBoxWidth, teeBoxHeight);
	    g2d.setColor(Color.BLACK);
	    g2d.drawRect((int) startX - teeBoxWidth / 2, (int) startY - teeBoxHeight / 2, teeBoxWidth, teeBoxHeight);

	    // Green
	    float greenX = x;
	    float greenY = y;
	    float greenWidth = 40f;
	    float greenHeight = 20f;

	    GeneralPath green = new GeneralPath();
	    green.moveTo(greenX, greenY);
	    green.curveTo(greenX + greenWidth * 0.2f, greenY - greenHeight,
	            greenX + greenWidth * 0.8f, greenY - greenHeight,
	            greenX + greenWidth, greenY);
	    green.curveTo(greenX + greenWidth * 0.8f, greenY + greenHeight,
	            greenX + greenWidth * 0.2f, greenY + greenHeight,
	            greenX, greenY);
	    green.closePath();

	    g2d.setColor(new Color(144, 238, 144));
	    g2d.fill(green);
	    g2d.setColor(Color.BLACK);
	    g2d.draw(green);

	    // Hazards (controlled water placement, more trees)
	    for (int i = 0; i < 24; i++) {
	        Point2D.Float point = fairwayPoints.get((int)(Math.random() * fairwayPoints.size()));

	        int offsetX, offsetY;
	        if (i % 6 == 5) {
	            offsetX = (int) (Math.random() * 60 - 30);
	            offsetY = (int) (Math.random() * 60 - 30);
	        } else {
	            offsetX = (int) (Math.random() * 100 - 50);
	            offsetY = (int) (Math.random() * 100 - 50);
	        }

	        int hazardX = (int) point.x + offsetX;
	        int hazardY = (int) point.y + offsetY;

	        if (i % 7 < 5) { // More trees
	            g2d.setColor(new Color(0, 100, 0));
	            g2d.fillOval(hazardX, hazardY, 20, 20);
	        } else if (i % 7 == 5) { // Fewer bunkers
	            g2d.setColor(new Color(210, 180, 140));
	            g2d.fillOval(hazardX, hazardY, 30, 20);
	            g2d.setColor(Color.BLACK);
	            g2d.drawOval(hazardX, hazardY, 30, 20);
	        } else { // Fewer water hazards
	            g2d.setColor(new Color(30, 144, 255));
	            g2d.fillOval(hazardX, hazardY, 40, 25);
	            g2d.setColor(Color.BLUE);
	            g2d.drawOval(hazardX, hazardY, 40, 25);
	        }
	    }

	    g2d.dispose();
	}



	// Draw Flag
	public void drawFlag(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    // Flag pole location
	    int poleX = greenLocationX;
	    int poleY = greenLocationY - 60;

	    // Draw flagpole
	    g2d.setColor(Color.DARK_GRAY);
	    g2d.setStroke(new BasicStroke(3));
	    g2d.drawLine(poleX, poleY, poleX, poleY + 60);

	    // Get wind info
	    double windSpeed = weather != null ? weather.getWindSpeed() : 0;
	    double windAngle = weather != null ? weather.getWindAngle() : 0;

	    // Flag base point (top of the pole)
	    int baseX = poleX;
	    int baseY = poleY;

	    // Waving amount = more wind, more curve
	    int waveOffset = (int) (windSpeed * 2); // Feel free to adjust

	    // Wind direction: right or left
	    int direction = Math.cos(Math.toRadians(windAngle)) >= 0 ? 1 : -1;

	    // Draw flag as a wavy triangle
	    GeneralPath flag = new GeneralPath();
	    flag.moveTo(baseX, baseY); // attach to pole
	    flag.lineTo(baseX + direction * 20, baseY + 10); // top curve
	    flag.curveTo(
	        baseX + direction * (10 + waveOffset), baseY + 20,
	        baseX + direction * (20 + waveOffset), baseY + 40,
	        baseX, baseY + 40
	    );
	    flag.closePath();

	    g2d.setColor(Color.RED);
	    g2d.fill(flag);
	    g2d.setColor(Color.BLACK);
	    g2d.draw(flag);
	    
	    g2d.dispose();
	}

	// Changes depending on what tee box
	

	public boolean isInHazard(Ball ball) {
		for (Obstacle o : obstacles) {
			if (o.isColliding(ball)) {
				return true;
			}
		}
		return false;
	}
	public Weather getWeather() {
        return weather;
    }
}
