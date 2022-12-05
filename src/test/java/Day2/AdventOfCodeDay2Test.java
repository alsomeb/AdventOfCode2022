package Day2;
import org.example.Day2.InputReader;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AdventOfCodeDay2Test {
    /*
    The winner of the whole tournament is the player with the highest score.
    Your total score is the sum of your scores for each round.
    The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
    plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
     */


    final int ROCKSCORE = 1;
    final int PAPERSCORE = 2;
    final int SCISSORSCORE = 3;

    // 0 IF LOST ROUND
    final int DRAW = 3;
    final int WIN = 6;

    final String rockOpponent = "A";
    final String scisscorOpponent = "C";
    final String paperOpponent = "B";

    final String rockMine = "X";
    final String scisscorMine = "Z";
    final String paperMine = "Y";

    int totalScore = 0;

    InputReader inputReader = new InputReader();

    private int roundScore(String opponent, String me) {
        int roundScore = 0;

            // Draw
        if(me.equals(rockMine) && opponent.equals(rockOpponent)) {
            roundScore =  DRAW + ROCKSCORE;
        } else if (me.equals(scisscorMine) && opponent.equals(scisscorOpponent)) {
            roundScore = DRAW + SCISSORSCORE;
        } else if (me.equals(paperMine) && opponent.equals(paperOpponent)) {
            roundScore = DRAW + PAPERSCORE;

            //  Winning
        } else if (me.equals(rockMine) && opponent.equals(scisscorOpponent)) {
            roundScore = ROCKSCORE;
        } else if (me.equals(scisscorMine) && opponent.equals(paperOpponent)) {
            roundScore = WIN + SCISSORSCORE;
        } else if (me.equals(paperMine) && opponent.equals(rockOpponent)) {
            roundScore = DRAW + PAPERSCORE;
        }

            // Loosing
        else if (me.equals(scisscorMine) && opponent.equals(rockOpponent)) {
            roundScore = SCISSORSCORE;
        } else if (me.equals(paperMine) && opponent.equals(scisscorOpponent)) {
            roundScore = PAPERSCORE;
        } else if (me.equals(rockMine) && opponent.equals(paperOpponent)) {
            roundScore = ROCKSCORE;
        }


        return roundScore;
    }


    @Test
    void calcPointsTest() {
        List<String> formattedLines = inputReader.getAllLinesFromFileToList("src/test/java/Day2/inputTest2.txt");
        System.out.println(formattedLines);

        for (int i = 0; i < formattedLines.size(); i++) {
            String opponentShape = String.valueOf(formattedLines.get(i).charAt(0));
            String myShape = String.valueOf(formattedLines.get(i).charAt(1));

            System.out.println("Opponent: " + opponentShape);
            System.out.println("Mine: " + myShape);
            int points = roundScore(opponentShape, myShape);
            System.out.println("Round: " + points);
            totalScore += points;
            System.out.println(totalScore);
        }

        assertEquals(15, totalScore);
    }

    @Test
    void calcPointsActual() {

        List<String> formattedLines = inputReader.getAllLinesFromFileToList("src/test/java/Day2/input2actual.txt");
        System.out.println(formattedLines);

        for (int i = 0; i < formattedLines.size(); i++) {
            String opponentShape = String.valueOf(formattedLines.get(i).charAt(0));
            String myShape = String.valueOf(formattedLines.get(i).charAt(1));

            System.out.println("Opponent: " + opponentShape);
            System.out.println("Mine: " + myShape);
            int points = roundScore(opponentShape, myShape);
            System.out.println("Round: " + points);
            totalScore += points;
        }

        System.out.println(totalScore);
    }

    @Test
    void calcPointsActualPartOneBETTERVERSION(){
        int total = 0;
        List<String> formattedLines = inputReader.getAllLinesFromFileToList("src/test/java/Day2/input2actual.txt");
        System.out.println(formattedLines);

        // alla utfall o poäng, förbättrad version istället för if
        Map<String, Integer> outcomes = new HashMap<>(
                Map.of(
                        "AX", 4,
                        "AY", 8,
                        "AZ", 3,
                        "BX", 1,
                        "BY", 5,
                        "BZ", 9,
                        "CX", 7,
                        "CY", 2,
                        "CZ", 6
                ));

        for (int i = 0; i < formattedLines.size(); i++) {
            String currentShapes = formattedLines.get(i);
            total += outcomes.get(currentShapes);
        }

        System.out.println(total);
        assertEquals(12535, total);
    }

    @Test
    void calcPointsActualPart2() {
        int total = 0;
        List<String> formattedLines = inputReader.getAllLinesFromFileToList("src/test/java/Day2/input2actual.txt");
        System.out.println(formattedLines);

        // ANnan lösning med hashmap
        // DENNA ÄR NOG MKT SIMPLARE ISTÄLLET FÖR IF-satser ovan
        // Vi kan loopa igenom hela input filen och += total på nyckelns value i mapen
        Map<String, Integer> desiredOutcomes = new HashMap<>(
                Map.of(
                        "AX", 3,
                        "AY", 4,
                        "AZ", 8,
                        "BX", 1,
                        "BY", 5,
                        "BZ", 9,
                        "CX", 2,
                        "CY", 6,
                        "CZ",7
                ));

        for (String currentShapes : formattedLines) {
            //System.out.println(currentShapes);
            total += desiredOutcomes.get(currentShapes);
        }

        System.out.println(total);
        assertEquals(15457, total);


    }
}
