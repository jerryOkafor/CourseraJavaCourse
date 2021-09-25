import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 19/09/2021
 */
public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
//        SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");

        int minimalRatters = 3;
        System.out.println("Total Movies: " + secondRatings.getMovieSize());
        System.out.println("Total Ratings: " + secondRatings.getRaterSize());

        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minimalRatters);
        Collections.sort(averageRatings);

        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }

        System.out.println(averageRatings.size() + " has " + minimalRatters);
    }

    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
//        SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
//        SecondRatings secondRatings = new SecondRatings("m1.csv", "r1.csv");

//        String movieTitle = "The Godfather";
//        String movieTitle = "No Country for Old Men";
//        String movieTitle = "Inside Llewyn Davis";

//        String movieTitle = "The Maze Runner";
//        String movieTitle = "Moneyball";
        String movieTitle = "Vacation";

        String movieId = secondRatings.getID(movieTitle);

        double rating = secondRatings.getAverageByID(movieId, 3);
        System.out.println("\"" + movieTitle + "\" " + rating);
    }

    public void testMovieCountByRating() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        Map<String, Integer> movieAndCount = secondRatings.moviesCountByRating();

        int ratingCount = 50;
        int count = 0;

        for (String key : movieAndCount.keySet()) {
            if (movieAndCount.get(key) >= ratingCount) {
                count++;
            }
        }


        for (String key : movieAndCount.keySet()) {
            if (movieAndCount.get(key) >= 12) {
                System.out.println(secondRatings.getTitle(key) + " " + movieAndCount.get(key));
            }
        }

        System.out.println(count + " movies has rating count of " + ratingCount);

    }

    public static void main(String[] args) {
        MovieRunnerAverage mva = new MovieRunnerAverage();
//        mva.printAverageRatings();
//        mva.getAverageRatingOneMovie();
        mva.testMovieCountByRating();
    }
}
