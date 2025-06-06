package main;
public class Ball {
   private double xPos, yPos, zPos;
   private boolean inHole;
   public Ball() {
       resetPosition();
       inHole = false;
   }
   public void move(double xDelta, double yDelta, double zDelta) {
       xPos += xDelta;
       yPos += yDelta;
       zPos += zDelta;
   }
   public void resetPosition() {
       xPos = 0;
       yPos = 0;
       zPos = 0;
       inHole = false;
   }
   public boolean isInHole() {
       return inHole;
   }
   public void setInHole(boolean inHole) {
       this.inHole = inHole;
   }
   // Getters
   public double getX() { return xPos; }
   public double getY() { return yPos; }
   public double getZ() { return zPos; }
}


