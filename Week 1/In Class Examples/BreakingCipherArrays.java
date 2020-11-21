public class BreakingCipherArrays {
    public void testFingerPrint(String s) {
        int[] counter = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int k=0; k < s.length(); k++) {
            char ch = s.charAt(k);
            int index = alphabet.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counter[index] += 1;
            }
        }
        for (int k=0; k < counter.length; k++){
            System.out.println(alphabet.charAt(k) + "\t" + counter[k]);
        }
    }

    public static void main(String[] args) {
        BreakingCipherArrays obj = new BreakingCipherArrays();
        obj.testFingerPrint("Yash Jain");
    }
}