package com.shpp.p2p.cs.kturevich.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private NameSurferDataBase db = new NameSurferDataBase(NAMES_DATA_FILE);
    private NameSurferGraph graph;
    private JTextField nameField;
    private JButton graphButton, clearButton;


	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        graph = new NameSurferGraph();

        JLabel nameLabel = new JLabel("Name: ");
        add(nameLabel, NORTH);

        nameField = new JTextField(15);
        nameField.addActionListener(this);
        add(nameField, NORTH);

        graphButton = new JButton("Graph");
        clearButton = new JButton("Clear");
        add(graphButton, NORTH);
        add(clearButton, NORTH);

        add(graph, CENTER);
        addActionListeners();
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nameField || e.getSource() == graphButton) {
            try {
                graph.addEntry(db.findEntry(nameField.getText().toLowerCase()));
            } catch (Exception ex) {
                System.out.println("WRONG NAME");
            }
        }
        if (e.getSource() == clearButton) {
            graph.clear();
        }

    }
}
