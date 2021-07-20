import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private final HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        map = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f.getPath());
        for (String w : fr.words()) {
            if (!map.containsKey(w)) {
                //System.out.println("Adding \"" + w + "\" to hash map");
                map.put(w, new ArrayList<>());
            }

            String fileName = f.getName();
            if (!map.get(w).contains(fileName)) {
                ArrayList<String> files = map.get(w);
                files.add(fileName);
                map.put(w, files);
            }
        }
    }

    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int maxNum = 0; // the size of the array list will tell us the number of file appearances
        for (ArrayList arrList : map.values()) {
            int currNumFiles = arrList.size();
            if (currNumFiles > maxNum) {
                maxNum = currNumFiles;
            }
        }

        return maxNum;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> output = new ArrayList<>();
        for (ArrayList arrList : map.values()) {
            int currNumFiles = arrList.size();
            if (currNumFiles == number) {
                output = arrList;
            }
        }
        System.out.println("No array list was found for file appearance of " + number);
        return output;

    }


    private void printsFilesIn(String word) {
        System.out.println("\"" + word + "\" appears in the files: \n");
        if (!map.containsKey(word)) {
            System.out.println("Word not found");
            return;
        }
        ArrayList<String> arrList = map.get(word);
        for (String s : arrList) {
            System.out.println(s);
        }
        System.out.println("\n");
    }

    public void tester() {
        buildWordFileMap();
        System.out.println("Printing map...");
        ArrayList<String> wordsin7 = new ArrayList<>();

        for (String w : map.keySet()) {
            System.out.println("Word: " + w + " : in files: " + map.get(w).size());

            if (map.get(w).size() == 7) {
                wordsin7.add(w);
            }
        }

        System.out.println("All 7: " + wordsin7.size());

//        int number = maxNumber();
        int count = 0;
        int number = 4;
        //int number = 7;
        for (String w : map.keySet()) {
            if (map.get(w).size() == number) {
                System.out.println("Word(s) with most file appearances:\n" + w);
                printsFilesIn(w);
                count++;
            }
        }


        System.out.println("Number of words that appear in " + number + " files " + count);

        String tree = "tree";
        System.out.println("Files where the word " + tree + " appears in:\n");
        for (String s : map.get(tree)) {
            System.out.println(s);
        }

        String sea = "sea";
        System.out.println("Files where the word " + sea + " appears in:\n");
        for (String s : map.get(sea)) {
            System.out.println(s);
        }

        String sad = "sad";
        System.out.println("Files where the word " + sad + " appears in:\n");
        for (String s : map.get(sad)) {
            System.out.println(s);
        }

        String red = "red";
        System.out.println("Files where the word " + red + " appears in:\n");
        for (String s : map.get(red)) {
            System.out.println(s);
        }
    }


    public static void main(String[] args) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
    }
}

