public class WordPlay {
    public boolean isVowel(char ch) {
        if (Character.isLowerCase(ch)) {
            ch = Character.toUpperCase(ch);
        }
        if (ch == 'A' ||ch == 'E'||ch == 'I'||ch == 'O'||ch == 'U') {
            return true;
        }
        return false;
    }

    public String replaceVowel(String phrase, char ch) {
        StringBuilder replacedPhrase = new StringBuilder(phrase);
        for(int i = 0; i < replacedPhrase.length(); i++) {
            char letter = replacedPhrase.charAt(i);
            if (isVowel(letter)) {
                replacedPhrase.setCharAt(i, ch);
            }
        }
        return replacedPhrase.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder emphasized = new StringBuilder(phrase);
        if (Character.isUpperCase(ch)) {
            ch = Character.toLowerCase(ch);
        }
        for(int i = 0; i < emphasized.length(); i++) {
            char letter = emphasized.charAt(i);
            if (Character.isUpperCase(letter)) {
                letter = Character.toLowerCase(letter);
            }
            if (letter == ch) {
                if (i % 2 == 0) {
                    emphasized.setCharAt(i, '*');
                }
                else {
                    emphasized.setCharAt(i, '+');
                }
            }
        }
        return emphasized.toString();
    }

    public void testIsVowel() {
        char ch = '*';
        boolean bool = isVowel(ch);
        System.out.println(bool);
    }

    public void testReplaceVowel() {
        String phrase = "Hello World!";
        char ch = '*';
        String replacedPhrase = replaceVowel(phrase, ch);
        System.out.println(replacedPhrase);
    }

    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        phrase = "Mary Bella Abracadabra";
        String emphasized = emphasize(phrase, ch);
        System.out.println(emphasized);
    }

    public static void main(String[] args) {
        WordPlay obj = new WordPlay();
        //obj.testIsVowel();
        //obj.testReplaceVowel();
        obj.testEmphasize();
    }
}