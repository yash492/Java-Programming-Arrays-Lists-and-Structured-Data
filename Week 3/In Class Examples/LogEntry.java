import java.util.*;
public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturnrd() {
        return bytesReturned;
    }

    // A toString() is an in-built method in Java that returns the value given to it in string format. 
    // Hence, any object that this method is applied on, will then be returned as a string object.
    public String toString() {
        return ipAddress + " " + accessTime + " " + request 
        + " "  + statusCode + " " + bytesReturned;

    }

}