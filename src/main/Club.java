package main;

public class Club {
    protected String name;
    protected double maxDistance;
    protected double accuracy;

    public Club(String name, double maxDistance, double accuracy) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.accuracy = accuracy;
    }

    public double calculateDistance(String shotType, Weather weather) {
        double distance = 0;
        if (Math.random() <= this.accuracy) {
        	distance = maxDistance - Math.random() * 20;
        }
        if (Math.random() > this.accuracy) {
        	distance = maxDistance - Math.random() * 70;
        }
        distance -= weather.getWindEffect();
        
        return distance;
    }

    public String getName() {
        return name;
    }
}

class Driver extends Club {
    public Driver() {
        super("Driver", 250, 0.6);
    }
}

class Iron extends Club {
    public Iron() {
        super("Iron", 150, 0.7);
    }
}

class Wedge extends Club {
    public Wedge() {
        super("Wedge", 80, 0.8);
    }
}

class Putter extends Club {
    public Putter() {
        super("Putter", 30, 0.9);
    }
}
