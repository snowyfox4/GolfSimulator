package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class MainMenuPanel extends JPanel {
   public MainMenuPanel(GolfGame game) {
       setLayout(new GridBagLayout());
       setBackground(new Color(34, 139, 34));  // green background
       JLabel title = new JLabel("Par Pursuit Golf");
       title.setFont(new Font("SansSerif", Font.BOLD, 36));
       title.setForeground(Color.WHITE);
       JButton nineHolesButton = new JButton("Play 9 Holes");
       JButton eighteenHolesButton = new JButton("Play 18 Holes");
       JButton instructionsButton = new JButton("Instructions");
       JButton exitButton = new JButton("Exit");
       // Button actions
       nineHolesButton.addActionListener((ActionEvent e) -> game.startGameWithHoles(9));
       eighteenHolesButton.addActionListener((ActionEvent e) -> game.startGameWithHoles(18));
       instructionsButton.addActionListener((ActionEvent e) ->
           JOptionPane.showMessageDialog(
               this,
               "Use the sliders to control shot power and angle.\n" +
               "Select a club and hit the ball. Try to finish under par!",
               "Instructions",
               JOptionPane.INFORMATION_MESSAGE
           )
       );
       exitButton.addActionListener((ActionEvent e) -> System.exit(0));
       // Layout
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(15, 0, 15, 0);
       gbc.gridx = 0;
       add(title, gbc);
       add(nineHolesButton, gbc);
       add(eighteenHolesButton, gbc);
       add(instructionsButton, gbc);
       add(exitButton, gbc);
   }
}
