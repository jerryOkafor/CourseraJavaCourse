/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 20/09/2021
 */
public class DirectorsFilter implements Filter {
    private String director;

    public DirectorsFilter(String director) {
        this.director = director;
    }

    @Override
    public boolean satisfies(String id) {
        for (String dir : director.split(",")) {
            if (MovieDatabase.getDirector(id).contains(dir)) {
                return true;
            }
        }
        return false;
    }
}
