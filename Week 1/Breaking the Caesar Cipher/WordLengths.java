import edu.duke.*;

public class WordLengths {
    public void countWordLengths (URLResource url, int[] counts) {
        for(String word: url.words()) {
            char firstLetter = word.charAt(0);
            char lastLetter = word.charAt(word.length() - 1);

            if (Character.isLetter(firstLetter) == false && Character.isLetter(lastLetter) == false) {
                getLength(word, counts, 2);
            }

            else if (Character.isLetter(firstLetter) && Character.isLetter(lastLetter)) {
                getLength(word, counts, 0);
            }

            else {
                getLength(word, counts, 1);
            }
        }

         for(int k = 0; k < counts.length; k++) {
             if (counts[k] == 0) {
                 continue;
             }
             if (counts[k] == 1){
                 System.out.println(counts[k] + " word of length " + k);
             }
             else {
                 System.out.println(counts[k] + " words of length " + k);
             }
         }
    }

    public int indexOfMax(int[] values) {
        int max = -1;
        int maxIndex = -1;
        for (int k = 0; k < values.length; k++) {
            if (max == -1 || values[k] > max) {
                max = values[k];
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void getLength (String word, int[] counts, int sub) {
        if (word.length() - sub >= 30) {
            counts[31] += 1;
        }
        else {
            counts[word.length() - sub] += 1;
        }
    }

    public void testCountWordLengths() {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//java/manywords.txt");
        //FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(url, counts);
        int maxIndex = indexOfMax(counts);
        System.out.println(maxIndex);
    }

    public static void main(String[] args) {
        WordLengths obj = new WordLengths();
        obj.testCountWordLengths();
    }
}