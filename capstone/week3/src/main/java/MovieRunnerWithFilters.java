import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 19/09/2021
 */
public class MovieRunnerWithFilters {

    public void printAverageRatings() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");


        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");


        int minimalRatters = 35;
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, new TrueFilter());
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getTitle());
        }

    }

    public void printAverageRatingsByYearAfter() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");


        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");


        int minimalRatters = 20;
        int year = 2000;
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, new YearAfterFilter(year));
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getYear() + " " + movie.getTitle());
        }

    }

    public void printAverageRatingsByGenre() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");

        int minimalRatters = 20;
        String genre = "Comedy";
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, new GenreFilter(genre));
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched ");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getYear() + " " + movie.getTitle() + "\n\t" + movie.getGenres());
        }

    }

    public void printAverageRatingsByMinutes() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");

        int minimalRatters = 5;
        int minMin = 105;
        int maxMin = 135;
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, new MinutesFilter(minMin, maxMin));
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched ");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " Time: " + movie.getMinutes() + " " + movie.getTitle());
        }

    }

    public void printAverageRatingsByDirectors() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + "raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()) + "movies");

        int minimalRatters = 4;
        String director = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, new DirectorsFilter(director));
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched ");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getTitle() + "\n\t" + movie.getDirector());
        }

    }

    public void printAverageRatingsByYearAfterAndGenre() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");

        int minimalRatters = 8;
        int yearAfter = 1990;
        String genre = "Drama";

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(yearAfter));
        allFilter.addFilter(new GenreFilter(genre));

        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, allFilter);
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getYear() + " " + movie.getTitle() + "\n\t" + movie.getGenres());
        }

    }

    public void printAverageRatingsByDirectorsAndMinutes() {
//        MovieDatabase.initialize("ratedmovies_short.csv");
//        ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");


        int minimalRatters = 3;
        int minMin = 90;
        int maxMin = 180;
        String director = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(minMin, maxMin));
        allFilter.addFilter(new DirectorsFilter(director));

        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRatters, allFilter);
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " Time: " + movie.getMinutes() + " " + movie.getTitle() + "\n\t" + movie.getDirector());
        }

    }

    public static void main(String[] args) {
        MovieRunnerWithFilters mvf = new MovieRunnerWithFilters();
        mvf.printAverageRatings();
        System.out.println("\n");

        mvf.printAverageRatingsByYearAfter();
        System.out.println("\n");

        mvf.printAverageRatingsByGenre();
        System.out.println("\n");

        mvf.printAverageRatingsByMinutes();
        System.out.println("\n");

        mvf.printAverageRatingsByDirectors();
        System.out.println("\n");

        mvf.printAverageRatingsByYearAfterAndGenre();
        System.out.println("\n");

        mvf.printAverageRatingsByDirectorsAndMinutes();
    }
}
