package com.shpp.p2p.cs.kturevich.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {
    ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();

	/* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while(true) {
                String line = reader.readLine();
                if (line == null)
                    break;

                this.entries.add(new NameSurferEntry(line));
            }
        } catch (IOException exception) {
            System.out.println("Problem while reading file!");
        }
    }
	
	/* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        for(NameSurferEntry entry : this.entries) {
            if (entry.getName().toLowerCase().equals(name))
                return entry;
        }
        return null;
    }
}

