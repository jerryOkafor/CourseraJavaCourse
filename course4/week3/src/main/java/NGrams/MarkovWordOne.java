package NGrams;

import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class MarkovWordOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, key, pos);
            if (start == -1) {
                break;
            }
            if (start + key.length() >= myText.length) {
                break;
            }
            String next = myText[start + 1];
            follows.add(next);
            pos = start + key.length();
            pos = start + 1;
        }
        return follows;
    }

    public void testIndexOf() {
        String[] test = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};

        String key1 = "this";
        int start1 = 0;
        System.out.println("Index of: " + key1 + " is: " + indexOf(test, key1, start1));

        String key2 = "this";
        int start2 = 3;
        System.out.println("Index of: " + key2 + " is: " + indexOf(test, key1, start2));

        String key3 = "frog";
        int start3 = 5;
        System.out.println("Index of: " + key3 + " is: " + indexOf(test, key1, start3));

        String key4 = "simple";
        int start4 = 2;
        System.out.println("Index of: " + key4 + " is: " + indexOf(test, key1, start4));

        String key5 = "test";
        int start5 = 5;
        System.out.println("Index of: " + key5 + " is: " + indexOf(test, key1, start5));

    }
}
