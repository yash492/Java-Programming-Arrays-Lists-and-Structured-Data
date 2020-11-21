import edu.duke.*;

public class WordsWithArrays {
    StorageResource myWords;

    public WordsWithArrays () {
        myWords = new StorageResource();
    }

    public void readWords() {
        myWords.clear();
        FileResource fr = new FileResource();
        for(String word: fr.words()) {
            myWords.add(word.toLowerCase());
        }
    }

    public boolean contains(String[] list, String word, int number) {
        for(int k=0; k < number; k++) {
            if(list[k].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfUniqueWords() {
        int numStored = 0;
        String[] words = new String[myWords.size()];
        for (String s: myWords.data()){
            if (!contains(words, s, numStored)) {
                words[numStored] = s;
                numStored++;
            }
        }
        return numStored;
    }

    public void tester() {
        readWords();
        System.out.println("number of unique words: " + myWords.size());
    }

    public static void main(String[] args) {
        WordsWithArrays wwa = new WordsWithArrays();
        wwa.tester();
    }

}