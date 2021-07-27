import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry> {
    private final Location fromWhere;

    DistanceComparator(Location where) {
        this.fromWhere = where;
    }

    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        double dist1 = o1.getLocation().distanceTo(fromWhere);
        double dist2 = o2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);

    }
}
