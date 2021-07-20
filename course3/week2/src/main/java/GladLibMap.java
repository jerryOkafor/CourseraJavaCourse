import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GladLibMap {
    private final HashMap<String, ArrayList<String>> myMap;
    private final ArrayList<String> usedWords;
    private final ArrayList<String> wordsConsidered;
    private final Random myRandom;

    private static final String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "course3/data/week2/short";

    public GladLibMap() {
        myRandom = new Random();
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);

        usedWords = new ArrayList<>();
        wordsConsidered = new ArrayList<>();
    }

    public GladLibMap(String source) {
        myRandom = new Random();
        myMap = new HashMap<>();
        initializeFromSource(source);

        usedWords = new ArrayList<>();
        wordsConsidered = new ArrayList<>();
    }

    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "fruit", "verb"};

        for (String label : labels) {
            ArrayList<String> arrayList = readIt(source + "/" + label + ".txt");
            myMap.put(label, arrayList);
        }

        System.out.println(myMap);
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        while (true) {
            if (!usedWords.contains(sub)) {
                usedWords.add(sub);
                break;
            }
            sub = getSubstitute(w.substring(first + 1, last));
        }

        if (!wordsConsidered.contains(sub)) {
            wordsConsidered.add(sub);
        }
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        StringBuilder story = new StringBuilder();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story.append(processWord(word)).append(" ");
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story.append(processWord(word)).append(" ");
            }
        }
        return story.toString();
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        usedWords.clear();

        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory + "/madtemplate2.txt");
        printOut(story, 60);

        System.out.println("\n\nTotal number of replace words: " + usedWords.size());
    }

    public int totalWordsInMap() {
        int count = 0;

        for (String label : myMap.keySet()) {
            count += myMap.get(label).size();
        }
        return count;
    }

    public int totalWordsConsidered() {
        int count = 0;

        for (String label : wordsConsidered) {
            count += myMap.get(label).size();
        }

        return count;
    }

    public static void main(String[] args) {
        GladLibMap gladLib = new GladLibMap();
        gladLib.makeStory();

        System.out.println("Total words in map: " + gladLib.totalWordsInMap());
        System.out.println("Total words considered: " + gladLib.totalWordsConsidered());
    }

}
