import java.util.ArrayList;

public class EarthQuakeClient2 {
    private final EarthQuakeParser parser;

    public EarthQuakeClient2() {
        parser = new EarthQuakeParser();
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        //Filter f = new MinMagFilter(4.0);
        //ArrayList<QuakeEntry> m7  = filter(list, f);

        Filter f = new MagnitudeFilter(4.0, 5.0);
        ArrayList<QuakeEntry> magQuakes = filter(list, f);

        Filter f2 = new DepthFilter(-12_000.0,-35_000.0);
        ArrayList<QuakeEntry> twoFilterQuakes = filter(magQuakes, f2);

//        Location japan = new Location(35.42, 139.43);
//        Filter dfilter = new DistanceFilter(10000 * 1000, japan);
//        ArrayList<QuakeEntry> dQuakes = filter(list, dfilter);
//
//        Filter lPhrasefilter = new PhraseFilter("end", "Japan");
//        ArrayList<QuakeEntry> twoFilterQuakes = filter(dQuakes, lPhrasefilter);

        for (QuakeEntry qe : twoFilterQuakes) {
            System.out.println(qe);
        }
        System.out.println("Earthquakes found: " + twoFilterQuakes.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
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

    public void testMatchAllFilter() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-10000.0, -100000.0));
        maf.addFilter(new PhraseFilter("any", "a"));

        ArrayList<QuakeEntry> manyFilters = filter(list, maf);

        for (QuakeEntry qe : manyFilters) {
            System.out.println(qe);
        }

        System.out.println("Earthquakes found: " + manyFilters.size());
        System.out.println("Filters used are: " + maf.getName());

    }

    public void testMatchAllFilter2() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedatasmall.atom";
        String source = EarthQuakeClientTester.dataDir + "/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        Location TulsaOklahoma = new Location(36.1314, -95.9372);
//        Location BillundDenmark = new Location(55.7308, 9.1153);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(10000 * 1000, TulsaOklahoma));
        maf.addFilter(new PhraseFilter("any", "Ca"));

        ArrayList<QuakeEntry> manyFilters = filter(list, maf);

        for (QuakeEntry qe : manyFilters) {
            System.out.println(qe);
        }

        System.out.println("Earthquakes found: " + manyFilters.size());

    }
}
