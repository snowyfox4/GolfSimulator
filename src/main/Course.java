package main;

import java.awt.Color;
import java.util.*;
public class Course {
    ArrayList<Hole> holes;
    Weather currentWeather;
    int holeCount;
    int coursePar;
  
    int[] parList9 = {4, 4, 5, 4, 3, 4, 5, 3, 4};
    int[] parList18 = {4, 4, 5, 4, 3, 4, 5, 3, 4, 4, 4, 5, 4, 3, 4, 5, 3, 4};
    int[] courseParList;
  
   public Course(int holeCount, Color dificulty) {
   	this.holeCount = holeCount;
   	int j;
   	//Randomize Pars for 9 Holes
   	if (holeCount == 9) {
   		for (int i = 0; i < holeCount; i++) {
   			j = (int)(Math.random()*9);
   			courseParList[i] = parList9[j];
   			coursePar+=parList9[j];
   		}
   	}
   	//Randomize Pars for 18 Holes
   	if (holeCount == 18) {
   		for (int i = 0; i < holeCount; i++) {
   			j = (int)(Math.random()*18);
   			courseParList[i] = parList18[j];
   			coursePar+=parList18[j];
   		}
   	}
       currentWeather = new Weather();
       holes = new ArrayList<Hole>(holeCount);
       generateCourse();
   }
   public void generateCourse() {
   	//Generate the course based on all the pars generated in the begining
       for (int i = 0; i < holeCount; i++) {
       	if (courseParList[i] == 3) {
       		holes.add(new Hole(3, (int)(Math.random()*100 + 120)));
       	}
       	if (courseParList[i] == 4) {
       		holes.add(new Hole(4, (int)(Math.random()*130 + 260)));
       	}
       	if (courseParList[i] == 5) {
       		holes.add(new Hole(5, (int)(Math.random()*100 + 450)));
       	}
       }
   
   }
  
  
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
