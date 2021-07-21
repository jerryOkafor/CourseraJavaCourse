import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Tester {
    private final String testDataDir = "/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course3/data/week4";
    public static final String dictDir = "/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course3/data/week4/dictionaries";

    public void testCaesarCipherAndCracker() {
        CaesarCipher caesarCipher = new CaesarCipher(6);
        FileResource fileResource = new FileResource();

        String encrypted = caesarCipher.encrypt(fileResource.asString());
        System.out.println("Original message: \n\n" + fileResource.asString() + "\n\nEncrypted message: \n\n" + encrypted);

        CaesarCracker caesarCracker = new CaesarCracker();
        String crackedMessage = caesarCracker.decrypt(encrypted);

        System.out.println("\n\nCracked message: \n\n" + crackedMessage);
    }

    public void testCeasarCracker() {
        FileResource fileResource = new FileResource();
        CaesarCracker caesarCracker = new CaesarCracker();
        String crackedMessage = caesarCracker.decrypt(fileResource.asString());

        System.out.println("\n\nOriginal Encrypted message: \n\n" + fileResource.asString());
        System.out.println("\n\nCracked message: \n\n" + crackedMessage);
    }

    public void testCaesarCrackerForPortugues() {
        FileResource fileResource = new FileResource();
        CaesarCracker caesarCracker = new CaesarCracker('a');
        String crackedMessage = caesarCracker.decrypt(fileResource.asString());

        System.out.println("\n\nOriginal Encrypted message: \n\n" + fileResource.asString());
        System.out.println("\n\nCracked message: \n\n" + crackedMessage);
    }


    public void testVigenereCipher() {
        String key = "rome"; //{17, 14, 12, 4}
        int[] intKey = {17, 14, 12, 4};
        VigenereCipher vigenereCipher = new VigenereCipher(intKey);
        FileResource fileResource = new FileResource();

        String orginalMessage = fileResource.asString();
        String encrypted = vigenereCipher.encrypt(orginalMessage);
        String decrypted = vigenereCipher.decrypt(encrypted);

        System.out.println("\n\nOriginal Message: \n\n" + orginalMessage);
        System.out.println("\n\nEncrypted Message: \n\n" + encrypted);
        System.out.println("\n\nCracked Message: \n\n" + decrypted);


    }

    public void testSliceString() {
        VigenereBreaker breaker = new VigenereBreaker();
        String testStr = "abcdefghijklm";
        System.out.println("Expected: adgjm. Output is " + breaker.sliceString(testStr, 0, 3));
        System.out.println("Expected: behk.  Output is " + breaker.sliceString(testStr, 1, 3));
        System.out.println("Expected: cfil.  Output is " + breaker.sliceString(testStr, 2, 3));
        System.out.println("Expected: aeim.  Output is " + breaker.sliceString(testStr, 0, 4));
        System.out.println("Expected: bfj.   Output is " + breaker.sliceString(testStr, 1, 4));
        System.out.println("Expected: cgk.   Output is " + breaker.sliceString(testStr, 2, 4));
        System.out.println("Expected: dhl.   Output is " + breaker.sliceString(testStr, 3, 4));
        System.out.println("Expected: afk.   Output is " + breaker.sliceString(testStr, 0, 5));
        System.out.println("Expected: bgl.   Output is " + breaker.sliceString(testStr, 1, 5));
        System.out.println("Expected: chm.   Output is " + breaker.sliceString(testStr, 2, 5));
        System.out.println("Expected: di.    Output is " + breaker.sliceString(testStr, 3, 5));
        System.out.println("Expected: ej.    Output is " + breaker.sliceString(testStr, 4, 5));
    }

    public void testTryKeyLength() {
        FileResource fileResource = new FileResource(testDataDir + "/secretmessage1.txt");
        VigenereBreaker breaker = new VigenereBreaker();

        String keyString = "flute";
        int[] expectedKey = {5, 11, 20, 19, 4};

        int[] key = breaker.tryKeyLength(fileResource.asString(), 4, 'e');
        System.out.println("Output Key: " + Arrays.toString(key));
    }

    public void testTryKeyLength2() {
        FileResource fileResource = new FileResource(testDataDir + "/secretmessage2.txt");

        int testKeyLength = 38;
        VigenereBreaker breaker = new VigenereBreaker();
        int[] key = breaker.tryKeyLength(fileResource.asString(), testKeyLength, 'e');
        VigenereCipher vigenereCipher = new VigenereCipher(key);
        String decryptedMessage = vigenereCipher.decrypt(fileResource.asString());

        HashSet<String> engDic = breaker.readDictionary(new FileResource(dictDir + "/English"));
        int wordCount = breaker.countWords(decryptedMessage, engDic);

        System.out.println("\nKey Used: " + Arrays.toString(key));
        System.out.println("\nWord Count: " + wordCount);

    }

    public void tesBreakVigenere() {
        VigenereBreaker breaker = new VigenereBreaker();
        breaker.breakVigenere();
    }

    public void testBreakForLanguage() {
//        FileResource fileResource = new FileResource(testDataDir + "/athens_keyflute.txt");
        FileResource fileResource = new FileResource(testDataDir + "/secretmessage2.txt");
        VigenereBreaker breaker = new VigenereBreaker();

        HashSet<String> engDic = breaker.readDictionary(new FileResource(dictDir + "/English"));
        String encryptedMessage = fileResource.asString();

        String decryptedMessage = breaker.breakForLanguage(encryptedMessage, engDic);
        System.out.println("\n\nOriginal Encrypted message: \n" + encryptedMessage);
        System.out.println("\n\nDescypted message: \n" + decryptedMessage);
    }

    public void testBreakForAllLanguages() {
        VigenereBreaker breaker = new VigenereBreaker();
        HashMap<String, HashSet<String>> languageDictionaries = new HashMap<>();

        System.out.println("Choose the languages you wish to test on:");
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            String languageName = file.getName().toLowerCase();
            FileResource fr = new FileResource(file);
            HashSet<String> languageDic = breaker.readDictionary(fr);
            languageDictionaries.put(languageName, languageDic);
            System.out.println("Mapped the language:  \"" + languageName + "\" to its dictionary");
        }

        System.out.println("Choose an encrypted file to be decrypted for all languages:");
//        FileResource fileResource = new FileResource(testDataDir + "/athens_keyflute.txt");
        FileResource fileResource = new FileResource(testDataDir + "/secretmessage4.txt");
        breaker.breakVignereForAllLanguage(fileResource.asString(), languageDictionaries);
    }

    public static void main(String[] args) {

        Tester tester = new Tester();
//        tester.testCaesarCipherAndCracker();
//        tester.testCaesarCipherAndCracker();
//        tester.testCaesarCrackerForPortugues();
//        tester.testVigenereCipher();
//        tester.testSliceString();
//        tester.testTryKeyLength();
//        tester.tesBreakVigenere();
//        tester.testBreakForLanguage();
//        tester.testTryKeyLength2();
        tester.testBreakForAllLanguages();

    }
}
