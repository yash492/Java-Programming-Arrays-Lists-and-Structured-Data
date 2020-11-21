import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> uniqueWords;
    private ArrayList<String> usedCategories;
	private int count;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        uniqueWords = new ArrayList<String> ();
        usedCategories = new ArrayList<String> ();
	}
	
	public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
        myRandom = new Random();
        uniqueWords = new ArrayList<String> ();
        usedCategories = new ArrayList<String> ();
	}
	
	private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", 
                            "timeframe", "verb", "fruit"};
		for(String category: categories) {
            ArrayList<String> categoryList = readIt(source + "/" + category + ".txt");
            myMap.put(category, categoryList);
        }
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
        
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
		return "**UNKNOWN**";
    }
    
    private void getUsedCategories(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first != -1 && last != -1){
            String label = w.substring(first+1,last);
            if (! usedCategories.contains(label)) {
                usedCategories.add(label);
            }
		}
    }
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
		String sub = getSubstitute(label);
		while (true) {
			if (uniqueWords.indexOf(sub) == -1) {
				uniqueWords.add(sub);
				break;
			}
			else {
				sub = getSubstitute(w.substring(first+1,last));
			}
		}
		count = count + 1;
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
                getUsedCategories(word);
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
                getUsedCategories(word);
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
    }
    
    private void totalWordsInMap() {
        for(String word: myMap.keySet()) {
            int size = myMap.get(word).size();
            System.out.println(word + " " + size);
        }
    }

    private void totalWordsConsidered() {
        for(String word: usedCategories) {
			if (myMap.containsKey(word)) {
				int size = myMap.get(word).size();
				System.out.println(word + " " + size);
			}
        }
    }

	
	public void makeStory(){
        uniqueWords.clear();
        
		String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println();

        System.out.println("Total number of words replaced is " + count);

        // prints out the total number of words that were possible to pick from.
		totalWordsInMap();
		System.out.println();
		 
		// prints out  the total number of words in the ArrayLists of the categories 
		// that were used for a particular GladLib
        totalWordsConsidered();
	}
	
	public static void main(String[] args) {
		GladLibMap gl = new GladLibMap();
		gl.makeStory();
	}

}
