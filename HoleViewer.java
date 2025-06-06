package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HoleViewer extends JPanel {
   private Hole hole;
   public HoleViewer(Hole hole) {
       this.hole = hole;
       setPreferredSize(new Dimension(600, 400));
       setBackground(new Color(135, 206, 235));
   }
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       hole.drawFairway(g);
       hole.drawFlag(g);
       g.setColor(Color.BLACK);
       g.drawString("Par: " + hole.getPar(), 20, 20);
       g.drawString("Distance: " + hole.getDistance() + " yards", 20, 40);
       if (hole.getWeather() != null) {
           g.drawString(String.format("Wind: %.1f mph", hole.getWeather().getWindSpeed()), 20, 60);
           g.drawString(String.format("Wind Angle: %.0f°", hole.getWeather().getWindAngle()), 20, 75);
       }
   }
}
