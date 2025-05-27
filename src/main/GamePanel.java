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
        hole.drawGreen(g);

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
    
    /**
    package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideViewPanel extends JPanel {
    private double shotPower = 100;    // yards
    private double launchAngle = 45;   // degrees
    private double shotCurve = 0;      // left/right offset in yards

    // Ball position in '3D' (distance, height, lateral offset)
    private double ballX = 0; // horizontal distance traveled
    private double ballY = 0; // height (vertical)
    private double ballZ = 0; // lateral offset

    private Timer timer;

    public SideViewPanel() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(135, 206, 235)); // sky blue

        // Animate the ball flight
        timer = new Timer(30, e -> {
            updateBallPosition();
            repaint();
        });

        timer.start();
    }

    private void updateBallPosition() {
        // Simplified projectile motion simulation
        if (ballX < shotPower) {
            ballX += 5; // move forward

            // Calculate height using projectile formula:
            // y = x * tan(angle) - (g * x^2) / (2 * v^2 * cos^2(angle))
            double g = 32.2; // ft/s² gravity
            double v = shotPower * 1.5; // convert yards to some velocity scale
            double angleRad = Math.toRadians(launchAngle);

            ballY = ballX * Math.tan(angleRad) - (g * ballX * ballX) / (2 * v * v * Math.pow(Math.cos(angleRad), 2));
            if (ballY < 0) ballY = 0;

            // Lateral offset: simple curve left/right
            ballZ = shotCurve * (ballX / shotPower);
        } else {
            ballX = 0; // reset for demo
            ballY = 0;
            ballZ = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Draw ground line
        g2d.setColor(new Color(34, 139, 34)); // green
        g2d.fillRect(0, getHeight() - 50, getWidth(), 50);

        // Simple perspective projection:
        // The farther ballX is, the smaller the ball radius
        double maxDistance = 500; // max distance for scaling
        int baseBallRadius = 15;
        int ballRadius = (int) (baseBallRadius * (1 - ballX / maxDistance));
        if (ballRadius < 3) ballRadius = 3;

        // Map 3D coords to 2D panel coords
        int centerX = getWidth() / 2;
        int groundY = getHeight() - 50;

        // lateral offset affects X position on screen
        int ballScreenX = centerX + (int) ballZ;
        // vertical height affects Y position on screen (inverted because (0,0) top-left)
        int ballScreenY = groundY - (int) (ballY * 2); // scale height

        // Draw the ball as a circle
        g2d.setColor(Color.WHITE);
        g2d.fillOval(ballScreenX - ballRadius, ballScreenY - ballRadius, ballRadius * 2, ballRadius * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(ballScreenX - ballRadius, ballScreenY - ballRadius, ballRadius * 2, ballRadius * 2);

        // Draw the tee (behind ball start)
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(centerX - 10, groundY - 10, 20, 10);
    }

    // Setters for sliders to control shot parameters
    public void setShotPower(double power) {
        this.shotPower = power;
    }

    public void setLaunchAngle(double angle) {
        this.launchAngle = angle;
    }

    public void setShotCurve(double curve) {
        this.shotCurve = curve;
    }
}
     */

    private void drawShotPreview(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw a rectangle area for the shot preview
        int x = 700, y = 250, width = 150, height = 150;
        g2d.setColor(new Color(200, 200, 255));
        g2d.fillRect(x, y, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
        g2d.drawString("Shot Preview", x + 30, y + 15);

        // Draw a simplified "3D" golf ball trajectory arc
        int centerX = x + width / 2;
        int baseY = y + height - 30;

        // Shot power controls arc height and length
        int arcWidth = (int) (shotPower * 1.2);
        int arcHeight = (int) (shotPower * 0.7);

        // Adjust arc start angle based on shotAngle slider
        int startAngle = 0;
        int arcAngle = 180;

        // Draw arc representing ball flight
        g2d.setColor(Color.RED);
        g2d.drawArc(centerX - arcWidth / 2, baseY - arcHeight, arcWidth, arcHeight * 2, startAngle, arcAngle);

        // Draw a small circle as golf ball at the start point
        int ballSize = 12;
        int ballX = centerX - ballSize / 2;
        int ballY = baseY - ballSize / 2;
        g2d.setColor(Color.WHITE);
        g2d.fillOval(ballX, ballY, ballSize, ballSize);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(ballX, ballY, ballSize, ballSize);

        // Draw shot angle line (simple rotation indication)
        g2d.setColor(Color.BLUE);
        int lineLength = 50;
        double angleRad = Math.toRadians(-shotAngle); // negative to invert y-axis
        int lineX = (int) (centerX + lineLength * Math.cos(angleRad));
        int lineY = (int) (baseY - lineLength * Math.sin(angleRad));
        g2d.drawLine(centerX, baseY, lineX, lineY);

        g2d.drawString("Power: " + shotPower, x + 10, y + height - 10);
        g2d.drawString("Angle: " + shotAngle + "°", x + 80, y + height - 10);
        g2d.drawString("Club: " + selectedClub, x + 10, y + height - 30);

        g2d.dispose();
    }
}
