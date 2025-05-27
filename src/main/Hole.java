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
	  
	    // Parameters
	    
	    GeneralPath fairway = new GeneralPath();
	    
	    float startX = (int) Math.random() * 1000;
	    float startY = (int) Math.random() * 1000;
	    fairway.moveTo(startX, startY);
	    //Graphics2D g2d = (Graphics2D) g;

        int x = (int)startX;
        int y = (int)startY;
        int width = 100;
        int height = 500;
        double angle = 300; // degrees
        double centerX = x + width / 2.0;
        double centerY = y + height / 2.0;

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);

        g2d.rotate(Math.toRadians(angle), centerX, centerY);
        g2d.draw(rectangle);
        
        
        
        
        //g2d.rotate(-Math.toRadians(angle), centerX, centerY); // Reset rotation
	    if (startX < 500) {
	    	if (startY < 500) {
	    		//Change random numbers
	    		fairway.curveTo(startX, startY, startX + 300, startY + 300, startX + 600, startY + 600);
	    		fairway.curveTo(startX + 600, startY + 600, startY + 400, startY + 400, startX + 300, startY + 300);
	    		fairway.curveTo(startX + 300, startY + 300, startY , startY, startX, startY);
	    	} else { 
	    		fairway.curveTo(startX, startY, startX + 500, startY + 500, startX + 600, startY + 600);
	    		fairway.curveTo(startX + 600, startY + 600, startY + 400, startY + 400, startX + 300, startY + 300);
	    		fairway.curveTo(startX + 300, startY + 300, startY , startY, startX, startY);
	    	}
	    } else {
	    	if (startY < 500) {
	    		fairway.curveTo(startX, startY, startX + 500, startY + 500, startX + 600, startY + 600);
	    		fairway.curveTo(startX + 600, startY + 600, startY + 400, startY + 400, startX + 300, startY + 300);
	    		fairway.curveTo(startX + 300, startY + 300, startY , startY, startX, startY);
	    	} else {
	    		fairway.curveTo(startX, startY, startX + 500, startY + 500, startX + 600, startY + 600);
	    		fairway.curveTo(startX + 600, startY + 600, startY + 400, startY + 400, startX + 300, startY + 300);
	    		fairway.curveTo(startX + 300, startY + 300, startY , startY, startX, startY);
	    	}
	    }
	    
	    // Fill and draw fairway
	    g2d.setColor(new Color(34, 139, 34)); // Forest green
	    g2d.fill(fairway);
	    g2d.setColor(Color.BLACK);
	    g2d.draw(fairway);
	    

	    // Draw Tee Box ON fairway near start (inside the shape)... (So start X start Y --> For green)
	    int teeBoxWidth = 40;
	    int teeBoxHeight = 20;
	    g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect((int)startX - teeBoxWidth/2, (int)startY - teeBoxHeight/2, teeBoxWidth, teeBoxHeight);
	    g2d.setColor(Color.BLACK);
	    g2d.drawRect((int)startX - teeBoxWidth/2, (int)startY - teeBoxHeight/2, teeBoxWidth, teeBoxHeight);

	    // Draw Green ON fairway near end (inside the shape)
	    float greenX = startX + this.distance - 60;
	    float greenY = startY;
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

	    g2d.setColor(new Color(144, 238, 144));  // light green
	    g2d.fill(green);
	    g2d.setColor(Color.BLACK);
	    g2d.draw(green);

	    g2d.dispose();
	}

	public void drawGreen(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;
	    GeneralPath green = new GeneralPath();

	    float greenWidth = 40f;
	    float greenHeight = 30f;

	    // Use greenLocationX, greenLocationY as center for green kidney bean
	    float gx = greenLocationX;
	    float gy = greenLocationY;

	    // Kidney bean shape for green (smaller than fairway)
	    green.moveTo(gx, gy);
	    green.curveTo(gx + greenWidth * 0.3f, gy - greenHeight,
	                  gx + greenWidth * 0.7f, gy + greenHeight,
	                  gx + greenWidth, gy);
	    green.curveTo(gx + greenWidth * 0.7f, gy + greenHeight * 1.2f,
	                  gx + greenWidth * 0.3f, gy - greenHeight * 1.2f,
	                  gx, gy);
	    green.closePath();

	    g2d.setColor(new Color(144, 238, 144)); // light green
	    g2d.fill(green);
	    g2d.setColor(Color.BLACK);
	    g2d.draw(green);
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
