import java.io.*;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice = new StringBuilder();
        for(int i=whichSlice; i<message.length(); i+=totalSlices) {
            char ch = message.charAt(i);
            slice.append(ch);
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker ccr = new CaesarCracker();
        for(int i=0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int k = ccr.getKey(slice);
            key[i] = k;
        }
        return key;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for (String word: dictionary) {
            for (int i=0; i < word.length(); i++) {
                char letter = word.charAt(i);
                int index = alphabet.indexOf(letter);
                if (index == -1) {
                    continue;
                }
                count [index] = count [index] + 1;
            }
        }

        CaesarCracker cc = new CaesarCracker();
        int maxIndex = cc.maxIndex(count);
        return alphabet.charAt(maxIndex);
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String decrypt = null;
        String lang = null;
        for(String language: languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(decrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                decrypt = decrypted;
                lang = language;
                //maxklength = i;
            } 
        }
        printLines(2, decrypt);
        System.out.println(lang);


    }



    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        /*
        FileResource fr = new FileResource();
        int klength = 4;
        char mostCommon = 'e';
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(encrypted));
        */

        /** 
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dy = new FileResource();
        HashSet<String> dictionary = readDictionary(dy);
        String decrypt = breakForLanguage(encrypted, dictionary);
        printLines(2, decrypt);
        */

        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap <String, HashSet<String>>();
        for(File f: dr.selectedFiles()){
            String language = f.getName();
            HashSet<String> dictionary = readDictionary(new FileResource(f));
            languages.put(language, dictionary);
        }
        FileResource fr = new FileResource();
        breakForAllLangs(fr.asString(), languages);
    }

    private void printLines(int num, String decrypt) {
        int i=0;
        for(String d: decrypt.split("\\n")) {
            i++;
            System.out.println(d);
            if (i==num) {
                break;
            }
        }
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String> ();
        for(String line: fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word: message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count += 1;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = 0;
        //int maxklength = 0;
        String decrypt = null;
        char mostCommonCharacter = mostCommonCharIn(dictionary);
        for (int i=1; i<10; i++){
            int[] key = tryKeyLength(encrypted, i, mostCommonCharacter);
            VigenereCipher vc = new VigenereCipher(key);
            String possibleDecrypted = vc.decrypt(encrypted);
            int count = countWords(possibleDecrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                decrypt = possibleDecrypted;
                //maxklength = i;
            }   
        }
        //System.out.println(maxCount);
        return decrypt;
        //return "";
    }

    
}
