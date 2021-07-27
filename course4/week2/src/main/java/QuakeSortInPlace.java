
/**
 * Write a description of class QuakeSortInPlace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class QuakeSortInPlace {
    private final EarthQuakeParser parser;

    public QuakeSortInPlace() {
        parser = new EarthQuakeParser();
    }

    private <T> void swapElements(ArrayList<T> in, int from, int to) {
        T t1 = in.get(from);
        T t2 = in.get(to);
        in.set(to, t1);
        in.set(from, t2);
    }

    private int indexOfSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    private void sortByMagnitude(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); i++) {
            int minIdx = indexOfSmallestMagnitude(in, i);
            swapElements(in, i, minIdx);
            System.out.println("Printing array after pass : " + i);
            printList(in);
        }

    }

    private int getIndexOfLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int largestDepth = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(largestDepth).getDepth()) {
                largestDepth = i;
            }
        }
        return largestDepth;
    }

    private void sortByLargestDepth(ArrayList<QuakeEntry> in) {
//        for (int i = 0; i < in.size(); i++) {
        for (int i = 0; i < 70; i++) {
            int minIdx = getIndexOfLargestDepth(in, i);
            swapElements(in, i, minIdx);
        }
    }

    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 1; i < quakeData.size() - numSorted; i++) {
            int currIndex = i - 1;
            int nextIndex = i;

            QuakeEntry currEntry = quakeData.get(currIndex);
            QuakeEntry nextEntry = quakeData.get(nextIndex);

            double currMag = currEntry.getMagnitude();
            double nexMag = nextEntry.getMagnitude();

            if (!(currMag < nexMag)) {
                swapElements(quakeData, currIndex, nextIndex);
            }


        }

    }

    private void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int N = in.size();
        for (int i = 0; i < N - 1; i++) {
//            printList(in);
            onePassBubbleSort(in, i);
            System.out.println("Printing  Quakes after " + (i) + " Pass");
//            printList(in);
        }
    }

    public <T> void printList(ArrayList<T> items) {
        for (T t : items) {
            System.out.println(t);
        }
        System.out.println("\n");
    }

    public void testSort() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = QuakeSortTester.dataDir + "/data/earthQuakeDataWeekDec6sample1.atom";
//        String source = QuakeSortTester.dataDir + "/data/nov20quakedatasmall.atom";
        //String source = QuakeSortTester.dataDir+"/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        sortByMagnitude(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public void testSortByLargestDepth() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = QuakeSortTester.dataDir + "/data/earthQuakeDataDec6sample2.atom";
//        String source = QuakeSortTester.dataDir + "/data/earthQuakeDataDec6sample1.atom";
//        String source = QuakeSortTester.dataDir + "/data/nov20quakedatasmall.atom";
        //String source = QuakeSortTester.dataDir+"/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        sortByLargestDepth(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void testSortByMagnitudeWithBubbleSort() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = QuakeSortTester.dataDir + "/data/earthQuakeDataWeekDec6sample1.atom";
//        String source = QuakeSortTester.dataDir + "/data/earthquakeDataSampleSix2.atom";
//        String source = QuakeSortTester.dataDir + "/data/nov20quakedatasmall.atom";
        //String source = QuakeSortTester.dataDir+"/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        sortByMagnitudeWithBubbleSortWithCheck(list);
        System.out.println();
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = QuakeSortTester.dataDir + "/data/nov20quakedata.atom";
        String source = QuakeSortTester.dataDir + "/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }
}
