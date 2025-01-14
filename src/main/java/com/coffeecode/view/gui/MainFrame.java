package com.coffeecode.view.gui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.coffeecode.controller.DictionaryController;
import com.coffeecode.model.SearchResult;

public class MainFrame extends JFrame {
    private final DictionaryController dictionaryController;
    private SearchPanel searchPanel;
    private VisualizerPanel visualizerPanel;
    private ControlPanel controlPanel;
    private Timer visualizationTimer;
    private SearchResult searchResult;
    private int currentStepIndex;

    public MainFrame(DictionaryController dictionaryController) {
        this.dictionaryController = dictionaryController;
        setTitle("Search Algorithm Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        initComponents();
        setupEventHandlers();
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

        visualizationTimer = new Timer(500, e -> stepVisualization());
    }

    private void setupEventHandlers() {
        searchPanel.addPropertyChangeListener("search", this::handleSearch);
        controlPanel.addPropertyChangeListener("playState", this::handlePlayState);
        controlPanel.addPropertyChangeListener("step", e -> stepVisualization());
        controlPanel.addPropertyChangeListener("reset", e -> resetVisualization());
    }

    private void handleSearch(PropertyChangeEvent evt) {
        SearchRequest request = (SearchRequest) evt.getNewValue();
        searchResult = dictionaryController.search(
            request.searchTerm, 
            request.isIndToEng
        );
        
        currentStepIndex = 0;
        visualizerPanel.setData(
            dictionaryController.getWords(request.isIndToEng)
        );
        updateVisualization();
    }

    private void handlePlayState(PropertyChangeEvent evt) {
        boolean isPlaying = (boolean) evt.getNewValue();
        if (isPlaying) {
            visualizationTimer.start();
        } else {
            visualizationTimer.stop();
        }
    }

    private void stepVisualization() {
        if (searchResult == null ||
                currentStepIndex >= searchResult.getSteps().size()) {
            visualizationTimer.stop();
            return;
        }

        updateVisualization();
        currentStepIndex++;
    }

    private void updateVisualization() {
        visualizerPanel.visualizeStep(
                searchResult.getSteps().subList(0, currentStepIndex + 1),
                currentStepIndex,
                searchResult.isFound() ? searchResult.getFoundIndex() : -1);
    }

    private void resetVisualization() {
        currentStepIndex = 0;
        if (searchResult != null) {
            updateVisualization();
        }
    }
}