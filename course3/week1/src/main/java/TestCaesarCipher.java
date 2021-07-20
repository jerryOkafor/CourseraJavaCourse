import edu.duke.FileResource;

public class TestCaesarCipher {
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
        int key = getKey(input);
        CaesarCipherOOP ccTwo = new CaesarCipherOOP(key);
        String message = ccTwo.decrypt(input);
        System.out.println("Decrypted message is: \n" + message);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String test = fr.asString();
        int key1 = 18;
        CaesarCipherOOP caesarCipherOOP = new CaesarCipherOOP(key1);

        System.out.println("String to be encrypted: \n" + test);
        String encrypted = caesarCipherOOP.encrypt(test);
        System.out.println("String after encryption: \n" + encrypted);

        System.out.println("Now, to decrypt the message...");
        String decrypted = caesarCipherOOP.decrypt(encrypted);
        System.out.println("Decrypted message: \n" + decrypted);

        System.out.println("Choose an encrypted file to convert to string and use breakCaesarCipher to figure out the keys");
        FileResource fr2 = new FileResource();
        String test2 = fr2.asString();
        System.out.println("Calling breakCaesarCipher...");
        breakCaesarCipher(test2);

    }

    public static void main(String[] args) {
        TestCaesarCipher testCaesarCipher = new TestCaesarCipher();
        testCaesarCipher.simpleTests();

    }
}
