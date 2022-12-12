package Day5;

import org.example.Day1.FileReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5Test {
    FileReader input = new FileReader();
    String inputActual = "src/test/java/Day5/input5actual.txt";
    String inputTest = "src/test/java/Day5/input5test.txt";

    List<String> lines =  input.getAllLinesFromFileToList(inputActual);


    // regex match any DIGIT and SPECIFIC WORD "move"
    // "\d" matches a digit (equivalent to [0-9])
    // "+" matches the previous token between one and unlimited times, as many times as possible, giving back as needed (greedy)
    final String REGEX_MOVE = "move \\d+";

    @Test
    void partFivePartOneTest() {
        int amount;
        int fromIndex;
        int targetIndex;

        List<List<String>> containers = new ArrayList<>(
                List.of(
                        new ArrayList<>(Arrays.asList("P", "V", "Z", "W", "D", "T")),
                        new ArrayList<>(Arrays.asList("D", "J", "F", "V", "W", "S", "L")),
                        new ArrayList<>(Arrays.asList("H", "B", "T", "V", "S", "L", "M", "Z")),
                        new ArrayList<>(Arrays.asList("J", "S", "R")),
                        new ArrayList<>(Arrays.asList("W", "L", "M", "F", "G", "B", "Z", "C")),
                        new ArrayList<>(Arrays.asList("B","G","R","Z","H","V","W","Q")),
                        new ArrayList<>(Arrays.asList("N", "D", "B", "C", "P", "J", "V")),
                        new ArrayList<>(Arrays.asList("Q","B","T","P")),
                        new ArrayList<>(Arrays.asList("C","R","Z","G","H"))
                )
        );



        for (String line : lines) {
            // Regex
            Pattern pattern = Pattern.compile(REGEX_MOVE, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);

            boolean matchFound = matcher.find();
            if(matchFound) {
                // Ta fram varje Instruction per line
                String[] wordsInCurrentLine = line.split(" ");
                amount = Integer.parseInt(wordsInCurrentLine[1]);
                fromIndex = Integer.parseInt(wordsInCurrentLine[3]);
                targetIndex = Integer.parseInt(wordsInCurrentLine[5]);


                // Görs så många gånger som amount säger
                for(int i = 0; i < amount; i++){
                    int fromIndexFormatted = fromIndex- 1;
                    int targetIndexFormatted = targetIndex - 1;

                    String elementToBeMoved = containers.get(fromIndexFormatted).get(0); // pga den på toppen skall alltid flyttas
                    containers.get(fromIndexFormatted).remove(0);

                    // lägg till elementet på targetIndex
                    // Inserts the specified element at the specified position in this list.
                    // Shifts the element currently at that position and any subsequent elements to the right (adds one to their indices).
                    containers.get(targetIndexFormatted).add(0, elementToBeMoved);
                }

            }

        }
        // Hämtar varje letter som ligger först i varje lista (letterList) och concatenate till en sträng
        // "Returns a Collector that concatenates the input elements into a String, in encounter order."
        String firstLetterOfEveryListConcat = containers.stream().map(letterList -> letterList.get(0)).collect(Collectors.joining());
        System.out.println(firstLetterOfEveryListConcat);
        assertEquals("TLFGBZHCN", firstLetterOfEveryListConcat);
    }

    @Test
    void part2() {
        int amount;
        int fromIndex;
        int targetIndex;

        List<List<String>> containers = new ArrayList<>(
                List.of(
                        new ArrayList<>(Arrays.asList("P", "V", "Z", "W", "D", "T")),
                        new ArrayList<>(Arrays.asList("D", "J", "F", "V", "W", "S", "L")),
                        new ArrayList<>(Arrays.asList("H", "B", "T", "V", "S", "L", "M", "Z")),
                        new ArrayList<>(Arrays.asList("J", "S", "R")),
                        new ArrayList<>(Arrays.asList("W", "L", "M", "F", "G", "B", "Z", "C")),
                        new ArrayList<>(Arrays.asList("B","G","R","Z","H","V","W","Q")),
                        new ArrayList<>(Arrays.asList("N", "D", "B", "C", "P", "J", "V")),
                        new ArrayList<>(Arrays.asList("Q","B","T","P")),
                        new ArrayList<>(Arrays.asList("C","R","Z","G","H"))
                )
        );

        for (String line : lines) {
            // Regex
            // Lägger olika grupper, dessa markeras genom paranteser i regex string
            Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            Matcher matcher = pattern.matcher(line);

            boolean matchFound = matcher.find();
            if(matchFound) {

                // Hämtar värden från regex grupperna (DEN BÖRJAR EJ FRÅN 0, FUNKAR SAMMA SOM INDEX),
                // vi behöver ej split och ta från index
                amount = Integer.parseInt(matcher.group(1));
                fromIndex = Integer.parseInt(matcher.group(2));
                targetIndex = Integer.parseInt(matcher.group(3));

                // Så indexering blir korrekt
                int fromIndexFormatted = fromIndex - 1;
                int targetIndexFormatted = targetIndex - 1;

                List<String> currentStack = containers.get(fromIndexFormatted);

                // Funkar för både chunks och 1, fångar upp alla eventualiteter
                List<String> chunk = currentStack
                        .stream()
                        .limit(amount)
                        .collect(Collectors.toList());

                // Ta bort dem element vi plockade ut
                for(int i = 0; i < amount; i++) {
                    containers.get(fromIndexFormatted).remove(0);
                }


                System.out.println(line);
                // chunk skall append in till target index
                System.out.println("HÄMTAD: " + chunk);
                containers.get(targetIndexFormatted).addAll(0, chunk);
                System.out.println("CONTAINER: " + containers.get(targetIndexFormatted));
            }

        }

        String firstLetterOfEveryListConcat = containers.stream().map(letterList -> letterList.get(0)).collect(Collectors.joining());
        System.out.println(firstLetterOfEveryListConcat);

    }

}

