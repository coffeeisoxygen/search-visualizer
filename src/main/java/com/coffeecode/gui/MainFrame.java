package com.coffeecode.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SearchPanel searchPanel;
    private VisualizerPanel visualizerPanel;
    private ControlPanel controlPanel;

    public MainFrame() {
        setTitle("Search Algorithm Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        searchPanel = new SearchPanel();
        visualizerPanel = new VisualizerPanel();
        controlPanel = new ControlPanel();

        add(searchPanel, BorderLayout.NORTH);
        add(visualizerPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
}