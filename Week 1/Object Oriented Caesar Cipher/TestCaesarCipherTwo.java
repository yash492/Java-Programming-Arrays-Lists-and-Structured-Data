public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        StringBuilder msg = new StringBuilder();
        for (int k=start; k < message.length(); k+=2) {
            char ch = message.charAt(k);
            msg.append(ch);
        }
        return msg.toString();
    }

    private int maxIndex(int[] freq) {
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

    private int[] countLetters(String encrypted) {
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

    private int maxLength(String firstDecrypt, String secondDecrypt) {
        if (firstDecrypt.length() > secondDecrypt.length()){
            return firstDecrypt.length();
        }
        return secondDecrypt.length();
    }

    private String concatenateTwoDecrypt(String firstDecrypt, String secondDecrypt) {
        int totalLength = firstDecrypt.length() + secondDecrypt.length();
        if (totalLength % 2 == 1) {
            secondDecrypt  = secondDecrypt + " ";
        }
        int maxLen = maxLength(firstDecrypt, secondDecrypt);
        StringBuilder decrypt = new StringBuilder();
        for (int k=0; k < maxLen; k++) {
            char firstLetter = firstDecrypt.charAt(k);
            decrypt.append(firstLetter);
            char secondLetter = secondDecrypt.charAt(k);
            decrypt.append(secondLetter);
        }
        return decrypt.toString();
    }

    private int getKey(String s) {
        int[] freq = countLetters(s);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex <  4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    } 

    public String breakCaesarCipher(String encrypted) { 
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int dkey1 = getKey(firstHalf);
        int dkey2 = getKey(secondHalf);
        CaesarCipher cc1 = new CaesarCipher(dkey1);
        CaesarCipher cc2 = new CaesarCipher(dkey2);
        return concatenateTwoDecrypt(cc1.decrypt(firstHalf), cc2.decrypt(secondHalf));


    }

    public void SimpleTest() {
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
        String encrypted = cct.encrypt("I am Yash Jain.");;
        System.out.println(encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println(decrypted);

        encrypted = cct.encrypt("A sentence with lot of eeeeeeeeeeeeeees.");
        System.out.println(encrypted);
        String decrypted1 = breakCaesarCipher(encrypted);
        System.out.println(decrypted1);


    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
        tcct.SimpleTest();
    }
}
