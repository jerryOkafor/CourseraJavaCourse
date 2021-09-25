import java.util.ArrayList;

/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 19/09/2021
 */
public interface Rater {
    void addRating(String item, double rating);

    boolean hasRating(String item);

    String getID();

    double getRating(String item);

    int numRatings();

    ArrayList<String> getItemsRated();
}
