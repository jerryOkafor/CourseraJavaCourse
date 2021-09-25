import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 20/09/2021
 */
public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");


//        MovieDatabase.initialize("ratedmoviesfull.csv");
//        RaterDatabase.initialize("ratings.csv");

        FourthRatings fourthRatings = new FourthRatings();

        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");


        int minimalRatters = 1;
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(minimalRatters, new TrueFilter());
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getTitle());
        }

    }

    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");

//        MovieDatabase.initialize("ratedmoviesfull.csv");
//        RaterDatabase.initialize("ratings.csv");

        FourthRatings fourthRatings = new FourthRatings();

        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.filterBy(new TrueFilter()).size() + " movies");

        int minimalRatters = 1;
        int yearAfter = 1990;
        String genre = "Drama";

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(yearAfter));
        allFilter.addFilter(new GenreFilter(genre));

        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(minimalRatters, allFilter);
        Collections.sort(averageRatings);

        System.out.println(averageRatings.size() + " movies matched");
        for (Rating rating : averageRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(rating.getValue() + " " + movie.getYear() + " " + movie.getTitle() + "\n\t" + movie.getGenres());
        }

    }

    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");


        FourthRatings fourthRatings = new FourthRatings();
        String raterId = "71";
        int minimalRaters = 5;
        int numOfTopSimilarRaters = 20;

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(raterId, numOfTopSimilarRaters, minimalRaters);
        for (Rating rating : similarRatings) {
            System.out.println(MovieDatabase.getMovie(rating.getItem()).getTitle() + " : Rating " + rating.getValue());
        }

    }

    public void printSimilarRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");


        FourthRatings fourthRatings = new FourthRatings();
        String raterId = "964";
        int minimalRaters = 5;
        int numOfTopSimilarRaters = 20;

        Filter filter = new GenreFilter("Mystery");

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numOfTopSimilarRaters, minimalRaters, filter);
        for (Rating rating : similarRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(movie.getTitle() + " : Rating " + rating.getValue());
            System.out.println("\t" + movie.getGenres());
        }
    }

    public void printSimilarRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");


        FourthRatings fourthRatings = new FourthRatings();
        String raterId = "120";
        int minimalRaters = 2;
        int numOfTopSimilarRaters = 10;

//        Filter filter = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        Filter filter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numOfTopSimilarRaters, minimalRaters, filter);
        for (Rating rating : similarRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(movie.getTitle() + " : Rating " + rating.getValue());
            System.out.println("\t" + movie.getDirector());
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");


        FourthRatings fourthRatings = new FourthRatings();
        String raterId = "168";
        int minimalRaters = 3;
        int numOfTopSimilarRaters = 10;
        int minMins = 80;
        int maxMins = 160;


        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(minMins, maxMins));

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numOfTopSimilarRaters, minimalRaters, filter);
        for (Rating rating : similarRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(movie.getTitle() + " : Mins: " + movie.getMinutes() + " : Rating " + rating.getValue());
            System.out.println("\t" + movie.getGenres());
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");


        FourthRatings fourthRatings = new FourthRatings();
        String raterId = "314";
        int minimalRaters = 5;
        int numOfTopSimilarRaters = 10;
        int minMins = 70;
        int maxMins = 200;
        int yearAfter = 1975;


        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(yearAfter));
        filter.addFilter(new MinutesFilter(minMins, maxMins));

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numOfTopSimilarRaters, minimalRaters, filter);
        for (Rating rating : similarRatings) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            System.out.println(movie.getTitle() + " Year: " + movie.getYear() + " : Mins: " + movie.getMinutes() + " : Rating " + rating.getValue());
            System.out.println("\t" + movie.getGenres());
        }
    }

    public void solveQuiz() {
        int[] me = {10, 6, 2, 8, 0};
        int[] them = {9, 4, 7, 0, 10};

        double dotProduct = 0;
        for (int i = 0; i < me.length; i++) {
            dotProduct += (me[i] - 5) * (them[i] - 5);
        }

        System.out.println("Do Product = " + dotProduct);
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mvr = new MovieRunnerSimilarRatings();
//        mvr.printAverageRatings();
//        System.out.println("\n");
//        mvr.printAverageRatingsByYearAfterAndGenre();

//        mvr.printSimilarRatings();
//        System.out.println();

//        mvr.printSimilarRatingsByGenre();
//        System.out.println();

//        mvr.printSimilarRatingsByDirector();
//        System.out.println();

//        mvr.printSimilarRatingsByGenreAndMinutes();
//        System.out.println();

//        mvr.printSimilarRatingsByYearAfterAndMinutes();
//        System.out.println();

        mvr.solveQuiz();
    }

}

