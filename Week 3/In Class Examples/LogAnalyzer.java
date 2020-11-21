import edu.duke.*;
import java.util.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer () {
        records = new ArrayList<LogEntry> ();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line: fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public HashMap<String, Integer> countVisitPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le: records) {
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

}
