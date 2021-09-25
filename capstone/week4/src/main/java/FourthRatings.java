/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 19/09/2021
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FourthRatings {


    public FourthRatings() {

    }

    public double getAverageByID(String movieId, int minimalRaters) {

        double totalRating = 0;
        int totalRatingsCount = 0;

        RaterDatabase.initialize("ratings_small.csv");
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
        RaterDatabase.initialize("ratings_small.csv");
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();

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

    /**
     * This method returns a double value with the affinity between two Raters. The higher the number,
     * the higher the affinity as well base on Collaboratively Filtering/Recommendations
     */
    private double dotProduct(Rater me, Rater r) {
        double result= 0.0;
        for (String movieId : me.getItemsRated()) {
            if (r.hasRating(movieId)) {
                result += (me.getRating(movieId) - 5) * (r.getRating(movieId) - 5);
            }
        }
        return result;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarRatings = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        //get the do product for all the remaining Raters
        for (Rater rater : RaterDatabase.getRaters()) {
            if (!rater.getID().equals(id) && dotProduct(rater,me) >= 0.0) {
                similarRatings.add(new Rating(rater.getID(), dotProduct(rater,me)));
            }
        }

        similarRatings.sort(Collections.reverseOrder());

        return similarRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    /**
     * This method should return an ArrayList of type Rating, of movies and their weighted average ratings
     * using only the top numSimilarRaters with positive ratings and including only those movies that have
     * at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall)
     *
     * @param id               the id of the Rater
     * @param numSimilarRaters determines how many items in the reverse sorted similarities array we shall consider,
     * @param minimalRaters    determines the minimal number raters we shall consider for a given movie
     * @param filter           the applied filter to the MovieDatabase
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter) {
        ArrayList<Rating> raterSimilarities = getSimilarities(id);
        ArrayList<Rating> similarRatings = new ArrayList<>();

        for (String movieId : MovieDatabase.filterBy(filter)) {
            //Do magic
            int numRatings = 0;
            double weightedTotal = 0;

            //select similar rating for all values of numSimilarRaters
            for (int index = 0; index < numSimilarRaters; index++) {
                //pick the matching similar rating for the given index.
                Rating similarRating = raterSimilarities.get(index);

                double closeness = similarRating.getValue();
                String raterId = similarRating.getItem();
                Rater rater = RaterDatabase.getRater(raterId);

                if (rater.hasRating(movieId)) {
                    numRatings++;
                    double movieRating = rater.getRating(movieId);
                    weightedTotal += closeness * movieRating;
                }
            }

            if (numRatings >= minimalRaters) {
                similarRatings.add(new Rating(movieId, weightedTotal / numRatings));
            }
        }

        similarRatings.sort(Collections.reverseOrder());
        return similarRatings;

    }
}