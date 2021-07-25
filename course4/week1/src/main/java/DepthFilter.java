public class DepthFilter implements Filter {
    private final double minDepth;
    private final double maxDepth;

    public DepthFilter(double minD, double maxD) {
        minDepth = minD;
        maxDepth = maxD;
    }

    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() <= minDepth && qe.getDepth() >= maxDepth);
    }

    public String getName() {
        return "Depth";
    }
}
