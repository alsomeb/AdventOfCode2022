package Day1;

import org.example.Day1.FileReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

/*
    This list represents the Calories of the food carried by five Elves:

        The first Elf is carrying food with 1000, 2000, and 3000 Calories, a total of 6000 Calories.
        The second Elf is carrying one food item with 4000 Calories.
        The third Elf is carrying food with 5000 and 6000 Calories, a total of 11000 Calories.
        The fourth Elf is carrying food with 7000, 8000, and 9000 Calories, a total of 24000 Calories.
        The fifth Elf is carrying one food item with 10000 Calories.
        In case the Elves get hungry and need extra snacks, they need to know which Elf to ask: they'd like to know how many Calories are being carried by the Elf carrying the most Calories. In the example above, this is 24000 (carried by the fourth Elf).

        Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?

 */

class AdventOfCodeDay1Test {
    FileReader reader = new FileReader();
    private final Map<Integer, Integer> elfTotalCaloriesTest = new HashMap<>();
    String pathTest = "src/test/java/Day1/inputTest.txt";
    List<String> allLinesTest = reader.getAllLinesFromFileToList(pathTest);

    @Test
    void mostCarryingElfNumberTest() {
        System.out.println("Input");
        System.out.println(allLinesTest);

        int totalCalories = 0;
        int elfNumber = 1;
        for (int i = 0; i < allLinesTest.size(); i++) {
            if(allLinesTest.get(i).isBlank()) {
                elfTotalCaloriesTest.put(elfNumber, totalCalories);
                elfNumber++;
                totalCalories = 0; // reset total Calories for next count
            } else {
                totalCalories += Integer.parseInt(allLinesTest.get(i));
            }
        }

        System.out.println("MAP:");
        System.out.println(elfTotalCaloriesTest);

        int key = -1;
        int value = -1;
        int maxCalorieValue = Collections.max(elfTotalCaloriesTest.values());
        Optional<Map.Entry<Integer, Integer>> found = elfTotalCaloriesTest.entrySet().stream().filter(entry -> entry.getValue().equals(maxCalorieValue)).findAny();
        if(found.isPresent()) {
            key = found.get().getKey();
            value = found.get().getValue();
            System.out.println("Elf Nr: " + key);
            System.out.println("Elf Carrying: " + value);
        }

        assertEquals(4, key);
        assertEquals(24000, value);
    }

    // Find the Top Three Elfs
    @Test
    void mostCarryingElfNumberPartTwo() {
        Map<Integer, Integer> elfTotalCalories = new HashMap<>();
        String path = "src/main/resources/input1.txt";
        List<String> allLines = reader.getAllLinesFromFileToList(path);

        int totalCalories = 0;
        int elfNumber = 1;
        for (int i = 0; i < allLines.size(); i++) {
            if(allLines.get(i).isBlank()) {
                elfTotalCalories.put(elfNumber, totalCalories);
                elfNumber++;
                totalCalories = 0; // reset total Calories for next count
            } else {
                totalCalories += Integer.parseInt(allLines.get(i));
            }
        }

        System.out.println("MAP:");
        System.out.println(elfTotalCalories);

        // ---- TOP 3 Calories, Vi kör loopen 3 gånger, appendar in biggestNr i topThreeElfsCaloriesList, sedan tar bort Entry i Map så vi kan hitta nästa största!
        int count = 0;
        List<Integer> topThreeElfsCaloriesList = new ArrayList<>();

        while (count < 3) {
            int currentBiggest = Collections.max(elfTotalCalories.values());
            topThreeElfsCaloriesList.add(currentBiggest);
            Optional<Map.Entry<Integer, Integer>> found = elfTotalCalories.entrySet().stream().filter(entry -> entry.getValue().equals(currentBiggest)).findFirst();

            if(found.isPresent()) {
                int key = found.get().getKey();
                elfTotalCalories.remove(key);
            }
            count++;
        }

        System.out.println(topThreeElfsCaloriesList);
        int total = topThreeElfsCaloriesList.stream().collect(Collectors.summingInt(nr -> nr.intValue()));
        System.out.println("total kcal: " + total);
        assertEquals(215594, total);

    }

    // Actual!
    @Test
    void mostCarryingElfNumberActualPartOne() {
        Map<Integer, Integer> elfTotalCalories = new HashMap<>();
        String path = "src/main/resources/input1.txt";
        List<String> allLines = reader.getAllLinesFromFileToList(path);
        System.out.println(allLines.size());

        int totalCalories = 0;
        int elfNumber = 1;
        for (int i = 0; i < allLines.size(); i++) {
            if(allLines.get(i).isBlank()) {
                elfTotalCalories.put(elfNumber, totalCalories);
                elfNumber++;
                totalCalories = 0; // reset total Calories for next count
            } else {
                totalCalories += Integer.parseInt(allLines.get(i));
            }
        }

        System.out.println("MAP:");
        System.out.println(elfTotalCalories);

        int key;
        int value;
        int maxCalorieValue = Collections.max(elfTotalCalories.values());
        Optional<Map.Entry<Integer, Integer>> found = elfTotalCalories.entrySet().stream().filter(entry -> entry.getValue().equals(maxCalorieValue)).findFirst();
        if(found.isPresent()) {
            key = found.get().getKey();
            value = found.get().getValue();
            System.out.println("Elf Nr: " + key);
            System.out.println("Elf Carrying KCAL: " + value);
        }

    }

}