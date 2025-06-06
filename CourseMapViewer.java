package main;
import javax.swing.*;
import java.awt.*;
public class CourseMapViewer extends JPanel {
   private Course course;
   public CourseMapViewer(Course course) {
       this.course = course;
       setPreferredSize(new Dimension(820, 520));
       setBackground(new Color(220, 255, 220));
   }
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       course.drawCourse(g);
   }
}


