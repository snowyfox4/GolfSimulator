package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel implements MouseListener{
    private GolfGame game;
    private JButton nextShotButton;
    private boolean mainScreen;
    BufferedImage background;
	Timer timer;

    public ControlPanel(GolfGame game) {
        this.game = game;
        this.mainScreen = true;
        //timer = new Timer(100, (ActionListener) this);
        //timer.start();
        setLayout(new FlowLayout());
        
        
        
        nextShotButton = new JButton("Next Shot");
        //nextShotButton.addActionListener(e -> {
        //    // For now, just move to the next hole
        //    game.nextHole();
        //    game.repaint();
        //});

        //add(nextShotButton);
    }
    
    protected void paintComponent(Graphics g) {
    
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (mainScreen) {
        	try {
				background =ImageIO.read(new File("golfMainScreen.jpeg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	g.setColor(Color.BLACK);
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}