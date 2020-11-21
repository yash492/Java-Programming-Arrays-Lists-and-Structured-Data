import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {
    public void countWords() {
        FileResource fr = new FileResource();
        //     <key,    value>
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String w: fr.words()) {
            w = w.toLowerCase();
            if (map.keySet().contains(w)) {
                map.put(w, map.get(w) + 1);
            }
            else {
                map.put(w, 1);
            }
        }
        for (String w: map.keySet()) {
            int occurrences = map.get(w);
            if (occurrences > 300) {
                System.out.println(occurrences + "\t" + w);
            }
        }
    }

    public static void main(String[] args) {
        WordFrequenciesMap wfm = new WordFrequenciesMap();
        wfm.countWords();
    }
}