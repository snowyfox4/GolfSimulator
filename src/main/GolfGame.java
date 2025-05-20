package main;

import javax.swing.*;
import java.awt.*;

public class GolfGame extends JFrame {
    private Course course;
    private Player player;
    private int currentHole;
    private boolean isGameOver;

    private GamePanel gamePanel;
    private ControlPanel controlPanel;

    public GolfGame() {
        setTitle("Par Pursuit Golf Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize with 9-hole course for now, difficulty ignored
        course = new Course(9, Color.GREEN);
        player = new Player("Player1");

        currentHole = 0;
        isGameOver = false;

        gamePanel = new GamePanel(this);
        controlPanel = new ControlPanel(this);

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void startGame() {
        currentHole = 0;
        isGameOver = false;
        // More game start logic here
    }

    public void playHole() {
        // Placeholder for game loop per hole
    }

    public void displayScorecard() {
        // Show final scores (to implement)
    }

    // Getters
    public Course getCourse() {
        return course;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentHole() {
        return currentHole;
    }

    public void nextHole() {
        currentHole++;
        if (currentHole >= course.getHoleCount()) {
            isGameOver = true;
            displayScorecard();
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public static void main(String[] args) {
        new GolfGame();
    }
}