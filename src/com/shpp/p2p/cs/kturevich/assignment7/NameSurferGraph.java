package com.shpp.p2p.cs.kturevich.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    ArrayList<NameSurferEntry> entries = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        this.entries.clear();
        this.update();
    }

	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (entry == null || this.entries.contains(entry))
            return;
        this.entries.add(entry);
        this.update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawField();
        drawEntries();
    }

    private void drawEntries(){
        Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

        for(int i = 0; i < this.entries.size(); i++) {
            Color color = colors[i % colors.length];
            drawEntry(this.entries.get(i), color);
        }
    }

    private void drawEntry(NameSurferEntry entry, Color color) {
        int step = getWidth() / NDECADES;

        double previousX = 0;
        double previousY = entry.getRank(0);

        for(int i = 0; i < NDECADES; i++) {
            double x = i * step;
            double y = normalizeY(entry.getRank(i));

            add(createLine(previousX, previousY, x, y, color));
            add(createLabel(getLabelText(entry, i), x, y, color));

            previousX = x;
            previousY = y;
        }

        add(createLabel(getLabelText(entry, NDECADES - 1), previousX, previousY, color));
    }

    private double normalizeY(int rank) {
        double scale = rank / (double)MAX_RANK;

        if(rank == 0) {
            return getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            return GRAPH_MARGIN_SIZE + (scale * (getHeight() - GRAPH_MARGIN_SIZE * 2));
        }
    }

    private String getLabelText(NameSurferEntry entry, int rank) {
        String result = "";
        result = entry.getName() + " ";

        if (entry.getRank(rank) == 0) {
            result += "*";
        } else {
            result += entry.getRank(rank);
        }

        return result;
    }

    private void drawField() {
        int step = getWidth() / NDECADES;
        int bottomMargin = getHeight() - GRAPH_MARGIN_SIZE;

        int x = 0;
        int year = START_DECADE;

        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(x, 0, x, getHeight()));
            add(new GLabel(year + "", x, getHeight()));

            year += 10;
            x += step;
        }

        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, bottomMargin, getWidth(), bottomMargin));
    }

    private GLine createLine(double x1, double y1, double x2, double y2, Color color) {
        GLine line = new GLine(x1, y1, x2, y2);
        line.setColor(color);
        return line;
    }

    private GLabel createLabel(String text, double x, double y, Color color){
        GLabel label = new GLabel(text, x, y);
        label.setColor(color);
        return label;
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
