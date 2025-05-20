package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GolfGame game;

    public GamePanel(GolfGame game) {
        this.game = game;
        setBackground(Color.CYAN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // For now, just draw the hole number and par info
        Hole hole = game.getCourse().getHoles().get(game.getCurrentHole());

        g.setColor(Color.BLACK);
        g.drawString("Hole: " + (game.getCurrentHole() + 1), 20, 20);
        g.drawString("Par: " + hole.getPar(), 20, 40);
        g.drawString("Distance: " + hole.getDistance() + " yards", 20, 60);

        // TODO: Draw the top-down course map and ball position
    }
}