public class MinMagFilter implements Filter {
    private final double magMin;

    public MinMagFilter(double min) {
        magMin = min;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

    public String getName() {
        return "MinMagnitude";
    }

}
