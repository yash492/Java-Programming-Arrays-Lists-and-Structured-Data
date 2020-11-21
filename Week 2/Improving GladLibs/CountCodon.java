import java.util.*;
import edu.duke.*;

public class CountCodon {
    private HashMap<String, Integer> map;

    public CountCodon() {
        map = new HashMap<String, Integer> ();
    }

    private void buildCodon(String dna, int start) {
        while (true) {
            int currentIndex = start + 3;
            if (dna.length() < currentIndex) {
                break;
            }
            String gene = dna.substring(start, currentIndex);
            if (! map.containsKey(gene)) {
                map.put(gene, 1);
            }
            else {
                int value = map.get(gene);
                map.put(gene, value + 1);
            }
            start = currentIndex;
        }
    }

    private String getMostCommonCodon () {
        int max = -1;
        String maxWord = "";
        for (String word: map.keySet()) {
            if (max == -1 || map.get(word) > max) {
                max = map.get(word);
            }
        }
        for (String word: map.keySet()) {
            if (max == map.get(word)) {
                maxWord = word;
                break;
            }
        }
        return maxWord;
    }

    private void printCodonCounts(int start, int end) {
        int count = 0;
        for(String word: map.keySet()) {
            if (map.get(word) >= start && map.get(word) <= end) {
                System.out.println(word + " " + map.get(word));
                count += 1;
            }
        }
        System.out.println(count);
    }

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        // for(int k =0; k<3; k++) {
        //     int start = k;
        //     buildCodon(dna, start);
        //     printCodonCounts(0, 1);
        //     System.out.println();
        // }
        int start = 0;
        buildCodon(dna, start);

        String mostCommonCodon = getMostCommonCodon();
        System.out.println(mostCommonCodon);
        
        // System.out.println(map.get(mostCommonCodon));
        // System.out.println(map.size());

        int startPrint = 7;
        int endPrint = 7;
        printCodonCounts(startPrint, endPrint);
    }

    public static void main(String[] args) {
        CountCodon cc = new CountCodon();
        cc.tester();
    }
}