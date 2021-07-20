import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private final ArrayList<String> myWords;
    private final ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        this.myWords = new ArrayList<>();
        this.myFreqs = new ArrayList<>();
    }


    public void findUnique() {
        myWords.clear();
        myFreqs.clear();

        FileResource fr = new FileResource();

        for (String word : fr.words()) {
            word = word.toLowerCase();

            int index = myWords.indexOf(word);

            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int newCount = myFreqs.get(index) + 1;
                myFreqs.set(index, newCount);
            }
        }

    }

    public int findIndexOfMax() {
        int currMaxIndex = 0;

        for (int i = 0; i < myWords.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(currMaxIndex))
                currMaxIndex = i;
        }

        return currMaxIndex;
    }

    public void tester() {
        findUnique();

        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        int indexOfMax = findIndexOfMax();

        System.out.println("Unique words: " + myWords.size());
        System.out.println("Most common word: " + myWords.get(indexOfMax) + ", Occurring " + myFreqs.get(indexOfMax) + " times");
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.tester();

    }
}
