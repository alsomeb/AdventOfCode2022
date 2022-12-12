package Day6;

import org.example.Day1.FileReader;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Day6Test {
    FileReader input = new FileReader();
    String inputActual = "src/test/java/Day6/input6actual.txt";

    @Test
    public void partOne() {
        String testLineInput = input.getAllLinesFromFileToList(inputActual).get(0);
        System.out.println(testLineInput);

        List<String> letters = new ArrayList<>(Arrays.asList(testLineInput.split("")));
        System.out.println(letters);

        int indexOfMarker;

        for (int i = 0; i < letters.size(); i++) {

            try {
                String l1 = letters.get(i);
                String l2 = letters.get(i + 1);
                String l3 = letters.get(i + 2);
                String l4 = letters.get(i + 3);
                List<String> currentStack = new ArrayList<>(Arrays.asList(l1, l2, l3, l4));

                final Set<String> setToReturn = new HashSet<>();
                final Set<String> set1 = new HashSet<>();

                for (String letter : currentStack) {
                    if (!set1.add(letter)) {
                        setToReturn.add(letter);
                    }

                    // om det inte finns några dubbletter o andra listan 4
                    if (setToReturn.isEmpty() && set1.size() == 4) {
                        System.out.println("HÄR: " + currentStack);
                        indexOfMarker = i + 4;
                        System.out.println("INDEX: " + indexOfMarker);
                        System.exit(0);
                    }
                    //System.out.println("set to return" + setToReturn);
                    //System.out.println("set1" + set1);
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("End of stack");
            }
        }
    }

    @Test
    public void partTwo() {
        String testLineInput = input.getAllLinesFromFileToList(inputActual).get(0);
        System.out.println(testLineInput);

        List<String> letters = new ArrayList<>(Arrays.asList(testLineInput.split("")));
        System.out.println(letters);

        int indexOfMarker;

        for (int i = 0; i < letters.size(); i++) {

            try {
                String l1 = letters.get(i);
                String l2 = letters.get(i + 1);
                String l3 = letters.get(i + 2);
                String l4 = letters.get(i + 3);
                String l5 = letters.get(i + 4);
                String l6 = letters.get(i + 5);
                String l7 = letters.get(i + 6);
                String l8 = letters.get(i + 7);
                String l9 = letters.get(i + 8);
                String l10 = letters.get(i + 9);
                String l11 = letters.get(i + 10);
                String l12 = letters.get(i + 11);
                String l13 = letters.get(i + 12);
                String l14 = letters.get(i + 13);

                List<String> currentStack = new ArrayList<>(Arrays.asList(
                        l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14));

                final Set<String> setToReturn = new HashSet<>();
                final Set<String> set1 = new HashSet<>();

                for (String letter : currentStack) {
                    if (!set1.add(letter)) {
                        setToReturn.add(letter);
                    }

                    // om det inte finns några dubbletter o andra listan 14 size
                    if (setToReturn.isEmpty() && set1.size() == 14) {
                        System.out.println("HÄR: " + currentStack);
                        indexOfMarker = i + 14;
                        System.out.println("INDEX: " + indexOfMarker);
                        System.exit(0);
                    }
                    //System.out.println("set to return" + setToReturn);
                    //System.out.println("set1" + set1);
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("End of stack");
            }
        }
    }
}