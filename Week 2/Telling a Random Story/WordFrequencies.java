import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String> ();
        myFreqs = new ArrayList<Integer> ();
    }
    
    /*
    public int getIndex(ArrayList<String>myWords, String word) {
        for (int k=0; k<myWords.size(); k++) {
            if (myWords.get(k).equals(word)) {
                return k;
            }
        }
        return -1;
    }*/
    

    public int findIndexOfMax(ArrayList<Integer> myFreqs) {
        int maxIndex = -1;
        int max = -1;
        for (int k=0; k < myFreqs.size(); k++) {
            if (max == -1 || myFreqs.get(k) > max) {
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word: fr.words()) {
            word = word.toLowerCase();
            //int index = getIndex(myWords, word);
            int index = myWords.indexOf(word);
            if(index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }

    public void tester() {
        findUnique();
        int maxIndex = findIndexOfMax(myFreqs);
        System.out.println(maxIndex);
        System.out.println("Number of unique words: " + myFreqs.size());
        // for(int k=0; k < myWords.size(); k++) {
        //     System.out.println(myFreqs.get(k) + " " + myWords.get(k));
        // }
        System.out.println("The word that occurs most often and its count are '" + myWords.get(maxIndex) + "' " + myFreqs.get(maxIndex));
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }

}