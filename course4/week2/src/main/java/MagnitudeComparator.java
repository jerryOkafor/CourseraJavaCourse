import java.util.Comparator;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        return Double.compare(o1.getMagnitude(), o2.getMagnitude());
    }
}
