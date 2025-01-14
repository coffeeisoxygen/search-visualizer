package com.coffeecode.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import com.coffeecode.service.search.SearchState;

public class VisualizerPanel extends JPanel {
    private static final int CELL_WIDTH = 80;
    private static final int CELL_HEIGHT = 30;
    private static final int PADDING = 10;
    private static final Color CURRENT_COLOR = new Color(255, 255, 0); // Yellow
    private static final Color TARGET_COLOR = new Color(0, 255, 0); // Green
    private static final Color DEFAULT_COLOR = new Color(200, 200, 200); // Light Gray

    private String[] data;
    private List<Integer> visitedIndices;
    private int currentIndex = -1;
    private int targetIndex = -1;
    private int lowIndex = -1;
    private int midIndex = -1;
    private int highIndex = -1;

    public VisualizerPanel() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null || data.length == 0)
            return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int startX = PADDING;
        int startY = getHeight() / 2 - CELL_HEIGHT / 2;

        // Draw cells
        for (int i = 0; i < data.length; i++) {
            // Set cell color based on state
            if (i == targetIndex) {
                g2d.setColor(TARGET_COLOR);
            } else if (i == currentIndex) {
                g2d.setColor(CURRENT_COLOR);
            } else {
                g2d.setColor(DEFAULT_COLOR);
            }

            // Draw cell rectangle
            g2d.fillRect(startX + i * (CELL_WIDTH + PADDING),
                    startY, CELL_WIDTH, CELL_HEIGHT);

            // Draw cell border
            g2d.setColor(Color.BLACK);
            g2d.drawRect(startX + i * (CELL_WIDTH + PADDING),
                    startY, CELL_WIDTH, CELL_HEIGHT);

            // Draw text
            String text = data[i];
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textX = startX + i * (CELL_WIDTH + PADDING) +
                    (CELL_WIDTH - textWidth) / 2;
            int textY = startY + (CELL_HEIGHT + fm.getAscent() -
                    fm.getDescent()) / 2;
            g2d.drawString(text, textX, textY);
        }
    }

    public void setData(String[] data) {
        this.data = data;
        reset();
        repaint();
    }

    public void visualizeStep(List<Integer> visitedIndices, int current, int target) {
        this.currentIndex = current;
        this.targetIndex = target;
        repaint();
    }

    public void updateState(SearchState state) {
        this.currentIndex = state.getCurrentIndex();
        this.lowIndex = state.getLowIndex();
        this.midIndex = state.getMidIndex();
        this.highIndex = state.getHighIndex();
        repaint();
    }

    public void reset() {
        currentIndex = -1;
        targetIndex = -1;
        lowIndex = -1;
        midIndex = -1;
        highIndex = -1;
        visitedIndices = null;
        repaint();
    }

    private void drawMarker(Graphics2D g2d, String label, int index) {
        if (index >= 0 && index < data.length) {
            int x = 10 + index * (CELL_WIDTH + 5) + CELL_WIDTH / 2;
            g2d.drawString(label, x, 100);
        }
    }

    private void drawCenteredString(Graphics2D g2d, String text, int x, int y, int width, int height) {
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(text, textX, textY);
    }
}
