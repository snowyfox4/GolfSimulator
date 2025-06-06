
package main;
public interface Obstacle {
   boolean isColliding(Ball ball);
   void applyEffect(Ball ball);
}
class Tree implements Obstacle {
   @Override
   public boolean isColliding(Ball ball) {
       // Simplified collision
       return false;
   }
   @Override
   public void applyEffect(Ball ball) {
       // E.g., reduce ball distance or cause stroke penalty
   }
}
class Water implements Obstacle {
   @Override
   public boolean isColliding(Ball ball) {
       return false;
   }
   @Override
   public void applyEffect(Ball ball) {
       // Penalty or reset ball position
   }
}
class SandTrap implements Obstacle {
   @Override
   public boolean isColliding(Ball ball) {
       return false;
   }
   @Override
   public void applyEffect(Ball ball) {
       // E.g., harder shot next turn
}
   
}