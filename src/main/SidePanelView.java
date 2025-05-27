
    package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SidePanelView extends JPanel {
    private double shotPower = 100;    // yards
    private double launchAngle = 45;   // degrees
    private double shotCurve = 0;      // left/right offset in yards

    // Ball position in '3D' (distance, height, lateral offset)
    private double ballX = 0; // horizontal distance traveled
    private double ballY = 0; // height (vertical)
    private double ballZ = 0; // lateral offset

    private Timer timer;

    public SidePanelView() {
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
