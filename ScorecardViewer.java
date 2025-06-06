package main;
import javax.swing.*;
import java.awt.*;
public class ScorecardViewer extends JPanel {
   private Scorecard scorecard;
   public ScorecardViewer(Scorecard scorecard) {
       this.scorecard = scorecard;
       setPreferredSize(new Dimension(800, 150));
       setBackground(Color.WHITE);
   }
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       scorecard.drawScorecard(g, 20, 30);
   }
}
