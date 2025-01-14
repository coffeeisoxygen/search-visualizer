package com.coffeecode.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControlPanel extends JPanel {
    private JComboBox<String> algorithmSelector;
    private JButton playPauseButton;
    private JButton stepButton;
    private JButton resetButton;
    private JSlider speedSlider;
    private JLabel statusLabel;
    private boolean isPlaying;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        initComponents();
    }

    private void initComponents() {
        // Algorithm selector
        algorithmSelector = new JComboBox<>(new String[] { "Linear Search", "Binary Search" });

        // Control buttons
        playPauseButton = new JButton("Play");
        stepButton = new JButton("Step");
        resetButton = new JButton("Reset");

        // Speed control
        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);

        // Status label
        statusLabel = new JLabel("Ready");

        // Add components
        add(new JLabel("Algorithm:"));
        add(algorithmSelector);
        add(playPauseButton);
        add(stepButton);
        add(resetButton);
        add(new JLabel("Speed:"));
        add(speedSlider);
        add(statusLabel);

        setupListeners();
    }

    private void setupListeners() {
        playPauseButton.addActionListener(e -> togglePlayPause());
        stepButton.addActionListener(e -> step());
        resetButton.addActionListener(e -> reset());
    }

    private void togglePlayPause() {
        isPlaying = !isPlaying;
        playPauseButton.setText(isPlaying ? "Pause" : "Play");
        stepButton.setEnabled(!isPlaying);
        firePropertyChange("playState", !isPlaying, isPlaying);
    }

    private void step() {
        firePropertyChange("step", false, true);
    }

    private void reset() {
        isPlaying = false;
        playPauseButton.setText("Play");
        stepButton.setEnabled(true);
        firePropertyChange("reset", false, true);
    }

    public String getSelectedAlgorithm() {
        return (String) algorithmSelector.getSelectedItem();
    }

    public int getSpeed() {
        return speedSlider.getValue();
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }
}
