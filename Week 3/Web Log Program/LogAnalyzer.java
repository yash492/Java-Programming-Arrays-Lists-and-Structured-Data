
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Yash Jain
 * @version 17 November 2020
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry> ();
         }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line: fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIPs() {
        ArrayList <String> uniqueIps = new ArrayList <String> ();
        for(LogEntry le: records) {
        String ipAddress = le.getIpAddress();
        if (! uniqueIps.contains(ipAddress)) {
                uniqueIps.add(ipAddress);
            }
        }
        return uniqueIps.size();
     }

     public void printAllHigherThanNum (int num) {
         for (LogEntry le: records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList <String> uniqueIPVisitsOnDay (String someday) {
        ArrayList<String> uniqueIPVisits = new ArrayList<String> ();
        for(LogEntry le: records) {
            String date = le.getAccessTime().toString();
            if (date.contains(someday)) {
                if (! uniqueIPVisits.contains(le.getIpAddress())) {
                    uniqueIPVisits.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPVisits;
     }

     public int countUniqueIPsInRange (int low, int high) {
        ArrayList<String> uniqueIPVisitsInRange = new ArrayList<String> ();
        for (LogEntry le: records) {
            int statusCode = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if ( low <= statusCode && statusCode <= high ) {
                if (! uniqueIPVisitsInRange.contains(ipAddress)) {
                    uniqueIPVisitsInRange.add(ipAddress);
                }
            }
        }
        return uniqueIPVisitsInRange.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (! counts.containsKey(ip)) {
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    private int max(int num1, int num2) {
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }


    public int mostNumberVisitsByIP (HashMap<String, Integer> counts) {
        int maxValue = -1;
        for(int visit: counts.values()) {
            maxValue = max(maxValue, visit);
        }
        return maxValue;
    }

    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
        int maxValue = mostNumberVisitsByIP(counts);
        ArrayList<String> mostIpVisits = new ArrayList<String> ();
        for(String key: counts.keySet()) {
            if (counts.get(key) == maxValue){
                mostIpVisits.add(key);
            }
        }
        return mostIpVisits;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsDays = new HashMap<String, ArrayList<String>>();
        for(LogEntry le: records) {
            String day = le.getAccessTime().toString().substring(4, 10);
            ArrayList <String> ipList = new ArrayList <String> ();
            if (! iPsDays.containsKey(day)) {
                ipList.add(le.getIpAddress());
                iPsDays.put(day, ipList);
            } 
            else {
                ArrayList<String> value = iPsDays.get(day);
                value.add(le.getIpAddress());
                iPsDays.put(day, value);
            }
        }
        return iPsDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsDays) {
        int maxValue = -1;
        String maxDay = null;
        for(LogEntry le: records) {
            String day = le.getAccessTime().toString().substring(4, 10);
            int size = iPsDays.get(day).size();
            if (maxValue == -1 || size > maxValue) {
                maxValue = size;
                maxDay = day;
            }
        }
        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsDays, String someday) {
        ArrayList<String> iPList = iPsDays.get(someday);
        HashMap<String, Integer> counts = new HashMap<String, Integer> ();
        for(String ip: iPList) {
            if (! counts.containsKey(ip)) {
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return iPsMostVisits(counts);

    }

 }
     
