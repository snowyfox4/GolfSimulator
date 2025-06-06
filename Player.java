package main;
import java.util.ArrayList;
public class Player {
   private String name;
   private int totalStrokes;
   private ArrayList<Integer> strokesPerHole;
   public Player(String name) {
       this.name = name;
       this.totalStrokes = 0;
       strokesPerHole = new ArrayList<>();
   }
   public String getName() {
       return name;
   }
   public int getTotalStrokes() {
       return totalStrokes;
   }
   public void addStroke(int strokes) {
       totalStrokes += strokes;
       strokesPerHole.add(strokes);
   }
   public ArrayList<Integer> getStrokesPerHole() {
       return strokesPerHole;
   }
   public void resetStrokes() {
       totalStrokes = 0;
       strokesPerHole.clear();
   }
   // For now, simple placeholder methods
   public String chooseClub() {
       return "Driver"; // placeholder
   }
   public String chooseShotType() {
       return "Normal"; // placeholder
   }
   public void swing(Club club, String shotType, Weather weather) {
       // Placeholder swing logic
   }
}
