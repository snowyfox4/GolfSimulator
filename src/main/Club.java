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
        // Basic example: maxDistance adjusted by weather and shot type
        double distance = maxDistance;

        if ("Low".equalsIgnoreCase(shotType)) {
            distance *= 0.9;
        } else if ("High".equalsIgnoreCase(shotType)) {
            distance *= 1.1;
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
        super("Driver", 250, 0.8);
    }
}

class Iron extends Club {
    public Iron() {
        super("Iron", 150, 0.85);
    }
}

class Wedge extends Club {
    public Wedge() {
        super("Wedge", 80, 0.9);
    }
}

class Putter extends Club {
    public Putter() {
        super("Putter", 20, 0.95);
    }
}
