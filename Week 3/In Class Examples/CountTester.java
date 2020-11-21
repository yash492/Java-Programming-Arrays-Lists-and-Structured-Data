import java.util.HashMap;

public class CountTester {
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitPerIP();
        System.out.println(counts);
    }

    public static void main(String[] args) {
        CountTester ct = new CountTester();
        ct.testCounts();
    }
}