package org.example.Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    private List<String> allLines = new ArrayList<>();

    public List<String> getAllLinesFromFileToList(String path) {
        String currentLine = "";

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(path));

            while ((currentLine = reader.readLine()) != null) {
                String formatedLine = currentLine.replaceAll(" ", "").trim();
                allLines.add(formatedLine);
            }

        } catch (IOException e) {
            System.err.println("I/O Error reading file");
            e.printStackTrace();
        }
        return allLines;
    }
}
