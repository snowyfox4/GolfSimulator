package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel {
    private GolfGame game;
    private JButton nextShotButton;

    public ControlPanel(GolfGame game) {
        this.game = game;
        setLayout(new FlowLayout());
        
        
        
        nextShotButton = new JButton("Next Shot");
        //nextShotButton.addActionListener(e -> {
        //    // For now, just move to the next hole
        //    game.nextHole();
        //    game.repaint();
        //});

        //add(nextShotButton);
    }
    public void mainScreen() {
    	
    }
}