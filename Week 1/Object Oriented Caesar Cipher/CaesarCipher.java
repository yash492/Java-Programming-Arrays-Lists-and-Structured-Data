public class CaesarCipher {
    private String alphabet;
    private String shiftAlphabet;
    private int mainKey;

    public CaesarCipher (int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int k=0; k < encrypted.length(); k++) {
            char letter = encrypted.charAt(k);
            if (Character.isUpperCase(letter)) {
                alphabet = alphabet.toUpperCase();
                shiftAlphabet = shiftAlphabet.toUpperCase();
            }
            else {
                alphabet = alphabet.toLowerCase();
                shiftAlphabet = shiftAlphabet.toLowerCase();
            }
            int index = alphabet.indexOf(letter);
            if (index != -1) {
                char newLetter = shiftAlphabet.charAt(index);
                encrypted.setCharAt(k, newLetter);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(encrypted);
    }
}