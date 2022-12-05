package Day3;
import org.example.Day1.FileReader;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Day3Test {
    /*
    o help prioritize item rearrangement, every item type can be converted to a priority:

    Lowercase item types a through z have priorities 1 through 26.
    Uppercase item types A through Z have priorities 27 through 52.
    In the above example, the priority of the item type that appears in both compartments of each rucksack is 16 (p), 38 (L), 42 (P), 22 (v), 20 (t), and 19 (s); the sum of these is 157.

    Find the item type that appears in both compartments of each rucksack. What is the sum of the priorities of those item types?
     */

    FileReader input = new FileReader();
    String inputActual = "src/test/java/Day3/input3actual.txt";
    String inputTest = "src/test/java/Day3/input3Test.txt";

    List<String> lines =  input.getAllLinesFromFileToList(inputActual);

    // Start with 0 to get the right score, pga indexering, vi har ju enligt spec 1-26 lowerCase, 27-52 upperCase
    String alphabet = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Test
    void partOne() {
        int score = 0;

        for(String line : lines) {
            int[] chars = new int[line.length()];

            int i = 0;
            // Every character
            for(String e : line.split("")) {
                chars[i] = alphabet.indexOf(e);
                i++;
            }

            // For every line
            int[] first = Arrays.copyOfRange(chars, 0, chars.length / 2);
            int[] second = Arrays.copyOfRange(chars, chars.length / 2, chars.length);
            System.out.println(Arrays.toString(first));
            System.out.println(Arrays.toString(second));

            // Double loop, first array and second array
            // Vi vill hitta delade bokstäver
            // SET PGA DE BLIR DUBLETTER, SET ÄR UNIK VÄRDEN BARA

            Set<Integer> scores = new HashSet<>();
            for (int k = 0; k < first.length; k++) {
                for (int j = 0; j < second.length; j++) {
                    if(first[k] == second[j]) {
                        scores.add(first[k]); // pga dem har samma
                    }
                }
            }
            System.out.println(scores);
            // https://www.baeldung.com/java-stream-sum
            // Denna returnerar en int, första gången 16, sedan 38... osv. som plussas på score
            score += scores.stream().reduce(0, (a, b) -> a + b);
        }
        System.out.println("Total: " + score);
    }


    @Test
    void partTwo() {
        int totalScore = 0;
        Map<Integer, List<String>> groups = new HashMap<>();
        List<String> currentGroup = new ArrayList<>();

        int cutOff = 3;
        int currentGroupNr = 1;
        int counter = 0;

        // Sortera in grupper
        for(String line : lines) {
            currentGroup.add(line);
            counter++;

            if(counter == cutOff) {
                groups.put(currentGroupNr, new ArrayList<>(currentGroup));
                currentGroup.clear(); // måste va new ArrayList för den pekar på samma referens
                counter = 0;
                currentGroupNr++;
            }
        }


        int hashSetId = 0;
        List<Set<Integer>> listOfSets = new ArrayList<>();
        listOfSets.add(new HashSet<>());
        listOfSets.add(new HashSet<>());
        listOfSets.add(new HashSet<>());

        // Hitta gemensamma bokstäver
        for(List<String> group : groups.values()) {
            System.out.println(group);

            for (String sack : group) {

                // Every character
                for(String e : sack.split("")) {
                    listOfSets.get(hashSetId).add(alphabet.indexOf(e));
                }
                hashSetId++; // plussar på för att få alla Sets
            }

            // hämtar den som är unik för alla listor, dvs den siffran som finns i alla 3 Sets
            listOfSets.get(0).retainAll(listOfSets.get(1));
            listOfSets.get(0).retainAll(listOfSets.get(2));

            // Plussar på totalen
            totalScore += listOfSets.get(0).stream().reduce((a, b) -> a + b).get();
            System.out.println(listOfSets);
            System.out.println("Gemensam: " + listOfSets.get(0));

            // resettar ID och skapar ny arrayList samt nya HashSets för nästa omgång
            listOfSets = new ArrayList<>();
            listOfSets.add(new HashSet<>());
            listOfSets.add(new HashSet<>());
            listOfSets.add(new HashSet<>());
            hashSetId = 0; // resettar när vi har en lista med 3 HashSets
        }

        System.out.println("sum of the priorities: " + totalScore);
    }

}
