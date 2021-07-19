public class WordPlay {

    public boolean isVowel(Character ch) {
        String vowels = "aeiou";
        String s = Character.toString(ch);
        s = s.toLowerCase();
        for (int i = 0; i < vowels.length(); i++) {
            int index = vowels.indexOf(i);
            if (s.equals(vowels.substring(i, i + 1))) {
                return true;
            }

        }
        return false;
    }

    public void testIsVowel() {
        System.out.println("Testing char 'F' for vowel");
        if (isVowel('F')) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        System.out.println("Testing char 'a' for vowel");
        if (isVowel('a')) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder phraseInput = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phraseInput.charAt(i);

            if (isVowel(currChar)) {
                phraseInput.setCharAt(i, ch);
            }
        }
        return phraseInput.toString();

    }

    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder phraseInput = new StringBuilder(phrase);
        String phraseLower = phrase.toLowerCase();
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phraseLower.charAt(i);
            int index = phraseLower.indexOf(ch);

            if (currChar == ch) {
                if ((i + 1) % 2 == 0) {// even number
                    phraseInput.setCharAt(i, '+');
                } else {
                    phraseInput.setCharAt(i, '*');
                }
            }
        }

        return phraseInput.toString();
    }

    public void testEmphasize() {
        System.out.println("Testing emphasize using string “dna ctgaaactga” with char 'a'");
        System.out.println();
        System.out.println("Result is: " + emphasize("dna ctgaaactga", 'a'));
        System.out.println("Result should be dn* ctg+*+ctg+");
        System.out.println();

        System.out.println("Test 2 with “Mary Bella Abracadabra” and char ‘a’ ");
        System.out.println();
        System.out.println("Result is: " + emphasize("Mary Bella Abracadabra", 'a'));
        System.out.println("Result should be M+ry Bell+ +br*c*d*br+");
        System.out.println();

    }

    public static void main(String[] args) {
        WordPlay wordPlay = new WordPlay();
//        wordPlay.testIsVowel();

//        wordPlay.testReplaceVowels();

        wordPlay.testEmphasize();

    }
}


