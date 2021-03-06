import edu.duke.FileResource;

/**
 * Assignment 1:  Word lengths
 * You will write a program to figure out the most common word length of words from a file. To
 * solve this problem you will need to keep track of how many words from a file are of each
 * possible length. You should group all words of length 30 or more together, and you should not
 * count basic punctuation that are the first or last characters of a group of characters.
 */
public class WordLengths {

    /**
     * Read in the words from resource and count the number of words of each length for all the
     * words in resource, storing these counts in the array counts
     */
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int wordlength = word.length();

            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                wordlength--;
            }
            if (wordlength >= counts.length) {
                wordlength = counts.length - 1;
            }

            if (wordlength > 0) {
                counts[wordlength] += 1;
            }
        }
    }

    /**
     * Returns the index position of the largest element in values
     */
    public int indexOfMax(int[] values) {
        int maxIndex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0 && values[i] >= values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Call countWordLengths with a file and then print the number of words of each
     * length. Test it on the small file smallHamlet.txt shown below
     */
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];

        countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println(counts[i] + " words of length " + i + ": ");
            }
        }

        int maxIndex = indexOfMax(counts);

        System.out.println("The most common word length in the file is " + maxIndex);

    }

    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();

        wordLengths.testCountWordLengths();
    }
}
