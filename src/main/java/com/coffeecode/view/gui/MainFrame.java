package com.coffeecode.view.gui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import com.coffeecode.controller.DictionaryController;
import com.coffeecode.model.SearchResult;

public class MainFrame extends JFrame {
    private final DictionaryController dictionaryController;
    private final SearchPanel searchPanel;
    private final VisualizerPanel visualizerPanel;
    private final ControlPanel controlPanel;
    private final Timer visualizationTimer;
    private SearchResult searchResult;
    private int currentStepIndex;

    public MainFrame(DictionaryController dictionaryController) {
        this.dictionaryController = dictionaryController;
        
        // Initialize components
        this.searchPanel = new SearchPanel();
        this.visualizerPanel = new VisualizerPanel();
        this.controlPanel = new ControlPanel();
        this.visualizationTimer = new Timer(500, e -> stepVisualization());
        
        setupFrame();
        setupEventHandlers();
    }

    private void setupFrame() {
        setTitle("Search Algorithm Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        add(searchPanel, BorderLayout.NORTH);
        add(visualizerPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }

    private void setupEventHandlers() {
        searchPanel.addPropertyChangeListener("search", this::handleSearch);
        controlPanel.addPropertyChangeListener("algorithm", e -> 
            dictionaryController.setSearchAlgorithm(
                "Binary Search".equals(e.getNewValue())
            ));
        controlPanel.addPropertyChangeListener("playing", this::handlePlayState);
        controlPanel.addPropertyChangeListener("step", e -> stepVisualization());
        controlPanel.addPropertyChangeListener("reset", e -> resetVisualization());
    }

    private void handleSearch(PropertyChangeEvent evt) {
        try {
            SearchRequest request = (SearchRequest) evt.getNewValue();
            String[] data = dictionaryController.getWords(request.isIndToEng);
            visualizerPanel.setData(data);
            
            searchResult = dictionaryController.search(request.searchTerm, request.isIndToEng);
            currentStepIndex = 0;
            updateVisualization();
            
        } catch (Exception e) {
            controlPanel.setStatus("Error: " + e.getMessage());
        }
    }

    private void handlePlayState(PropertyChangeEvent evt) {
        boolean isPlaying = (boolean) evt.getNewValue();
        if (isPlaying && searchResult != null) {
            visualizationTimer.start();
        } else {
            visualizationTimer.stop();
        }
    }

    private void stepVisualization() {
        if (searchResult == null || currentStepIndex >= searchResult.getSteps().size()) {
            visualizationTimer.stop();
            controlPanel.setStatus("Complete");
            return;
        }
        
        updateVisualization();
        currentStepIndex++;
    }

    private void updateVisualization() {
        if (searchResult != null) {
            visualizerPanel.visualizeStep(
                searchResult.getSteps().subList(0, currentStepIndex + 1),
                currentStepIndex,
                searchResult.isFound() ? searchResult.getFoundIndex() : -1
            );
        }
    }

    private void resetVisualization() {
        visualizationTimer.stop();
        currentStepIndex = 0;
        visualizerPanel.reset();
        controlPanel.setStatus("Ready");
    }
}