import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }

    private int maxIndex(int[] iarr) {
        int maxIndex = 0;
        for (int i = 0; i < iarr.length; i++) {
            if (iarr[i] > iarr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private String halfOfString(String message, int start) {
        StringBuilder msg = new StringBuilder(message);
        StringBuilder half = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char currChar = msg.charAt(i);
            int idx = message.indexOf(start);
            if ((start + i) < message.length()) {
                if (i % 2 == 0) {
                    char newChar = message.charAt(start + i);
                    half.append(newChar);
                }
            }
        }
        return half.toString();

    }

    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int decryptKey = maxIndex - 4;
        if (maxIndex < 4) {
            decryptKey = 26 - (4 - maxIndex);
        }

        return decryptKey;
    }


    public void breakCaesarCipher(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);

        int keyHalf1 = getKey(half1);
        int keyHalf2 = getKey(half2);

        System.out.println("Key for " + half1 + " is " + keyHalf1);
        System.out.println("Key for " + half2 + " is " + keyHalf2);

        CaesarCipherTwo ccTwo = new CaesarCipherTwo(keyHalf1, keyHalf2);
        String message = ccTwo.decrypt(input);
        System.out.println("Decrypted message is: \n" + message);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String test = fr.asString();
        int key1 = 17;
        int key2 = 3;
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(key1, key2);

        System.out.println("String to be encrypted: \n" + test);
        String encrypted = ccTwo.encrypt(test);
        System.out.println("String after encryption: \n" + encrypted);

        System.out.println("Now, to decrypt the message...");
        String decrypted = ccTwo.decrypt(encrypted);
        System.out.println("Decrypted message: \n" + decrypted);

        System.out.println("Choose an encrypted file to convert to string and use breakCaesarCipher to figure out the keys");
        FileResource fr2 = new FileResource();
        String test2 = fr2.asString();
        System.out.println("Calling breakCaesarCipher...");
        breakCaesarCipher(test2);

    }

    public void quiz() {
        CaesarCipher cipher = new CaesarCipher();

        System.out.println("Question 1: Encrypt the following phrase " +
                "with the Caesar Cipher algorithm, using key 15. " +
                "\nCan you imagine life WITHOUT the internet AND computers in your pocket? \n" +
                "What is the encrypted string?");
        int keyQ1 = 15;

        String strQ1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        String encrypted = cipher.encrypt(strQ1, keyQ1);
        System.out.println("Encrypted string for Q1 is:");
        System.out.println(encrypted);

        System.out.println("\n Question 2: Encrypt the following phrase with the algorithm " +
                "described for using two Caesar Cipher keys, with key1 = 21 and key2 = 8. " +
                "Can you imagine life WITHOUT the internet AND computers in your pocket? \n" +
                "What is the encrypted string?");

        int key1Q2 = 21;
        int key2Q2 = 8;

        encrypted = cipher.encryptTwoKeys(strQ1, key1Q2, key2Q2);
        System.out.println("Encrypted string for Q2 is: \n" + encrypted + "\n");

        System.out.println("Q4: Consider the file errors.txt. " +
                "What is the most common word length (ignoring the punctuation of the first and last " +
                "character of each group of characters)?\n" + "Choose the file errors.txt");

        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();

        System.out.println("Q5: same as Q4, but instead choose the file manywords.txt" + "\n");
        wl.testCountWordLengths();

        System.out.println("Q6: The following phrase was encrypted with the two-key encryption method " +
                "we discussed using the two keys 14 and 24. What is the decrypted message? " +
                "Hfs cpwewloj loks cd Hoto kyg Cyy.");

        int key1Q6 = 14;
        int key2Q6 = 24;
        encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String decrypted = cipher.encryptTwoKeys(encrypted, 26 - key1Q6, 26 - key2Q6);

        System.out.println("Decrypted message is: \n" + decrypted + "\n");

        System.out.println("Q7: The following phrase was encrypted with the two-key encryption method " +
                "described in this course. You will need to figure out which keys were used to encrypt it. " +
                "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx! \n" + "What is the original message?");

        encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";

        CaesarBreaker cb = new CaesarBreaker();
        decrypted = cb.decryptTwoKeys(encrypted);
        System.out.println("Original message is: \n" + decrypted + "\n");

        System.out.println("Q8: Decrypt the encrypted file mysteryTwoKeysQuiz.txt. " +
                "This file is encrypted with the two-key encryption method we discussed. " +
                "You’ll need to decrypt the complete file by figuring out which keys were used to decrypt it. \n" +
                "What are the first five decrypted words?\n" + "Choose the file mysteryTwoKeysQuiz.txt");

        FileResource fr = new FileResource();
        encrypted = fr.asString();
        decrypted = cb.decryptTwoKeys(encrypted);
        System.out.println(decrypted);
        System.out.println("Q9: get the two keys from Q8 (should print out through the last method call");
    }

    public static void main(String[] args) {

        TestCaesarCipherTwo testCaesarCipherTwo = new TestCaesarCipherTwo();
        testCaesarCipherTwo.quiz();
    }
}
