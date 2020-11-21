import java.util.*;

import edu.duke.FileResource;

public class Tester {
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println(slice);
    }

    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        int[] key = vb.tryKeyLength(fr.asString(), 4, 'e');
        for (int k: key){
            System.out.print(k + " ");
        }
    }

    public void testBreakVigenere () {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();        
    }

    public void testCountWords() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        FileResource dy = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(dy);
        String decrypt = vb.breakForLanguage(fr.asString(), dictionary);
        System.out.println(vb.countWords(decrypt, dictionary));
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        // t.testSliceString();
        // t.testTryKeyLength();
        t.testBreakVigenere();
        // t.testCountWords();
    }
}
