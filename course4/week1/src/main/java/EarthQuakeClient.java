
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class EarthQuakeClient {
    private final EarthQuakeParser parser;

    public EarthQuakeClient() {
        parser = new EarthQuakeParser();
    }

    public ArrayList<QuakeEntry> filterByMagnitude(@NotNull ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(@NotNull ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(@NotNull ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(@NotNull ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            switch (where) {
                case "start":
                    if (qe.getInfo().startsWith(phrase)) {
                        answer.add(qe);
                    }
                    break;
                case "any":
                    if (qe.getInfo().contains(phrase)) {
                        answer.add(qe);
                    }

                    break;
                case "end":
                    if (qe.getInfo().endsWith(phrase)) {
                        answer.add(qe);
                    }
                    break;
            }

        }
        return answer;
    }

    public void bigQuakes() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        Collections.sort(listBig);
        Collections.reverse(listBig);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe);
        }

        System.out.println("Found " + listBig.size() + " quakes that match that criteria");
        if (listBig.size() > 50) {
            System.out.println("50 th Quake " + listBig.get(49));
        }
    }

    public void closeToMe() {
//        String source = Tester.dataDir + "/data/nov20quakedata.atom";
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        //Durham, NC
        //Location city = new Location(35.988, -78.907);

        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000 * 1000 /*KM*/, city);
        for (QuakeEntry entry : close) {
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters / 1000 + " " + entry.getInfo());
        }

        System.out.println("Found " + close.size() + " quakes that match that criteria");
    }

    public void quakesOfDepth() {
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
//        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

//        double minDepth = -10000.0;
        double minDepth = -4000.0;
//        double maxDepth = -5000.0;
        double maxDepth = -2000.0;

        System.out.printf("Find quakes with depth between %.2f and %.2f", minDepth, maxDepth);
        System.out.println();

        ArrayList<QuakeEntry> close = filterByDepth(list, minDepth, maxDepth);
        for (QuakeEntry entry : close) {
            System.out.println(entry);
        }

        System.out.println("Found " + close.size() + " quakes that match that criteria");
    }


    public void quakesByPhrase() {
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
//        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

//        String where = "end";
//        String where = "start";
        String where = "any";

//        String phrase = "California";
//        String phrase = "Explosion";
//        String phrase = "Creek";
//        String phrase = "Quarry Blast";
//        String phrase = "Alaska";
        String phrase = "Can";

        System.out.printf("Find quakes with position %s and phrase %s", where, phrase);
        System.out.println();

        ArrayList<QuakeEntry> close = filterByPhrase(list, where, phrase);
        for (QuakeEntry entry : close) {
            System.out.println(entry);
        }

        System.out.println("Found " + close.size() + " quakes that match that criteria");
    }


    public void dumpCSV(@NotNull ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

}
