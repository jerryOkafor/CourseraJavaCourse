import edu.duke.FileResource;
import kotlin.text.UStringsKt;

import java.util.HashMap;
import java.util.Map;

public class WordFrequenciesMap {
    private final HashMap<String, Integer> countWords;

    public WordFrequenciesMap() {
        this.countWords = new HashMap<>();
    }


    public void findUnique() {
        countWords.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();

            if (!countWords.containsKey(word)) {
                countWords.put(word, 1);
            } else {
                int newCount = countWords.get(word) + 1;
                countWords.replace(word, newCount);
            }
        }

    }

    public String findKeyOfMax() {
        String currMaxIndex = null;

        for (Map.Entry<String, Integer> set : countWords.entrySet()) {
            if (currMaxIndex == null) {
                currMaxIndex = set.getKey();
                continue;
            }

            if (set.getValue() > countWords.get(currMaxIndex))
                currMaxIndex = set.getKey();
        }

        return currMaxIndex;
    }

    public void tester() {
        findUnique();

        for (Map.Entry<String, Integer> set : countWords.entrySet()) {
            System.out.println(set.getKey() + "\t" + set.getValue());

        }

        String indexOfMax = findKeyOfMax();

        System.out.println("Unique words: " + countWords.size());
        System.out.println("Most common word: " + indexOfMax + ", Occurring " + countWords.get(indexOfMax) + " times");
    }

    public static void main(String[] args) {
        WordFrequenciesMap wordFrequencies = new WordFrequenciesMap();
        wordFrequencies.tester();

    }
}
