import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliceBuilder = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sliceBuilder.append(message.charAt(i));
        }
        return sliceBuilder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] slices = new String[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);

        for (int i = 0; i < klength; i++) {
            slices[i] = sliceString(encrypted, i, klength);
        }

        int i = 0;
        while (i < klength) {
            key[i] = caesarCracker.getKey(slices[i]);
            i++;
        }

        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> output = new HashSet<>(); // new hash set to contain the dictionary words
        for (String line : fr.lines()) {
            String lineLower = line.toLowerCase();
            output.add(lineLower);

        }
        return output;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            String wordLower = word.toLowerCase();
            if (dictionary.contains(wordLower)) {
                count += 1;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int[] keyList = new int[100];
        int[] wordCount = new int[100];

        for (int i = 1; i <= 100; i++) {
            keyList[i - 1] = i;
        }

        for (int i = 0; i < 100; i++) {
            int[] key = tryKeyLength(encrypted, keyList[i], 'e');
            //method which returns a possible key arrangement to decrypt below
            VigenereCipher vc = new VigenereCipher(key);
            String decryptedMsg = vc.decrypt(encrypted);
            wordCount[i] = countWords(decryptedMsg, dictionary);
        }

        int largestWordCount = 0;
        int largestWordCountIndex = 0;
        for (int i = 0; i < 100; i++) {
            if (wordCount[i] > largestWordCount) {
                largestWordCount = wordCount[i];
                largestWordCountIndex = i;
            }
        }

        System.out.println("\nThe largest word count is: " + largestWordCount);

        int keyLengthWithMaxWordCount = keyList[largestWordCountIndex];
        char mostCommonChar = mostCommonCharacterInDictionary(dictionary);
        int[] keysArr = tryKeyLength(encrypted, keyLengthWithMaxWordCount, mostCommonChar);

        System.out.println("\n\nPossible key is: \n" + Arrays.toString(keysArr));

        System.out.println("\nThe key length is " + keysArr.length);
        VigenereCipher vc = new VigenereCipher(keysArr);
        return vc.decrypt(encrypted);
    }

    public char mostCommonCharacterInDictionary(HashSet<String> dictionary) {
        HashMap<Character, Integer> lettersWithCounts = new HashMap<>();
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alph.length(); i++) {
            lettersWithCounts.put(alph.charAt(i), 0);
        }

        for (String s : dictionary) {
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++) {
                char currChar = s.charAt(i);
                if (lettersWithCounts.containsKey(currChar)) {
                    lettersWithCounts.put(currChar, lettersWithCounts.get(currChar) + 1);
                }
            }
        }

        System.out.println(lettersWithCounts);
        int max = 0;
        char output = 'e';

        for (Character c : lettersWithCounts.keySet()) {
            int count = lettersWithCounts.get(c);
            if (count > max) {
                max = count;
                output = c;
            }
        }

        return output;
    }

    public void breakVigenere() {
        FileResource fileResource = new FileResource();
        String fileContent = fileResource.asString();

//        HashSet<String> dictionary = readDictionary(new FileResource("/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course3/data/week4/dictionaries/English"));

        int testKeyLength = 4;
        int[] keyLength = tryKeyLength(fileContent, testKeyLength, 'e');
        VigenereCipher cipher = new VigenereCipher(keyLength);
        String possibleMessage = cipher.decrypt(fileContent);

        System.out.println("\nUsing KeyLength: " + Arrays.toString(keyLength));
        System.out.println("\n\nOriginal Message: \n\n" + fileContent);
        System.out.println("\nDecrypted Message: \n\n" + possibleMessage);

//        String[] allWords = possibleMessage.split(" ");
//        System.out.println("\nAll words: \n\n" + allWords.length);

//        ArrayList<String> validWords = new ArrayList<>();
//        for (String word : allWords) {
//            if (dictionary.contains(word)) {
//                validWords.add(word);
//            }
//        }
//
//        System.out.println("\nValid words: \n\n" + validWords.size());
    }


    public void breakVigenereForUnknownKeyLength() {
        FileResource fileResource = new FileResource();
        String fileContent = fileResource.asString();

        HashSet<String> dictionary = readDictionary(new FileResource(Tester.dictDir + "/English"));

        String possibleMessage = breakForLanguage(fileContent, dictionary);

        System.out.println("\n\nOriginal Message: \n\n" + fileContent);
        System.out.println("\nDecrypted Message: \n\n" + possibleMessage);

        System.out.println("\nValid words: \n\n" + countWords(possibleMessage, dictionary));
    }

    public void breakVignereForAllLanguage(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String language : languages.keySet()) {
            System.out.println("\n\nBreaking for language: " + language);

            HashSet<String> langDict = languages.get(language);
            String decryptedMessage = breakForLanguage(encrypted, langDict);

            if (language.equals("german")) {
                System.out.println("\n\nDecrypted Message: \n" + decryptedMessage);
            }
        }

    }

}
