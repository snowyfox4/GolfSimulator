package main;

import javax.swing.*;
import java.awt.*;

public class GolfGame extends JFrame {
    private Course course;
    private Player player;
    private int currentHole;
    private boolean isGameOver;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    private MainMenuPanel mainMenuPanel;
    private JPanel gamePlayPanel;
    private GamePanel gamePanel;
    private SidePanelView sidePanel;

    public GolfGame() {
        setTitle("Par Pursuit Golf Simulator");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        course = new Course(9, Color.GREEN);
        player = new Player("Player1");
        currentHole = 0;
        isGameOver = false;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainMenuPanel = new MainMenuPanel(this);
        gamePanel = new GamePanel(this, course);
        sidePanel = new SidePanelView();
        sidePanel.setPreferredSize(new Dimension(600, 400));

        gamePlayPanel = new JPanel(new BorderLayout());
        gamePlayPanel.add(gamePanel, BorderLayout.CENTER);
        gamePlayPanel.add(sidePanel, BorderLayout.EAST);

        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(gamePlayPanel, "GamePlay");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "MainMenu");
        setVisible(true);
    }

    public void startGameWithHoles(int holes) {
        course = new Course(holes, Color.GREEN);
        gamePanel = new GamePanel(this, course);
        gamePlayPanel.removeAll();
        gamePlayPanel.add(gamePanel, BorderLayout.CENTER);
        gamePlayPanel.add(sidePanel, BorderLayout.EAST);
        currentHole = 0;
        isGameOver = false;
        cardLayout.show(mainPanel, "GamePlay");
        revalidate();
        repaint();
    }

    public Course getCourse() { return course; }
    public Player getPlayer() { return player; }
    public int getCurrentHole() { return currentHole; }
    public boolean isGameOver() { return isGameOver; }

    public void nextHole() {
        currentHole++;
        if (currentHole >= course.getHoleCount()) {
            isGameOver = true;
            JOptionPane.showMessageDialog(this, "Game Over!");
            cardLayout.show(mainPanel, "MainMenu");
        }
    }

    public SidePanelView getSidePanel() {
        return sidePanel;
    }

    public static void main(String[] args) {
        new GolfGame();
    }
}