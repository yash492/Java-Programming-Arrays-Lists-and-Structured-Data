import java.util.Random;

public class DiceRolling {
    public void simulate(int rolls) {
        Random rand = new Random();
        int[] counts = new int[14];
        for (int k=0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1+d2] +=1;
        }

        for (int k=2; k < 13; k++) {
            System.out.println(k + "'s:\t" + counts[k] + "\tPercentage of number/rolls: " + (double) counts[k]/rolls * 100);
        }
    }

    public static void main(String[] args) {
        DiceRolling obj = new DiceRolling();
        obj.simulate(10000);
    }
}
