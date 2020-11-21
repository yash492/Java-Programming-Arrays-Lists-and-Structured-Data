import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        FileResource fr = new FileResource();
        for(String s: fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            System.out.println(index);
            // The two ArrayList are initialised at parallel and implicitly connected by the index value.
            // This ensures that only unique values are stored
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            // If more than one value is present, then it adds 1 to myFreqs by getting the no. of words stored.
            // This is done via using index which implicitly maps myWords and myFreqs and this index used is the 
            // index of words present in myWords ArrayList and using this we set the values of myFreqs.
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        // System.out.println("The number of unique words: " + myWords.size());
        // for (int k=0; k < myWords.size(); k++) {
        //     System.out.println( myFreqs.get(k) + "\t" +  myWords.get(k));
        // }
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}