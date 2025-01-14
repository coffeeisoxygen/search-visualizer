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
    private static final int CELL_WIDTH = 60;
    private static final int CELL_HEIGHT = 40;
    private static final int CELL_SPACING = 5;
    private static final Color CURRENT_COLOR = new Color(255, 255, 0);
    private static final Color VISITED_COLOR = new Color(200, 200, 200);
    private static final Color FOUND_COLOR = new Color(0, 255, 0);

    private String[] data;
    private List<Integer> visitedIndices;
    private int currentIndex = -1;
    private int foundIndex = -1;
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
        if (data == null)
            return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawArray(g2d);
        drawMarkers(g2d);
    }

    private void drawArray(Graphics2D g2d) {
        int x = 10;
        int y = 50;

        for (int i = 0; i < data.length; i++) {
            // Cell background
            Color bgColor = getCellColor(i);
            g2d.setColor(bgColor);
            g2d.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);

            // Cell border
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, CELL_WIDTH, CELL_HEIGHT);

            // Cell text
            drawCenteredString(g2d, data[i], x, y, CELL_WIDTH, CELL_HEIGHT);

            x += CELL_WIDTH + CELL_SPACING;
        }
    }

    private Color getCellColor(int index) {
        if (index == foundIndex)
            return FOUND_COLOR;
        if (index == currentIndex)
            return CURRENT_COLOR;
        if (visitedIndices != null && visitedIndices.contains(index))
            return VISITED_COLOR;
        return Color.WHITE;
    }

    private void drawMarkers(Graphics2D g2d) {
        if (lowIndex >= 0)
            drawMarker(g2d, "L", lowIndex);
        if (midIndex >= 0)
            drawMarker(g2d, "M", midIndex);
        if (highIndex >= 0)
            drawMarker(g2d, "H", highIndex);
    }

    public void updateState(SearchState state) {
        this.currentIndex = state.getCurrentIndex();
        this.lowIndex = state.getLowIndex();
        this.midIndex = state.getMidIndex();
        this.highIndex = state.getHighIndex();
        repaint();
    }

    public void setData(String[] data) {
        this.data = data;
        reset();
        repaint();
    }

    public void reset() {
        currentIndex = -1;
        foundIndex = -1;
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
