package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Course {
	// Hole Drawing List
	private ArrayList<GeneralPath> holeDrawings;
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
	// Maybe int... but color is fine (Red, Yellow, White, Blue, Then you can just
	// convert)
	private Color difficulty;
	private int difficultyAddedDistance;
	// Preset pars for 9-hole and 18-hole courses
	private final int[] parList9 = { 4, 4, 5, 4, 3, 4, 5, 3, 4 };
	private final int[] parList18 = { 4, 4, 5, 4, 3, 4, 5, 3, 4, 4, 4, 5, 4, 3, 4, 5, 3, 4 };

	/**
	 * Constructor to create a Course. Randomizes pars for each hole based on
	 * holeCount.
	 * 
	 * @param holeCount  Number of holes (9 or 18)
	 * @param difficulty Color to represent course difficulty
	 */
	public Course(int holeCount, Color difficulty) {
		this.holeCount = holeCount;
		this.coursePar = 0;
		this.courseParList = new int[holeCount];
		if (difficulty == Color.RED) 
			difficultyAddedDistance = 0;
		if (difficulty==Color.YELLOW)
			difficultyAddedDistance = 20;
		if(difficulty == Color.WHITE)
			difficultyAddedDistance = 40;
		if(difficulty == Color.BLUE)
			difficultyAddedDistance = 70;
		

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
	 * Generates holes based on the pars stored in courseParList. Assigns random
	 * distances to holes based on par.
	 */
	public void generateCourse() {
		int distance;
		for (int i = 0; i < holeCount; i++) {
			int par = courseParList[i];
			if (par == 3) {
				// Par 3 hole, distance between 120 and 220 yards
				distance = (int) Math.random() * 100 + 120 + difficultyAddedDistance;
				holes.add(new Hole(3, distance, currentWeather));
			} else if (par == 4) {
				// Par 4 hole, distance between 260 and 390 yards
				distance = (int) Math.random() * 130 + 260 + difficultyAddedDistance;
				holes.add(new Hole(4, distance, currentWeather));
			} else if (par == 5) {
				// Par 5 hole, distance between 450 and 550 yards
				distance = (int) Math.random() * 100 + 450 + difficultyAddedDistance;
				holes.add(new Hole(5, distance, currentWeather));
			}
		}
	}
	/**
	 * Draw the course by making boxes for each individual hole and spreading them out/turning them
	 * @param g
	 */

	public void drawCourse(Graphics g) {
		if (holeDrawings == null || holeDrawings.isEmpty())
			return;

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int panelWidth = 800;
		int panelHeight = 500;
		int padding = 20;

		int numHoles = holeDrawings.size();

		// Choose grid layout based on number of holes
		int cols, rows;
		if (numHoles == 9) {
			cols = 3;
			rows = 3;
		} else if (numHoles == 18) {
			cols = 6;
			rows = 3;
		} else {
			cols = (int) Math.ceil(Math.sqrt(numHoles));
			rows = (int) Math.ceil((double) numHoles / cols);
		}

		int cellWidth = (panelWidth - 2 * padding) / cols;
		int cellHeight = (panelHeight - 2 * padding) / rows;

		for (int i = 0; i < numHoles; i++) {
			GeneralPath path = holeDrawings.get(i);

			// Bounds of original path
			var bounds = path.getBounds2D();
			double scaleX = (cellWidth - 20) / bounds.getWidth();
			double scaleY = (cellHeight - 20) / bounds.getHeight();
			double scale = Math.min(scaleX, scaleY);

			// Scale and translate path
			GeneralPath transformed = (GeneralPath) path.clone();
			transformed.transform(java.awt.geom.AffineTransform.getTranslateInstance(-bounds.getX(), -bounds.getY()));
			transformed.transform(java.awt.geom.AffineTransform.getScaleInstance(scale, scale));

			int col = i % cols;
			int row = i / cols;
			double offsetX = padding + col * cellWidth + (cellWidth - bounds.getWidth() * scale) / 2;
			double offsetY = padding + row * cellHeight + (cellHeight - bounds.getHeight() * scale) / 2;

			transformed.transform(java.awt.geom.AffineTransform.getTranslateInstance(offsetX, offsetY));

			// Draw
			g2d.setColor(Color.GREEN.darker());
			g2d.fill(transformed);
			g2d.setColor(Color.BLACK);
			g2d.draw(transformed);

			// Label the hole number
			g2d.drawString("Hole " + (i + 1), (int) offsetX + 5, (int) offsetY + 15);
		}

		g2d.dispose();
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

	public void addHoleDrawing(GeneralPath a) {
		holeDrawings.add(a);
	}
}