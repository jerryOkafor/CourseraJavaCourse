import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private final ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    //we'll see .satisfies in a second
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder output = new StringBuilder();
        for (Filter currFilter : filters) {
            String filterName = currFilter.getName();
            output.append(filterName).append(" ");
        }
        return output.toString();
    }
}
