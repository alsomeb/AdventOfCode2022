package Day4;

import org.example.Day1.FileReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4Test {
    FileReader input = new FileReader();
    String inputActual = "src/test/java/Day4/input4Actual.txt";
    String inputTest = "src/test/java/Day4/input4Test.txt";

    List<String> lines =  input.getAllLinesFromFileToList(inputActual);

    @Test
    public void adventOfCodePart4Test() {
        int total = 0;

        for(String line : lines) {
            String[] elfPairs = line.split(",");
            System.out.println(Arrays.toString(elfPairs));
            int startFirstElf = Integer.parseInt(elfPairs[0].split("-")[0]);
            int endFirstElf = Integer.parseInt(elfPairs[0].split("-")[1]);
            int startSecondElf = Integer.parseInt(elfPairs[1].split("-")[0]);
            int endSecondElf = Integer.parseInt(elfPairs[1].split("-")[1]);


            // Åt båda hållen, måste va if och else-if, annars blir dem identiska
            if (startFirstElf <= startSecondElf && endFirstElf >= endSecondElf) {
                total++;
            } else if (startFirstElf >= startSecondElf && endFirstElf <= endSecondElf) {
                total++;
            }

        }
        System.out.println("Total Pairs range contain the other: " + total);
        //assertEquals(2, total);
    }

    @Test
    public void adventOfCodePart4TestDel2() {
        int total = 0;

        for(String line : lines) {
            String[] elfPairs = line.split(",");
            System.out.println(Arrays.toString(elfPairs));
            int startFirstElf = Integer.parseInt(elfPairs[0].split("-")[0]);
            int endFirstElf = Integer.parseInt(elfPairs[0].split("-")[1]);
            int startSecondElf = Integer.parseInt(elfPairs[1].split("-")[0]);
            int endSecondElf = Integer.parseInt(elfPairs[1].split("-")[1]);

            int[] firstElfArray = new int[endFirstElf - startFirstElf + 1];
            int[] secondElfArray = new int[endSecondElf - startSecondElf + 1];

            for (int i = 0; i < firstElfArray.length; i++) {
                firstElfArray[i] = startFirstElf;
                startFirstElf++;
            }

            for (int i = 0; i < secondElfArray.length; i++) {
                secondElfArray[i] = startSecondElf;
                startSecondElf++;
            }

            // a Stream consistent of the elements of this stream, each boxed to an Integer
            // Jag kanske skulle ha gjort Listor från början men det blev såhär med Arrays pga de va enklare att plussa på siffror ovan
            // och få hela range för firstElfArray och secondElfArray
            List<Integer> first = Arrays.stream(firstElfArray).boxed().collect(Collectors.toList());
            List<Integer> second = Arrays.stream(secondElfArray).boxed().collect(Collectors.toList());

            System.out.println(first);
            System.out.println(second);

            boolean secondListContainsAnyNumberFromFirst = first.stream().anyMatch(s -> second.contains(s));
            boolean firstListContainsAnyNumberFromSecond = second.stream().anyMatch(s -> first.contains(s));

            if(secondListContainsAnyNumberFromFirst) {
                total++;
            } else if (firstListContainsAnyNumberFromSecond) {
                total++;
            }


        }
        System.out.println("Total: " + total);
        //assertEquals(4, total);
    }
}
