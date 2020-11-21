public class CaesarCipherTwo {
    // private String alphabet;
    // private String shiftedAlphabet1;
    // private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;


    public CaesarCipherTwo(int key1, int key2) {
        // alphabet = "abcdefghijklmnopqrstuvwxyz";
        // shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        // shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt (String input) {
        CaesarCipher cc1 = new CaesarCipher(mainKey1);
        CaesarCipher cc2 = new CaesarCipher(mainKey2);
        String encrypt1  = cc1.encrypt(input);
        String encrypt2  = cc2.encrypt(input);
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

    private String concatanateTwoDecryptWhenNotBreaking(String firstDecrypt, String secondDecrypt) {
        StringBuilder combined = new StringBuilder();
        for (int k=0; k < firstDecrypt.length(); k++) {
            if (k % 2 == 0) {
                char firstDecryptLetter = firstDecrypt.charAt(k);
                combined.append(firstDecryptLetter);
            }
            else {
                char secondDecryptLetter = secondDecrypt.charAt(k);
                combined.append(secondDecryptLetter);
            }
        }
        return combined.toString();
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc1 = new CaesarCipher(26 - mainKey1);
        CaesarCipher cc2 = new CaesarCipher(26 - mainKey2);
        return concatanateTwoDecryptWhenNotBreaking(cc1.encrypt(encrypted), cc2.encrypt(encrypted));
    }
}
