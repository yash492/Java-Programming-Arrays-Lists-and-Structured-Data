public class TestCaesarCipher {
    public int[] countLetters(String encrypted) {
        int[] count = new int[26];
        encrypted = encrypted.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int k=0; k < encrypted.length(); k++) {
            char letter = encrypted.charAt(k);
            int index = alphabet.indexOf(letter);
            if (index != -1) {
                count[index] += 1;
            }
        }
        return count;
    }

    public int maxIndex(int[] freq) {
        int max = -1;
        int maxIndex = -1;
        for (int k=0; k < freq.length; k++) {
            if (max == -1 || freq[k] > max){
                max = freq[k];
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void simpleTests() {
        int key = 15;
        String phrase = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(phrase);
        String decrypted = cc.decrypt(encrypted);
        String decrypted2 = breakCaesarCipher(encrypted);
        System.out.println(encrypted + "\t" + decrypted);
        System.out.println("breakCaesarCipher() returns " + decrypted2);

    }

    public String breakCaesarCipher(String encrypted) {
        int[] count = countLetters(encrypted);
        int maxIndex = maxIndex(count);
        int dkey = maxIndex - 4;
        if (maxIndex <  4) {
            dkey = 26 - (4 - maxIndex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(encrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipher tcc = new TestCaesarCipher();
        tcc.simpleTests();

    }
}
