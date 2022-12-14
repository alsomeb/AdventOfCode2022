package Day7;

import org.example.Day1.FileReader;
import org.junit.jupiter.api.Test;

import java.util.*;


public class Day7Test {

    FileReader input = new FileReader();
    String inputActual = "src/test/java/Day7/input7actual.txt";
    String inputTest = "src/test/java/Day7/input7test.txt";
    List<String> lines = input.getAllLinesFromFileToList(inputActual);


    @Test
    void partOne() {
        List<String> currentDir = new ArrayList<>();
        Map<String, Integer> dirSize = new HashMap<>();

        for (String line : lines) {
            if(line.charAt(0) == '$'){
                String[] command = line.split(" ");

                if(command[1].equals("cd")) {
                    if (command[2].equals("..")) {
                        currentDir.remove(currentDir.size() - 1);
                    } else {
                        currentDir.add(command[2]);
                    }
                }
            } else if (!(line.startsWith("dir"))) {
                String[] file = line.split(" ");
                int fileSize = Integer.parseInt(file[0]);
                System.out.println(currentDir);
                String fileDir = String.join("/", currentDir).replace("//", "/");
                dirSize.put(fileDir, dirSize.getOrDefault(fileDir, 0) + fileSize);

                while (fileDir.contains("/")) {
                    fileDir = fileDir.substring(0, fileDir.lastIndexOf("/"));
                    dirSize.put(fileDir, dirSize.getOrDefault(fileDir, 0) + fileSize);
                }
            }
        }

        System.out.println(dirSize);

        int totalSize = 0;
        for(Integer size : dirSize.values()){
            if(size <= 100000){
                totalSize += size;
            }
        }

        System.out.println("Total size: " + totalSize);
    }


    @Test
    void part2() {
        List<String> currentDir = new ArrayList<>();
        Map<String, Integer> dirSize = new HashMap<>();

        for (String line : lines) {
            if(line.charAt(0) == '$'){
                String[] command = line.split(" ");

                if(command[1].equals("cd")) {
                    if (command[2].equals("..")) {
                        currentDir.remove(currentDir.size() - 1);
                    } else {
                        currentDir.add(command[2]);
                    }
                }
            } else if (!(line.startsWith("dir"))) {
                String[] file = line.split(" ");
                int fileSize = Integer.parseInt(file[0]);
                System.out.println(currentDir);
                String fileDir = String.join("/", currentDir).replace("//", "/");
                dirSize.put(fileDir, dirSize.getOrDefault(fileDir, 0) + fileSize);

                while (fileDir.contains("/")) {
                    fileDir = fileDir.substring(0, fileDir.lastIndexOf("/"));
                    dirSize.put(fileDir, dirSize.getOrDefault(fileDir, 0) + fileSize);
                }
            }
        }

        int maxSpace = 70000000;
        int minUsed = 30000000;
        int currentUnused = maxSpace - dirSize.get("");

        int requiredSpace = minUsed - currentUnused;

        Integer smallestSize = Integer.MAX_VALUE;

        for(Integer size : dirSize.values()){
            if(size < smallestSize && size > requiredSpace){
                smallestSize = size;
            }
        }

        System.out.println(smallestSize);
    }
}

