package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Course {
    // List of holes in this course
    private ArrayList<Hole> holes;
    // Current weather affecting the course
    private Weather currentWeather;
    // Number of holes (9 or 18)
    private int holeCount;
    // Total par for the course
    private int coursePar;
    // Par values for the course holes
    private int[] courseParList;
    
    
    

    // Preset pars for 9-hole and 18-hole courses
    private final int[] parList9 = {4, 4, 5, 4, 3, 4, 5, 3, 4};
    private final int[] parList18 = {4, 4, 5, 4, 3, 4, 5, 3, 4, 4, 4, 5, 4, 3, 4, 5, 3, 4};

    /**
     * Constructor to create a Course.
     * Randomizes pars for each hole based on holeCount.
     * @param holeCount Number of holes (9 or 18)
     * @param difficulty (Currently unused) Color to represent course difficulty
     */
    public Course(int holeCount, Color difficulty) {
        this.holeCount = holeCount;
        this.coursePar = 0;
        this.courseParList = new int[holeCount];
        
        // Randomly assign pars for each hole based on holeCount
        if (holeCount == 9) {
            for (int i = 0; i < holeCount; i++) {
                int j = (int) (Math.random() * parList9.length);
                courseParList[i] = parList9[j];
                coursePar += parList9[j];
            }
        } else if (holeCount == 18) {
            for (int i = 0; i < holeCount; i++) {
                int j = (int) (Math.random() * parList18.length);
                courseParList[i] = parList18[j];
                coursePar += parList18[j];
            }
        }

        currentWeather = new Weather();
        holes = new ArrayList<>(holeCount);
        generateCourse();
    }

    /**
     * Generates holes based on the pars stored in courseParList.
     * Assigns random distances to holes based on par.
     */
    public void generateCourse() {
        for (int i = 0; i < holeCount; i++) {
            int par = courseParList[i];
            if (par == 3) {
                // Par 3 hole, distance between 120 and 220 yards
                holes.add(new Hole(3, currentWeather));
            } else if (par == 4) {
                // Par 4 hole, distance between 260 and 390 yards
                holes.add(new Hole(4, currentWeather));
            } else if (par == 5) {
                // Par 5 hole, distance between 450 and 550 yards
                holes.add(new Hole(5, currentWeather));
            }
        }
    }
    
    public void drawCourse() {
    	
    }
    // Getters and setters
    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public void setHoles(ArrayList<Hole> holes) {
        this.holes = holes;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public int getHoleCount() {
        return holeCount;
    }

    public void setHoleCount(int holeCount) {
        this.holeCount = holeCount;
    }

    public int getCoursePar() {
        return coursePar;
    }

    public void setCoursePar(int coursePar) {
        this.coursePar = coursePar;
    }

    public int[] getCourseParList() {
        return courseParList;
    }

    public void setCourseParList(int[] courseParList) {
        this.courseParList = courseParList;
    }

    public int getTotalHoles() {
        return holeCount;
    }

    public int getPar() {
        return coursePar;
    }

    public Weather getWeather() {
        return currentWeather;
    }
}