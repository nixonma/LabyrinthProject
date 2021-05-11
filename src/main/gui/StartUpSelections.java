package main.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpSelections {

    String labyrinthType;
    int scale;

    public StartUpSelections() {
        JFrame optionSelect = new JFrame();
        JComboBox<String> algorithmBox = new JComboBox<>();
        algorithmBox.addItem("Recursive Backtrack");
        algorithmBox.addItem("Iterative Backtrack");

        JSlider scaleSlider = new JSlider(1, 8, 1);
        scaleSlider.setMajorTickSpacing(1);
        scaleSlider.setMinorTickSpacing(1);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setPaintLabels(true);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinthType = (String) algorithmBox.getSelectedItem();
                scale = scaleSlider.getValue();
                optionSelect.setVisible(false);
                Main.run();
            }
        });
        optionSelect.add(algorithmBox);
        optionSelect.add(scaleSlider);
        optionSelect.add(new JLabel(""));
        optionSelect.setVisible(true);
    }

    public String getLabyrinthType() {
        return labyrinthType;
    }

    public int getScale() {
        return scale;
    }
}
