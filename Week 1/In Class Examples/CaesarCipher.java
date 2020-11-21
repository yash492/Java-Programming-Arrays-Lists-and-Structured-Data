public class CaesarCipher {
    public String encrypt (String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftAlphabet = "";
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
                char shiftChar = shiftAlphabet.charAt(idx);
                encrypted.setCharAt(i, shiftChar);
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt() {
        String text = "I am Yash Jain.";
        String encrypt = encrypt(text, 3);
        System.out.println(encrypt);
    }

    public static void main(String[] args) {
        CaesarCipher obj = new CaesarCipher();
        obj.testEncrypt();
    }
}