package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
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
    public void drawFairway(Graphics g) {

    		Graphics2D g2d = (Graphics2D) g;
    		 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    	        // Fairway color
    	        g2d.setColor(new Color(60, 179, 113)); // medium sea green

    	        GeneralPath bean = new GeneralPath();

    	        // Start at the top left
    	        bean.moveTo(150, 100);

    	        // Right bulging outer curve
    	        bean.curveTo(300, 80, 500, 180, 450, 250);

    	        // Lower curve bending inward (like kidney bean shape)
    	        bean.curveTo(400, 320, 200, 300, 180, 260);

    	        // Upper left inward curve returning to the start
    	        bean.curveTo(120, 220, 130, 150, 150, 100);

    	        bean.closePath();

    	        // Fill and draw
    	        g2d.fill(bean);

    	        // Optional outline
    	        g2d.setColor(Color.DARK_GRAY);
    	        g2d.draw(bean);

    }
    
    public void drawGreen(Graphics g) {
    	
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
