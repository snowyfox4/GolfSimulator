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

    public Hole(int par, int distance) {
        this.par = par;
        this.distance = distance;
        this.width = (int)Math.random()*30+30;
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
    public void drawFairway(Graphics g) {
    		//NEED A RANDOM BASED ON DISTANCE :)
    		//RANDOM CURVATURE TOO!!
    	
    	
    		Graphics2D g2d = (Graphics2D) g;
    		

            // Create the GeneralPath
            GeneralPath fairway = new GeneralPath();

            // Define fairway shape with curves
            float startX = 100;
            float startY = 100;
            fairway.moveTo(startX, startY);

            //Use curveTo to create fairway curves
            fairway.curveTo(startX + 50, startY - 50, startX + 150, startY - 50, startX + 200, startY);
            fairway.curveTo(startX+200, startY, startX+300, startY+100, startX+250, startY+200);
            fairway.curveTo(startX+250, startY+200, startX+175, startY+300, startX+300, startY+300);
            fairway.curveTo(startX+300, startY+300, startX+450, startY+350, startX+250, startY+450);
            fairway.curveTo(startX+250, startY+450, startX-100, startY+500, startX, startY);
            
            // Draw and fill the fairway
            g2d.setColor(Color.GREEN);
            g2d.fill(fairway);
            g2d.setColor(Color.BLACK);
            g2d.draw(fairway);
            //Make Bunker and Obstacle 
           
            

    }
    
    public void drawGreen(Graphics g) {
    		Graphics2D g2d = (Graphics2D) g;
    		GeneralPath green = new GeneralPath();
    		
    		g2d.setColor(new Color(144, 238, 144));
    		float startX = 300;
    		float startY = 450;
    		green.moveTo(startX, startY);
    		green.curveTo(startX+50, startY-50, startX+100, startY-50, startX+100, startY); 
    		green.curveTo(startX+100, startY, startX+50, startY+50, startX,startY);
    		green.curveTo(startX, startY, startY, startY, startX, startY);
    		//g2d.setColor(Color.GREEN);
            g2d.fill(green);
            g2d.setColor(Color.BLACK);
            g2d.draw(green);
            
    }
    
    //Changes depending on what tee box
    public void drawTeeBox(Graphics g) {
    		
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
