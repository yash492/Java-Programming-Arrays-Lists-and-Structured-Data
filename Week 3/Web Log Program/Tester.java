
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = 400;
        la.printAllHigherThanNum(num);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String someday = "Sep 24";
        for (String ipAddress: la.uniqueIPVisitsOnDay(someday)) {
            System.out.println(ipAddress);
        }
        System.out.println(la.uniqueIPVisitsOnDay(someday).size());
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        System.out.println(la.countUniqueIPsInRange(low, high));
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer>  counts = la.countVisitsPerIP();
        for (String key: counts.keySet()) {
            System.out.println(counts.get(key));
        }
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer>  counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer>  counts = la.countVisitsPerIP();
        ArrayList <String> mostIpVisits = la.iPsMostVisits(counts);
        for (String visit: mostIpVisits) {
            System.out.println(visit);
        }
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        for (String key: ipDays.keySet()) {
            ArrayList <String> value = ipDays.get(key);
            System.out.println(key);
            for (String v: value) {
                System.out.println(v);
            }
            System.out.println();
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        String maxDay = la.dayWithMostIPVisits(ipDays);
        System.out.println(maxDay);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        String someday = "Sep 29";
        ArrayList<String> mostVisits = la.iPsWithMostVisitsOnDay(ipDays, someday);
        for (String mostVisit: mostVisits) {
            System.out.println(mostVisit);
        }
    }


 
    public static void main(String[] args) {
        Tester t = new Tester();
        // t.testLogAnalyzer();
        // t.testUniqueIP();
        // t.testPrintAllHigherThanNum();
         t.testUniqueIPVisitsOnDay();
        // t.testCountUniqueIPsInRange();
        // t.testCountVisitsPerIP();
        // t.testMostNumberVisitsByIP();
        // t.testIPsMostVisits();
        // t.testIPsForDays();
        // t.testDayWithMostIPVisits();
        // t.testIPsWithMostVisitsOnDay();
    }
}
