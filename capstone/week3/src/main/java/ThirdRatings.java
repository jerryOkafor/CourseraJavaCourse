/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 19/09/2021
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFileName) {
        FirstRatings firstRating = new FirstRatings();
        this.myRaters = firstRating.loadRaters(ratingsFileName);

    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String movieId, int minimalRaters) {

        double totalRating = 0;
        int totalRatingsCount = 0;

        //loop through rater class
        for (Rater rater : myRaters) {
            if (rater.getItemsRated().contains(movieId)) {
                for (String item : rater.getItemsRated()) {
                    if (item.equals(movieId)) {
                        double rating = rater.getRating(item);
                        totalRating += rating;
                        totalRatingsCount++;
                    }
                }
            }
        }
        if (totalRatingsCount >= minimalRaters) {
            return totalRating / totalRatingsCount;
        } else {
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieId : movies) {
            Movie movie = MovieDatabase.getMovie(movieId);
            double averageRating = getAverageByID(movie.getID(), minimalRaters);

            if (averageRating > 0.0) {
                ratings.add(new Rating(movie.getID(), averageRating));
            }
        }
        return ratings;
    }


    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieId : movies) {
            Movie movie = MovieDatabase.getMovie(movieId);
            double averageRating = getAverageByID(movie.getID(), minimalRaters);

            if (averageRating > 0.0) {
                ratings.add(new Rating(movie.getID(), averageRating));
            }
        }
        return ratings;
    }


    public Map<String, Integer> moviesCountByRating() {
        Map<String, Integer> moviesRatingCount = new HashMap<>();

        for (Rater rater : myRaters) {
            for (String s : rater.getItemsRated()) {
                if (moviesRatingCount.containsKey(s)) {
                    moviesRatingCount.put(s, moviesRatingCount.get(s) + 1);
                } else {
                    moviesRatingCount.put(s, 1);
                }
            }
        }

        return moviesRatingCount;
    }
}