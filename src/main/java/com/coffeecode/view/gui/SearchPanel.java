package com.coffeecode.view.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {
    private JTextField searchField;
    private JComboBox<String> languageSelector;
    private JButton searchButton;
    private JLabel resultLabel;

    public SearchPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Search input
        searchField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(new JLabel("Search:"), gbc);

        gbc.gridx = 1;
        add(searchField, gbc);

        // Language selector
        languageSelector = new JComboBox<>(new String[] { "ID -> EN", "EN -> ID" });
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        add(languageSelector, gbc);

        // Search button
        searchButton = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(searchButton, gbc);

        // Result label
        resultLabel = new JLabel("Enter word to search");
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(resultLabel, gbc);

        setupListeners();
    }

    private void setupListeners() {
        searchButton.addActionListener(e -> performSearch());
    }

    private void performSearch() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            resultLabel.setText("Please enter a word");
            return;
        }

        boolean isIndToEng = languageSelector.getSelectedIndex() == 0;
        firePropertyChange("search", null,
                new SearchRequest(searchText, isIndToEng));
    }

    public void setResult(String result) {
        resultLabel.setText(result);
    }
}

class SearchRequest {
    final String searchTerm;
    final boolean isIndToEng;

    SearchRequest(String searchTerm, boolean isIndToEng) {
        this.searchTerm = searchTerm;
        this.isIndToEng = isIndToEng;
    }
}
