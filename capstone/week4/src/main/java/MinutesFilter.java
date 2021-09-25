/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 20/09/2021
 */
public class MinutesFilter implements Filter {
    private int minMinutes;
    private int maxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        int movieMin = MovieDatabase.getMinutes(id);
        return movieMin >= minMinutes && movieMin <= maxMinutes;
    }
}
