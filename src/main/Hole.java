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

	private Color difficulty;
	
	//Draw Hole Stuff
	private float ctrl1X;
	private float ctrl1Y;
	private float ctrl2X;
	private float ctrl2Y;
	
	private float [] changeY;
	private float[] fairSway;
	private float[] lessSway;
	
	private float[] fairwayCurve1;
	private float[] fairwayCurve2;
	
	
	private int curves;
	private float segmentLength;
	
	private int random;
	
	private int offsetX;
	private int offsetY;
	private int offsetX1;
	private int offsetY1;
	private int offsetX2;
	private int offsetY2;

	public Hole(int par, int distance, Weather weather) {
		this.distance = distance;
		this.par = par;
		this.width = (int) Math.random() * 30 + 30;
		obstacles = new ArrayList<>();
		this.weather = weather;
		this.difficulty = Color.white;
		this.fairwayLocationX = 400;
	    this.fairwayLocationY = 300;
	    
	    offsetX1 = (int) (Math.random() * 100 - 50);
	    offsetY1 = (int) (Math.random() * 100 - 50);
	    
	    offsetX2 = (int) (Math.random() * 60 - 30);
	    offsetX1 = (int) (Math.random() * 60 - 30);
	    
	   

        this.curves = (int) Math.max(3, distance / 100);
        fairSway = new float[curves];
        lessSway = new float[curves];
        changeY = new float[curves];
        fairwayCurve1 = new float[curves];
        fairwayCurve2 = new float [curves];
        for(int i = 0; i<fairSway.length;i++)
        	fairSway[i] = (float) (Math.random() * 60 - 30);
		
        for (int i = 0; i < lessSway.length; i++) {
        	lessSway[i] = (float) (Math.random() * 60 - 30);
        }
        
        for(int i = 0; i< changeY.length; i++) {
        	changeY[i] = (float) (Math.random() * 60 - 30); 
        }
        for(int i = 0; i< fairwayCurve1.length; i++) {
        	fairwayCurve1[i] = (float) (Math.random() * segmentLength * 0.3f);
        }
        for(int i = 0; i<fairwayCurve2.length; i++) {
        	fairwayCurve2[i] = (float) (Math.random() * segmentLength * 0.3f);
        }
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

	    

	    GeneralPath fairway = new GeneralPath();
	    fairway.moveTo(fairwayLocationX, fairwayLocationY);


	    ArrayList<Point2D.Float> fairwayPoints = new ArrayList<>();
	    
	    fairwayPoints.add(new Point2D.Float(fairwayLocationX, fairwayLocationY));
	    
	    
	
	    this.segmentLength = distance / (float) curves;
	    
	    float tempFairX = this.fairwayLocationX;
	    float tempFairY = this.fairwayLocationY;
	    
	   
	    
	    for (int i = 0; i < curves; i++) {

		    this.ctrl1X = tempFairX + segmentLength * 0.25f + fairwayCurve1[i];
	        this.ctrl1Y = tempFairY + lessSway[i]; // reduced sway
	        this.ctrl2X = tempFairX + segmentLength * 0.75f + fairwayCurve2[i];
	        this.ctrl2Y = tempFairY + fairSway[i];
	        
	        
	        fairway.curveTo(ctrl1X, ctrl1Y, ctrl2X, ctrl2Y, tempFairX, tempFairY);
	        fairwayPoints.add(new Point2D.Float(tempFairX, tempFairY));
	        tempFairX += segmentLength;
	        tempFairY += changeY[i];
	        
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
	    g2d.fillRect((int) fairwayLocationX - teeBoxWidth / 2, (int) fairwayLocationY - teeBoxHeight / 2, teeBoxWidth, teeBoxHeight);
	    g2d.setColor(Color.BLACK);
	    g2d.drawRect((int) fairwayLocationX - teeBoxWidth / 2, (int) fairwayLocationY - teeBoxHeight / 2, teeBoxWidth, teeBoxHeight);

	    // Green
	    float greenX = fairwayLocationX;
	    float greenY = fairwayLocationY;
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
	    	  this.random = (int)(Math.random());
	          
	        Point2D.Float point = fairwayPoints.get(random * fairwayPoints.size());
	        if (i % 7 == 5) {
	            this.offsetX = offsetX2;
	            this.offsetY = offsetY2;
	        } else {
	            this.offsetX = offsetX1;
	            this.offsetY = offsetY1;
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

	    // Draw flag as a wavy triangl...maybe no so that when windy you can see it
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