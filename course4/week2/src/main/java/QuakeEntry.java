public class QuakeEntry implements Comparable<QuakeEntry> {

    private final Location myLocation;
    private final String title;
    private final double depth;
    private final double magnitude;

    public QuakeEntry(double lat, double lon, double mag,
                      String t, double d) {
        myLocation = new Location(lat, lon);

        magnitude = mag;
        title = t;
        depth = d;
    }

    public Location getLocation() {
        return myLocation;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getInfo() {
        return title;
    }

    public double getDepth() {
        return depth;
    }

    @Override
    public int compareTo(QuakeEntry loc) {
        if (Double.compare(magnitude, loc.getMagnitude()) == 0) {
            double depth1 = depth;
            double depth2 = loc.getDepth();
            return Double.compare(depth1, depth2);
        }
        return Double.compare(magnitude, loc.getMagnitude());
    }

    public String toString() {
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(), myLocation.getLongitude(), magnitude, depth, title);
    }

}