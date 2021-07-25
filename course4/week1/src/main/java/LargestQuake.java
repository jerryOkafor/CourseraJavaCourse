import java.util.ArrayList;

public class LargestQuake {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
//        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
        int num = 50;
        ArrayList<QuakeEntry> largest = getLargest(list, num);

        System.out.println("printing the " + num + " largest earthquakes from " + source + "...");
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }

        System.out.println("Found " + largest.size() + " quakes that match that criteria");
        System.out.println("20th Quake " + largest.get(19));
        System.out.println("20th Quake " + largest.get(49));

    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int idxMax = 0;
        double max = -999.9;
        for (int i = 0; i < data.size(); i++) {
            QuakeEntry currEntry = data.get(i);
            double currMag = currEntry.getMagnitude();
            if (currMag > max) {
                idxMax = i;
                max = currMag;
            }

        }
        return idxMax;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> output = new ArrayList<>();
        ArrayList<QuakeEntry> copy = quakeData;

        for (int i = 0; i < howMany; i++) {
            int currMax = indexOfLargest(copy);
            QuakeEntry currMaxEntry = copy.get(currMax);
            output.add(currMaxEntry);
            copy.remove(currMaxEntry);
        }
        return output;
    }
}
