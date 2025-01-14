package com.coffeecode.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

public class VisualizerPanel extends JPanel {
    private String[] data;
    private List<Integer> steps;
    private int currentStep;
    private int foundIndex;

    private static final int CELL_WIDTH = 60;
    private static final int CELL_HEIGHT = 40;
    private static final Color CURRENT_COLOR = new Color(255, 255, 0);
    private static final Color VISITED_COLOR = new Color(200, 200, 200);
    private static final Color FOUND_COLOR = new Color(0, 255, 0);

    public VisualizerPanel() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
    }

    public void setData(String[] data) {
        this.data = data;
        this.steps = null;
        this.currentStep = -1;
        this.foundIndex = -1;
        repaint();
    }

    public void visualizeStep(List<Integer> steps, int currentStep, int foundIndex) {
        this.steps = steps;
        this.currentStep = currentStep;
        this.foundIndex = foundIndex;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null)
            return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 10;
        int y = 50;

        for (int i = 0; i < data.length; i++) {
            // Draw cell
            if (i == foundIndex) {
                g2d.setColor(FOUND_COLOR);
            } else if (steps != null && steps.contains(i)) {
                g2d.setColor(VISITED_COLOR);
            } else {
                g2d.setColor(Color.WHITE);
            }

            g2d.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, CELL_WIDTH, CELL_HEIGHT);

            // Draw text
            g2d.drawString(data[i], x + 5, y + 25);

            x += CELL_WIDTH + 5;
        }
    }
}
