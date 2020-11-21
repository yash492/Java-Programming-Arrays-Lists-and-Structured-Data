import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> count;

    public CharactersInPlay() {
        characters = new ArrayList<String> ();
        count = new ArrayList<Integer> ();
    }

    private void update(String person) {
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            count.add(1);
        }

        else {
            int value = count.get(index);
            count.set(index, value + 1);
        }
    }

    public void findAllCharacters () {
        FileResource fr = new FileResource();
        for(String line: fr.lines()) {
            int indexPeriod = line.indexOf('.');
            if (indexPeriod == -1) {
                continue;
            }
            String name = line.substring(0, indexPeriod);
            update(name);
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int k=0; k < count.size(); k++) {
            if (num1 <= count.get(k) && count.get(k) <= num2) {
                System.out.println(characters.get(k) + " " + count.get(k));
            }
        }
    }
        
    

    public int maxIndex(ArrayList<Integer> count) {
        int maxValue = -1;
        int maxIndex = -1;
        for (int k=0; k<count.size(); k++) {
            if (maxValue == -1 || count.get(k) > maxValue) {
                maxValue = count.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void tester() {
        findAllCharacters();
        charactersWithNumParts(10, 15);
        System.out.println("Max count of speaking parts: " + count.get(maxIndex(count)) + " " + maxIndex(count));
        System.out.println("Name of character with most speaking parts: " + characters.get(maxIndex(count)));
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }

}
