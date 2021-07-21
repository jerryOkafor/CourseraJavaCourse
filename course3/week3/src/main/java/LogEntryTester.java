import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogEntryTester {
    private final String dir = "/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course3/data/week3";

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method. Done
        String filename = dir + "/short-test_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        la.printAll();
    }

    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(dir + "/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");

    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        //String filename = "weblog-short_log";
        String filename = dir + "/weblog2_log";
        la.readFile(filename);
        String date1 = "Sep 27";
        //String date2 = "Sep 30";
        String date2 = "Mar 24";
        ArrayList<String> uniqIPDate1 = la.uniqueIPVisitsOnDay(date1);
        ArrayList<String> uniqIPDate2 = la.uniqueIPVisitsOnDay(date2);
        System.out.println("Unique ip visits on " + date1 + " is " + uniqIPDate1.size());
        System.out.println("Unique ip visits on " + date2 + " is " + uniqIPDate2.size());
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        String filename =  dir + "/weblog2_log";
        la.readFile(filename);
        //System.out.println("number of unique ips in range (400, 499) is " + la.countUniqueIPsInRange(200,299));
        System.out.println("number of unique ips in range (200,299) is " + la.countUniqueIPsInRange(200, 299));
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = dir + "/weblog1_log";
        la.readFile(filename);
        la.printAllHigherThanNum(400);
    }


    public void mostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = dir + "/weblog2_log";
        la.readFile(filename);
        HashMap<String, Integer> counter = la.countVisitsPerIP();
        int mostNumberVisitsByIP = la.mostNumberVisitsByIP(counter);
        ArrayList<String> ipMostVisited = la.iPsMostVisits(counter);
        HashMap<String,ArrayList<String>> ipsForDay  = la.iPsForDays();
        String dayWithMostIp = la.dayWithMostIPVisits(ipsForDay);

        System.out.println("Most number visited by IP: " + mostNumberVisitsByIP);
        System.out.println("Most visited  IP: " + ipMostVisited);
        System.out.println("Ips for Day: " + dayWithMostIp);
        System.out.println("Ips with most visit on day: " + la.iPsWithMostVisitsOnDay(ipsForDay,"Sep 30"));
    }

    public static void main(String[] args) {
        LogEntryTester tester = new LogEntryTester();
        tester.testLogEntry();
//        tester.testPrintAllHigherThanNum();
//        tester.testUniqueIPVisitsOnDay();
        tester.testCountUniqueIPsInRange();
//        tester.mostNumberVisitsByIP();
//        tester.testUniqIP();
    }
}
