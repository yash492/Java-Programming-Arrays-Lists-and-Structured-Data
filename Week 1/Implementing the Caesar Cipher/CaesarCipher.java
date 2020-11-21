public class CaesarCipher {
    public String encrypt (String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftAlphabet = "";
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)) {
                alphabet = alphabet.toLowerCase();
                shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
            }
            else {
                alphabet = alphabet.toUpperCase();
                shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
            }
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
            char newChar = shiftAlphabet.charAt(idx);
            encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys (String input, int key1, int key2) {
        String encrypt1 = encrypt(input, key1);
        String encrypt2 = encrypt(input, key2);
        StringBuilder whatever = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch1 = encrypt1.charAt(i);
            char ch2 = encrypt2.charAt(i);
            if (i % 2 == 0) {
                whatever.setCharAt(i, ch1);
            }
            else {
                whatever.setCharAt(i, ch2);
            }
        }
        return whatever.toString();
    }

    public void testEncrypt() {
        String text = "I am Yash Jain.";
        int key = 3;
        text = "FIRST LEGION ATTACK EAST FLANK!";
        key = 23;
        text = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        key = 15;
        String encrypt = encrypt(text, key);
        System.out.println(encrypt);
    }

    public void testEncrytTwoKeys() {
        String text = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String encrypt = encryptTwoKeys(text, key1, key2);
        System.out.println(encrypt);
    }

    public static void main(String[] args) {
        CaesarCipher obj = new CaesarCipher();
         obj.testEncrypt();
         obj.testEncrytTwoKeys();
    }
}

