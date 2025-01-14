package com.coffeecode.view.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
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
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        initComponents();
        setupListeners();
    }

    private void initComponents() {
        algorithmSelector = new JComboBox<>(new String[] { "Linear Search", "Binary Search" });
        playPauseButton = new JButton("Play");
        stepButton = new JButton("Step");
        resetButton = new JButton("Reset");

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setPaintTicks(true);

        statusLabel = new JLabel("Ready");

        add(new JLabel("Algorithm:"));
        add(algorithmSelector);
        add(playPauseButton);
        add(stepButton);
        add(resetButton);
        add(new JLabel("Speed:"));
        add(speedSlider);
        add(statusLabel);
    }

    private void setupListeners() {
        playPauseButton.addActionListener(e -> togglePlayPause());
        stepButton.addActionListener(e -> firePropertyChange("step", false, true));
        resetButton.addActionListener(e -> firePropertyChange("reset", false, true));
        algorithmSelector
                .addActionListener(e -> firePropertyChange("algorithm", null, algorithmSelector.getSelectedItem()));
    }

    private void togglePlayPause() {
        isPlaying = !isPlaying;
        playPauseButton.setText(isPlaying ? "Pause" : "Play");
        stepButton.setEnabled(!isPlaying);
        firePropertyChange("playing", !isPlaying, isPlaying);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public int getSpeed() {
        return speedSlider.getValue();
    }

    public String getSelectedAlgorithm() {
        return (String) algorithmSelector.getSelectedItem();
    }
}
