package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private GolfGame game;
    private Course course;

    // UI controls
    private JSlider powerSlider;
    private JSlider angleSlider;
    private JComboBox<String> clubSelector;

    // Current shot parameters
    private int shotPower = 50;    // 0-100
    private int shotAngle = 0;     // -45 to +45 degrees
    private String selectedClub = "Driver";

    public GamePanel(GolfGame game, Course course) {
        this.game = game;
        this.course = course;
        setBackground(new Color(135, 206, 235)); // sky blue

        setLayout(null); // We'll position controls manually

        // Club selector combo box
        String[] clubs = {"Driver", "3 Wood", "5 Iron", "7 Iron", "9 Iron", "Pitching Wedge", "Putter"};
        clubSelector = new JComboBox<>(clubs);
        clubSelector.setBounds(700, 20, 150, 25);
        clubSelector.addActionListener(e -> {
            selectedClub = (String) clubSelector.getSelectedItem();
            repaint();
        });
        add(clubSelector);

        // Power slider
        powerSlider = new JSlider(0, 100, shotPower);
        powerSlider.setBounds(700, 60, 150, 50);
        powerSlider.setMajorTickSpacing(25);
        powerSlider.setPaintTicks(true);
        powerSlider.setPaintLabels(true);
        powerSlider.addChangeListener(e -> {
            shotPower = powerSlider.getValue();
            repaint();
        });
        add(powerSlider);

        // Angle slider
        angleSlider = new JSlider(-45, 45, shotAngle);
        angleSlider.setBounds(700, 130, 150, 50);
        angleSlider.setMajorTickSpacing(15);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        angleSlider.addChangeListener(e -> {
            shotAngle = angleSlider.getValue();
            repaint();
        });
        add(angleSlider);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Hole hole = course.getHoles().get(game.getCurrentHole());

        // Draw top-down fairway and green
        hole.drawFairway(g);

        // Draw some text info
        g.setColor(Color.BLACK);
        g.drawString("Hole: " + (game.getCurrentHole() + 1), 20, 20);
        g.drawString("Par: " + hole.getPar(), 20, 40);
        g.drawString("Distance: " + hole.getDistance() + " yards", 20, 60);

        // Draw "3D" style shot preview area
        drawShotPreview(g);

        // Draw wind info
        if (hole.getWeather() != null) {
            g.drawString(String.format("Wind: %.1f mph", hole.getWeather().getWindSpeed()), 700, 200);
            g.drawString(String.format("Wind Angle: %.0f°", hole.getWeather().getWindAngle()), 700, 215);
        }
    }
    
   
    private void drawShotPreview(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 700, y = 250, width = 200, height = 180;

        // Background
        g2d.setColor(new Color(135, 206, 250)); // Sky
        g2d.fillRect(x, y, width, height / 2);
        g2d.setColor(new Color(34, 139, 34)); // Ground
        g2d.fillRect(x, y + height / 2, width, height / 2);

        // Labels
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
        g2d.drawString("Shot Preview", x + 50, y + 15);

        // Draw club and stance (simple stick-figure representation)
        int stanceX = x + width / 2;
        int stanceY = y + height - 20;
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(stanceX, stanceY, stanceX, stanceY - 30); // body
        g2d.drawOval(stanceX - 5, stanceY - 40, 10, 10); // head
        g2d.drawLine(stanceX, stanceY - 20, stanceX - 10, stanceY - 10); // arm holding club

        // Draw club line (rotate with shot angle)
        double angleRad = Math.toRadians(-shotAngle);
        int clubLength = 40;
        int clubX = (int) (stanceX + clubLength * Math.cos(angleRad));
        int clubY = (int) (stanceY - 15 - clubLength * Math.sin(angleRad));
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawLine(stanceX - 10, stanceY - 15, clubX, clubY);

        // Power bar (oscillating effect)
        int barX = x + 10;
        int barY = y + 30;
        int barHeight = 100;
        int powerHeight = (int) (shotPower * barHeight / 100);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(barX, barY, 15, barHeight);
        g2d.setColor(Color.RED);
        g2d.fillRect(barX, barY + (barHeight - powerHeight), 15, powerHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(barX, barY, 15, barHeight);
        g2d.drawString("Power", barX, barY - 5);

        // Angle bar (left-right control)
        int angleBarX = x + width - 60;
        int angleBarY = y + height - 30;
        int angleWidth = 50;
        int angleX = angleBarX + 25 + (int) (Math.sin(Math.toRadians(shotAngle)) * 25);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(angleBarX, angleBarY, angleWidth, 8);
        g2d.setColor(Color.BLUE);
        g2d.fillOval(angleX - 5, angleBarY - 5, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Angle", angleBarX + 5, angleBarY - 5);

        // Trajectory arc
        int arcCenterX = stanceX;
        int baseY = stanceY - 20;
        int arcWidth = (int) (shotPower * 1.2);
        int arcHeight = (int) (shotPower * 0.6);
        g2d.setColor(Color.RED);
        g2d.drawArc(arcCenterX - arcWidth / 2, baseY - arcHeight, arcWidth, arcHeight * 2, 0, 180);

        // Shot info
        g2d.setColor(Color.BLACK);
        g2d.drawString("Power: " + shotPower, x + 10, y + height - 10);
        g2d.drawString("Angle: " + shotAngle + "°", x + 100, y + height - 10);
        g2d.drawString("Club: " + selectedClub, x + 10, y + height - 30);

        g2d.dispose();
    }

}
