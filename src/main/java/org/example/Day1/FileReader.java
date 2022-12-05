package org.example.Day1;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;



public class FileReader {
    private List<String> lines;

    public FileReader() {
    }

    public List<String> getAllLinesFromFileToList(String path) {
        lines = new ArrayList<>();
        String currentLine = "";

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(path));

            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine.trim());
            }

        } catch (IOException e) {
            System.err.println("I/O Error reading file");
            e.printStackTrace();
        }
        return lines;
    }
}
