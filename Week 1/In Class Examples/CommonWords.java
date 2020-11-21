import edu.duke.*;

public class CommonWords {
    public String[] getCommon() {
        String[] common = new String[20];
        FileResource fr = new FileResource("data\\common.txt");
        int index = 0;
        for(String word: fr.words()) {
            common[index] = word;
            index += 1;
        }
        return common;
    }

    public int indexOf(String word, String[] list) {
        for(int k=0; k < list.length; k++) {
            if(list[k].equals(word)) {
                return k;
            }
        }
        return -1;
    }

    public void countWords(FileResource fr, String[] common, int[] counts) {
        for(String s: fr.words()){
            s = s.toLowerCase();
            int index = indexOf(s, common);
            if (index != -1) {
                counts[index] += 1;
            }
        }
    }

    public void countShakespeare(){
		//String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
			//          "likeit.txt", "macbeth.txt", "romeo.txt"};
	    String[] plays = {"small.txt"};
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for(int k=0; k < plays.length; k++){
			FileResource resource = new FileResource("data\\" + plays[k]);
			countWords(resource,common,counts);
			System.out.println("done with " + plays[k]);
		}
		
		for(int k=0; k < common.length; k++){
			System.out.println(common[k] + "\t" + counts[k]);
		}
	}

}
