package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GolfGame game;
    private JSlider powerSlider, angleSlider;
    private JComboBox<String> clubSelector;

    public GamePanel(GolfGame game, Course course) {
        this.game = game;
        setBackground(new Color(135, 206, 235));
        setLayout(null);

        clubSelector = new JComboBox<>(new String[]{"Driver", "3 Wood", "5 Iron", "7 Iron", "Pitching Wedge"});
        clubSelector.setBounds(30, 30, 150, 25);
        add(clubSelector);

        powerSlider = new JSlider(0, 100, 50);
        powerSlider.setBounds(30, 70, 150, 50);
        powerSlider.setPaintTicks(true);
        powerSlider.setPaintLabels(true);
        powerSlider.setMajorTickSpacing(25);
        add(powerSlider);

        angleSlider = new JSlider(-45, 45, 0);
        angleSlider.setBounds(30, 140, 150, 50);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        angleSlider.setMajorTickSpacing(15);
        add(angleSlider);

        JButton hitButton = new JButton("Hit Shot");
        hitButton.setBounds(30, 210, 150, 30);
        hitButton.addActionListener(e -> {
            double power = powerSlider.getValue();
            double angle = angleSlider.getValue();
            double curve = 0;
            game.getSidePanel().playShot(power, angle, curve);
        });
        add(hitButton);
    }
}