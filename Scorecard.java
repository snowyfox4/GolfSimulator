package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Scorecard {
    private int numHoles;
    private int[] pars;
    private int[] playerScores;  // For simplicity, just one player

    public Scorecard(int numHoles) {
        if (numHoles != 9 && numHoles != 18) {
            throw new IllegalArgumentException("Only 9 or 18 holes allowed.");
        }
        this.numHoles = numHoles;
        pars = new int[numHoles];
        playerScores = new int[numHoles];
        // Initialize pars and scores to default values (e.g., par 4)
        for (int i = 0; i < numHoles; i++) {
            pars[i] = 4;
            playerScores[i] = 0;  // 0 means no score yet
        }
    }

    public void setPar(int holeIndex, int par) {
        if (holeIndex >= 0 && holeIndex < numHoles) {
            pars[holeIndex] = par;
        }
    }

    public void setScore(int holeIndex, int score) {
        if (holeIndex >= 0 && holeIndex < numHoles) {
            playerScores[holeIndex] = score;
        }
    }

    public int getScore(int holeIndex) {
        if (holeIndex >= 0 && holeIndex < numHoles) {
            return playerScores[holeIndex];
        }
        return 0;
    }

    public int getTotalScore() {
        int total = 0;
        for (int score : playerScores) {
            total += score;
        }
        return total;
    }

    public int getTotalPar() {
        int total = 0;
        for (int p : pars) {
            total += p;
        }
        return total;
    }

    public void drawScorecard(Graphics g, int startX, int startY) {
        int cellWidth = 40;
        int cellHeight = 30;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        // Draw header row: Hole numbers
        for (int i = 0; i < numHoles; i++) {
            g.drawRect(startX + (i + 1) * cellWidth, startY, cellWidth, cellHeight);
            String holeNum = String.valueOf(i + 1);
            g.drawString(holeNum, startX + (i + 1) * cellWidth + 15, startY + 20);
        }
        // Total header
        g.drawRect(startX + (numHoles + 1) * cellWidth, startY, cellWidth, cellHeight);
        g.drawString("Total", startX + (numHoles + 1) * cellWidth + 5, startY + 20);

        // Draw "Par" row
        g.drawRect(startX, startY + cellHeight, cellWidth, cellHeight);
        g.drawString("Par", startX + 10, startY + cellHeight + 20);
        for (int i = 0; i < numHoles; i++) {
            g.drawRect(startX + (i + 1) * cellWidth, startY + cellHeight, cellWidth, cellHeight);
            g.drawString(String.valueOf(pars[i]), startX + (i + 1) * cellWidth + 15, startY + cellHeight + 20);
        }
        g.drawRect(startX + (numHoles + 1) * cellWidth, startY + cellHeight, cellWidth, cellHeight);
        g.drawString(String.valueOf(getTotalPar()), startX + (numHoles + 1) * cellWidth + 15, startY + cellHeight + 20);

        // Draw "Score" row
        g.drawRect(startX, startY + 2 * cellHeight, cellWidth, cellHeight);
        g.drawString("Score", startX + 5, startY + 2 * cellHeight + 20);
        for (int i = 0; i < numHoles; i++) {
            g.drawRect(startX + (i + 1) * cellWidth, startY + 2 * cellHeight, cellWidth, cellHeight);
            String scoreStr = playerScores[i] == 0 ? "-" : String.valueOf(playerScores[i]);
            g.drawString(scoreStr, startX + (i + 1) * cellWidth + 15, startY + 2 * cellHeight + 20);
        }
        g.drawRect(startX + (numHoles + 1) * cellWidth, startY + 2 * cellHeight, cellWidth, cellHeight);
        g.drawString(String.valueOf(getTotalScore()), startX + (numHoles + 1) * cellWidth + 15, startY + 2 * cellHeight + 20);
    }
}

