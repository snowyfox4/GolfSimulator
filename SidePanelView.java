package main;

import javax.swing.*;
import java.awt.*;

public class SidePanelView extends JPanel {
    private double shotPower = 100;
    private double launchAngle = 45;
    private double shotCurve = 0;
    private double ballX = 0, ballY = 0, ballZ = 0;
    private Timer timer;

    public SidePanelView() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(135, 206, 235));
        timer = new Timer(30, e -> {
            updateBallPosition();
            repaint();
        });
    }

    public void playShot(double power, double angle, double curve) {
        this.shotPower = power;
        this.launchAngle = angle;
        this.shotCurve = curve;
        ballX = 0; ballY = 0; ballZ = 0;
        timer.start();
    }

    private void updateBallPosition() {
        if (ballX < shotPower) {
            ballX += 5;
            double g = 32.2;
            double v = shotPower * 1.5;
            double angleRad = Math.toRadians(launchAngle);
            ballY = ballX * Math.tan(angleRad) - (g * ballX * ballX) / (2 * v * v * Math.pow(Math.cos(angleRad), 2));
            if (ballY < 0) ballY = 0;
            ballZ = shotCurve * (ballX / shotPower);
        } else {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, getHeight() - 50, getWidth(), 50);

        int maxDistance = 500;
        int baseBallRadius = 15;
        int ballRadius = (int)(baseBallRadius * (1 - ballX / maxDistance));
        if (ballRadius < 3) ballRadius = 3;

        int centerX = getWidth() / 2;
        int groundY = getHeight() - 50;
        int ballScreenX = centerX + (int) ballZ;
        int ballScreenY = groundY - (int) (ballY * 2);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(ballScreenX - ballRadius, ballScreenY - ballRadius, ballRadius * 2, ballRadius * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(ballScreenX - ballRadius, ballScreenY - ballRadius, ballRadius * 2, ballRadius * 2);
    }
}