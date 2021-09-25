import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.*;

/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 18/09/2021
 */
public class FirstRatings {

    public static String DATA_DIR = "/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/capstone/data";

    public ArrayList<Movie> loadMovies(String fileName) {
        FileResource fr = new FileResource(DATA_DIR + "/" + fileName);
        CSVParser csvParser = fr.getCSVParser();

        ArrayList<Movie> movies = new ArrayList<>();

        for (CSVRecord record : csvParser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            String minutesStr = record.get("minutes");
            int minutes = Integer.parseInt(minutesStr);
            String poster = record.get("poster");

            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(movie);
        }

        return movies;
    }

    public void testLoadMovies() {
//        String fileName = "ratedmovies_short.csv";
        String fileName = "ratedmoviesfull.csv";

        ArrayList<Movie> movies = loadMovies(fileName);

        int comedyGenre = 0;
        int greaterThan150 = 0;
        Map<String, Integer> directorsCount = new HashMap<>();

        for (Movie movie : movies) {
//                System.out.print("Movie: " + movie);

            if (movie.getGenres().contains("Comedy")) {
                comedyGenre++;
            }

            if (movie.getMinutes() > 150) {
                greaterThan150++;
            }

//                System.out.println("Directors: " + movie.getDirector());

            for (String director : movie.getDirector().split(",")) {
                int oldCount = directorsCount.getOrDefault(director, 0) + 1;
                directorsCount.put(director, oldCount);
            }

        }

        System.out.println("Movies Count: " + movies.size());
        System.out.println("Comedy Genre Count: " + comedyGenre);
        System.out.println("Greater Than 150 min Count: " + greaterThan150);

//            for (Map.Entry<String, Integer> entry : directorsCount.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }

        maxMovieByDirector(movies);


    }

    public void maxMovieByDirector(ArrayList<Movie> movies) {
        Map<String, Integer> movieDirMap = new HashMap<>();

        for (Movie movie : movies) {
            String directors = movie.getDirector();

            for (String dir : directors.split(",")) {
                //add or update the map
                if (movieDirMap.containsKey(dir)) {
                    movieDirMap.put(dir, movieDirMap.get(dir) + 1);
                } else {
                    movieDirMap.put(dir, 1);
                }
            }
        }


        ArrayList<String> maxDirs = new ArrayList<>();
        int max = 0;
        String maxDir = "";

        for (String s : movieDirMap.keySet()) {
            if (movieDirMap.get(s) > max) {
                max = movieDirMap.get(s);
            }
        }

        for (String s : movieDirMap.keySet()) {
            if (movieDirMap.get(s) == max) {
                maxDirs.add(s);
            }
        }

        System.out.println("Maximum number of movies by any director is: " + max);
        System.out.println("Directors who directed this many movies : " + maxDirs.size() + "\n");
        for (String s : maxDirs) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println();
    }

    public ArrayList<Rater> loadRaters(String fileName) {
        FileResource fr = new FileResource(DATA_DIR + "/" + fileName);
        CSVParser csvParser = fr.getCSVParser();

        ArrayList<Rater> raters = new ArrayList<>();
        Map<String, EfficientRater> ratersMap = new HashMap<>();


        for (CSVRecord record : csvParser) {
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            String rating = record.get("rating");
            String time = record.get("time");

            EfficientRater rater = ratersMap.get(raterId);
            if (rater == null) {
                rater = new EfficientRater(raterId);
            }
            rater.addRating(movieId, Double.parseDouble(rating));

            ratersMap.put(raterId, rater);

        }

        for (Map.Entry<String, EfficientRater> entry : ratersMap.entrySet()) {
            raters.add(entry.getValue());
        }

        return raters;
    }


    public void testLoadRaters() {
//        String fileName = "ratings_short.csv";
        String fileName = "ratings.csv";

        ArrayList<Rater> raters = loadRaters(fileName);

        String raterId = "193";
        int raterRatingCount = 0;

        for (Rater rater : raters) {
//                System.out.println("Rater: " + rater.getID() + " Rating Count: " + rater.numRatings());
            if (rater.getID().equals(raterId)) {
                raterRatingCount += rater.numRatings();
            }

//                for (String ratingItem : rater.getItemsRated()) {
//                    System.out.println("Rating: " + rater.getRating(ratingItem));
//                }
        }

        System.out.println("Rating count for " + raterId + " is : " + numberOfRatings(raters, raterId));
        maxNumberOfRatings(raters);
        System.out.println("Most Movie by Rater: " + mostMovieByRater(raters));
        movieRatings(raters, "1798709");
        movieRated(raters);


    }

    public String mostMovieByRater(ArrayList<Rater> raters) {
        Map<String, Integer> ratersCount = new HashMap<>();

        String raterId = "";
        int maxRating = 0;

        for (Rater rater : raters) {
            if (rater.numRatings() > maxRating) {
                maxRating = rater.numRatings();
                raterId = rater.getID();
            }
        }

        return raterId;
    }

    public int numberOfRatings(ArrayList<Rater> raters, String raterId) {
        int rating = 0;
        for (Rater rater : raters) {
            if (rater.getID().equals(raterId)) {
                rating = rater.numRatings();
                break;

            }
        }
        return rating;
    }

    public void maxNumberOfRatings(ArrayList<Rater> raters) {
        int maxRating = 0;
        int count = 0;

        for (Rater rater : raters) {
            if (rater.numRatings() > maxRating) {
                maxRating = rater.numRatings();
                count = 1;
                continue;
            }

            if (rater.numRatings() == maxRating) {
                count++;
            }
        }

        System.out.println("Max Rating is : " + maxRating + " by " + count + " rater(s)");
    }

    public void movieRatings(ArrayList<Rater> raters, String movieId) {
        int rating = 0;

        for (Rater rater : raters) {
            if (rater.getItemsRated().contains(movieId))
                rating++;
        }

        System.out.println("Rating for : " + movieId + " is " + rating);
    }

    public void movieRated(ArrayList<Rater> raters) {
        Set<String> ratedSets = new HashSet<>();

        for (Rater rater : raters) {
            ratedSets.addAll(rater.getItemsRated());
        }

        System.out.println("Rated Movies count : " + ratedSets.size());
    }
}
