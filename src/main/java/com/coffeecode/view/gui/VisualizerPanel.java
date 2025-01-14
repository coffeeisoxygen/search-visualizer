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
    private String[] data;
    private List<Integer> steps;
    private int currentStep;
    private int foundIndex;
    private SearchState searchState;

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
        if (data == null) return;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawArray(g2d);
        if (searchState != null && searchState.isBinarySearch()) {
            drawBinarySearchMarkers(g2d);
        }
    }

    private void drawArray(Graphics2D g2d) {
        int x = 10;
        int y = 50;
        
        for (int i = 0; i < data.length; i++) {
            // Draw cell background
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
            drawCenteredString(g2d, data[i], x, y, CELL_WIDTH, CELL_HEIGHT);
            
            x += CELL_WIDTH + 5;
        }
    }

    private void drawBinarySearchMarkers(Graphics2D g2d) {
        int baseY = 100;
        g2d.setColor(Color.BLUE);
        
        // Draw Low marker
        drawMarker(g2d, "L", searchState.getLowIndex(), baseY);
        
        // Draw Mid marker
        drawMarker(g2d, "M", searchState.getMidIndex(), baseY);
        
        // Draw High marker
        drawMarker(g2d, "H", searchState.getHighIndex(), baseY);
    }

    private void drawMarker(Graphics2D g2d, String label, int index, int baseY) {
        if (index >= 0 && index < data.length) {
            int x = 10 + index * (CELL_WIDTH + 5) + CELL_WIDTH/2;
            g2d.drawString(label, x, baseY);
        }
    }

    private void drawCenteredString(Graphics2D g2d, String text, int x, int y, int width, int height) {
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(text, textX, textY);
    }
}
