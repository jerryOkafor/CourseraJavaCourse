/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 20/09/2021
 */
public class GenreFilter implements Filter {
    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
