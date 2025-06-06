package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel implements ActionListener {
   private GolfGame game;
   private Course course;
   private JSlider powerSlider;
   private JSlider angleSlider;
   private JComboBox<String> clubSelector;
   private Timer timer;
   private int shotPower = 50;
   private int shotAngle = 0;
   private String selectedClub = "Driver";
   public GamePanel(GolfGame game, Course course) {
       this.game = game;
       this.course = course;
       setBackground(new Color(135, 206, 235));
       setLayout(null);
       String[] clubs = {"Driver", "Iron", "Wedge", "Putter"};
       clubSelector = new JComboBox<>(clubs);
       clubSelector.setBounds(0, 20, 150, 25);
       clubSelector.addActionListener(e -> {
           selectedClub = (String) clubSelector.getSelectedItem();
           repaint();
       });
       add(clubSelector);
       powerSlider = new JSlider(0, 100, shotPower);
       powerSlider.setBounds(0, 60, 150, 50);
       powerSlider.setMajorTickSpacing(25);
       powerSlider.setPaintTicks(true);
       powerSlider.setPaintLabels(true);
       powerSlider.addChangeListener(e -> {
           shotPower = powerSlider.getValue();
           repaint();
       });
       add(powerSlider);
       angleSlider = new JSlider(0, 100, shotAngle);
       angleSlider.setBounds(700, 130, 150, 50);
       angleSlider.setMajorTickSpacing(15);
       angleSlider.setPaintTicks(true);
       angleSlider.setPaintLabels(true);
       angleSlider.addChangeListener(e -> {
           shotAngle = angleSlider.getValue();
           repaint();
       });
       add(angleSlider);
       JButton courseMapButton = new JButton("View Course Map");
       courseMapButton.setBounds(0, 400, 200, 20);
       courseMapButton.addActionListener(e -> {
           JFrame frame = new JFrame("Full Course Map");
           frame.setSize(840, 560);
           frame.add(new CourseMapViewer(course));
           frame.setLocationRelativeTo(this);
           frame.setVisible(true);
       });
       add(courseMapButton);
       JButton holeOverviewButton = new JButton("Hole Overview");
       holeOverviewButton.setBounds(0, 500, 200, 20);
       holeOverviewButton.addActionListener(e -> {
           Hole currentHole = course.getHoles().get(game.getCurrentHole());
           JFrame frame = new JFrame("Hole " + (game.getCurrentHole() + 1));
           frame.setSize(600, 400);
           frame.add(new HoleViewer(currentHole));
           frame.setLocationRelativeTo(this);
           frame.setVisible(true);
       });
       add(holeOverviewButton);
       JButton scorecardButton = new JButton("Scorecard");
       scorecardButton.setBounds(0, 600, 100, 20);
       scorecardButton.addActionListener(e -> {
           Scorecard scorecard = new Scorecard(course.getHoleCount());
           int[] parList = course.getCourseParList();
           for (int i = 0; i < course.getHoleCount(); i++) {
               scorecard.setPar(i, parList[i]);
               if (i < game.getPlayer().getStrokesPerHole().size()) {
                   scorecard.setScore(i, game.getPlayer().getStrokesPerHole().get(i));
               }
           }
           JFrame frame = new JFrame("Scorecard");
           frame.setSize(850, 200);
           frame.add(new ScorecardViewer(scorecard));
           frame.setLocationRelativeTo(this);
           frame.setVisible(true);
       });
       add(scorecardButton);
       timer = new Timer(50,this);
       timer.start();
       this.setFocusable(true);
   }
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       //drawShotPreview(g);
       Hole hole = course.getHoles().get(game.getCurrentHole());
       hole.drawFairway(g);
       hole.drawFlag(g);
       g.setColor(Color.BLACK);
       g.drawString("Hole: " + (game.getCurrentHole() + 1), 20, 20);
       g.drawString("Par: " + hole.getPar(), 20, 40);
       g.drawString("Distance: " + hole.getDistance() + " yards", 20, 60);
       
       if (hole.getWeather() != null) {
           g.drawString(String.format("Wind: %.1f mph", hole.getWeather().getWindSpeed()), 700, 330);
           g.drawString(String.format("Wind Angle: %.0f°", hole.getWeather().getWindAngle()), 700, 345);
       }
   }
   private void drawShotPreview(Graphics g) {
       Graphics2D g2d = (Graphics2D) g.create();
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       int x = 700, y = 370, width = 200, height = 180;
       g2d.setColor(new Color(135, 206, 250));
       g2d.fillRect(x, y, width, height / 2);
       g2d.setColor(new Color(34, 139, 34));
       g2d.fillRect(x, y + height / 2, width, height / 2);
       g2d.setColor(Color.BLACK);
       g2d.drawRect(x, y, width, height);
       g2d.drawString("Shot Preview", x + 50, y + 15);
       int stanceX = x + width / 2;
       int stanceY = y + height - 20;
       g2d.setStroke(new BasicStroke(2));
       g2d.drawLine(stanceX, stanceY, stanceX, stanceY - 30);
       g2d.drawOval(stanceX - 5, stanceY - 40, 10, 10);
       g2d.drawLine(stanceX, stanceY - 20, stanceX - 10, stanceY - 10);
       double angleRad = Math.toRadians(-shotAngle);
       int clubLength = 40;
       int clubX = (int) (stanceX + clubLength * Math.cos(angleRad));
       int clubY = (int) (stanceY - 15 - clubLength * Math.sin(angleRad));
       g2d.setColor(Color.DARK_GRAY);
       g2d.drawLine(stanceX - 10, stanceY - 15, clubX, clubY);
       int barX = x + 10;
       int barY = y + 30;
       int barHeight = 100;
       int powerHeight = (int) (shotPower * barHeight / 100);
       g2d.setColor(Color.LIGHT_GRAY);
       g2d.fillRect(barX, barY, 15, barHeight);
       g2d.setColor(Color.RED);
       g2d.fillRect(barX, barY + (barHeight - powerHeight), 15, powerHeight);
       g2d.setColor(Color.BLACK);
       g2d.drawRect(barX, barY, 15, barHeight);
       g2d.drawString("Power", barX, barY - 5);
       int angleBarX = x + width - 60;
       int angleBarY = y + height - 30;
       int angleWidth = 50;
       int angleX = angleBarX + 25 + (int) (Math.sin(Math.toRadians(shotAngle)) * 25);
       g2d.setColor(Color.LIGHT_GRAY);
       g2d.fillRect(angleBarX, angleBarY, angleWidth, 8);
       g2d.setColor(Color.BLUE);
       g2d.fillOval(angleX - 5, angleBarY - 5, 10, 10);
       g2d.setColor(Color.BLACK);
       g2d.drawString("Angle", angleBarX + 5, angleBarY - 5);
       int arcCenterX = stanceX;
       int baseY = stanceY - 20;
       int arcWidth = (int) (shotPower * 1.2);
       int arcHeight = (int) (shotPower * 0.6);
       g2d.setColor(Color.RED);
       g2d.drawArc(arcCenterX - arcWidth / 2, baseY - arcHeight, arcWidth, arcHeight * 2, 0, 180);
       g2d.setColor(Color.BLACK);
       g2d.drawString("Power: " + shotPower, x + 10, y + height - 10);
       g2d.drawString("Angle: " + shotAngle + "°", x + 100, y + height - 10);
       g2d.drawString("Club: " + selectedClub, x + 10, y + height - 30);
       g2d.dispose();
   }
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	powerSlider.setValue(powerSlider.getValue()+1);
}
}


