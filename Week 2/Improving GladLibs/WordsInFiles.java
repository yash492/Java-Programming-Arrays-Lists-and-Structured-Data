import java.io.*;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
    // Key is word , value is file name
    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles () {
        map = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        for(String word: fr.words()) {
            if (! map.containsKey(word)) {
                ArrayList<String> fileName = new ArrayList<String>();
                fileName.add(f.getName());
                map.put(word, fileName);
            }
            else {
                ArrayList<String> value = map.get(word);
                if (! value.contains(f.getName())) {
                    value.add(f.getName());
                    map.put(word, value);
                }
            }
        }
    
    }

    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        int max = -1;
        for (String word: map.keySet()) {
            //map.get(word) returns ArrayList<String>
            int size = map.get(word).size();
            if(max == -1 || size > max){
                max = size;
            }
        }
        return max;
    } 

    private ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> list = new ArrayList<String> ();
        for(String word: map.keySet()) {
            int size = map.get(word).size();
            if (size == number) {
                list.add(word);
            }
        }
        return list;
    }

    private void printFilesIn(String word) {
        if (map.containsKey(word)) {
            for(String fileName: map.get(word)) {
                System.out.println(fileName);
            }
        }
    }

    public void tester() {
        buildWordFileMap();
        // determines the maximum number of files any word is in
        System.out.println(maxNumber());
        System.out.println();

        // determines all the words that are in the given number of files
        int number = 4;
        int count = 0;
        for(String word: wordsInNumFiles(number)) {
            //System.out.println(word);
            count += 1;
        }
        System.out.println(count);
        System.out.println();
        
        // for each such word, prints the filenames of the files it is in
        String word = "tree";
        printFilesIn(word);
    }

    public static void main(String[] args) {
        WordsInFiles wf = new WordsInFiles();
        wf.tester();
    }
} 