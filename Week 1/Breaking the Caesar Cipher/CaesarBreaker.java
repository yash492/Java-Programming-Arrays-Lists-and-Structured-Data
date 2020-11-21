import edu.duke.*;

public class CaesarBreaker {
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freq = countLetters(encrypted);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex <  4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println(dkey + "\t" + maxDex);
        return cc.encrypt(encrypted, 26 - dkey);
    }

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
            if ((max == -1 || freq[k] > max)){
                max = freq[k];
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public String halfOfString(String message, int start) {
        StringBuilder msg = new StringBuilder();
        for (int k=start; k < message.length(); k+=2) {
            char ch = message.charAt(k);
            msg.append(ch);
        }
        return msg.toString();
    }

    public int getKey(String s) {
        int[] freq = countLetters(s);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex <  4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    } 
    public int maxLength(String firstDecrypt, String secondDecrypt) {
        if (firstDecrypt.length() > secondDecrypt.length()){
            return firstDecrypt.length();
        }
        return secondDecrypt.length();
    }

    public String concatenateTwoDecrypt(String firstDecrypt, String secondDecrypt) {
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

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        // replace dkey1 = 2 and dkey2 = 20 for question 8.
        int dkey1 = getKey(firstHalf);
        int dkey2 = getKey(secondHalf);
        System.out.println(dkey1 + " " + dkey2);

        String firstDecrypt = cc.encrypt(firstHalf, 26 - dkey1);
        String SecondDecrypt = cc.encrypt(secondHalf, 26 - dkey2);
        return concatenateTwoDecrypt(firstDecrypt, SecondDecrypt);
    } 



    public void testCountLetters() {
        String word = "aaabbbaaaaa";
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//java/manywords.txt");        
        int[] count = countLetters(url.asString());
        for (int k = 0; k < count.length; k++) {
            System.out.println(count[k] + "\t" + k);
        }
    }

    public void testMaxIndex() {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//java/manywords.txt");        
        int[] count = countLetters(url.asString());
        int maxIndex = maxIndex(count);
        System.out.println(maxIndex);
    }

    public void testDecrypt() {
        String encrypted = "UXGHI ATVXDC PIIPRZ TPHI UAPCZ TTT!";
        String decrypt = decrypt(encrypted);
        System.out.println(decrypt);
    }

    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        int start = 1;
        String halfMessage = halfOfString(message, start);
        System.out.println(halfMessage);
    }

    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//java/mysteryTwoKeysQuiz.txt");
        String encrypted = url.asString();
        String decryptTwoKeys = decryptTwoKeys(encrypted);
        System.out.println(decryptTwoKeys);
        }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        //cb.testDecrypt();
        //cb.testHalfOfString();
        // cb.testDecryptTwoKeys();
        cb.testCountLetters();
        cb.testMaxIndex();
    }
}
