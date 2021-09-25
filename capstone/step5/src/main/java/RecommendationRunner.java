import java.util.ArrayList;
import java.util.Random;

/**
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 25/09/2021
 */
public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        ArrayList<String> itemsToRate = new ArrayList<>();
        int numToDisplay = 15;
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();

        for (int i = 0; i < numToDisplay; i++) {
            int r = rand.nextInt(movies.size());
            String title = movies.get(r);
            if (!itemsToRate.contains(title)) {
                itemsToRate.add(title);
            }
        }
        return itemsToRate;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        FourthRatings fr = new FourthRatings();
        int numToDisplay = 15;
        int minimalSimilarRaters = 10;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, minimalSimilarRaters, minimalRaters);
        int length = Math.min(similarRatings.size(), numToDisplay);

        System.out.println
                (
                        "<style>" +
                                "  body { background-color: #1d1f20; }" +
                                "  h2.error { background-color: #ffd700; color: #dc143c; margin: 5; }" +
                                "  #customers, h2 { font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;" +
                                "                   border-collapse: collapse; width: 100%;text-align: center;}" +
                                "  #custmers td, #customers th, h2 { border: 1px solid #3e3a3a; padding: 8px;}" +
                                "  #customers tr { background-color: #343537; color: #efefef; }" +
                                "  #customers tr:nth-child(even) { background-color: #686666; }" +
                                "  #customers tr:hover { background-color: #ff4444; }" +
                                "  #customers th { padding-top: 12px; padding-bottom: 12px; text-align: center;" +
                                "                  background-color: #ef040a; color: white; }" +
                                "  #customers img { height: 50%; }" +
                                "  h2 { background-color: #ef040a; }" +
                                "</style>" +
                                "<div class=\"content\">" +
                                "  <div class=\"ui-widget\">" +
                                "   <html>" +
                                "<h2>Hi there! - These are some movies you may like</h2>" +
                                "<table id=\"customers\">" +
                                "  <tr>" +
                                "    <th>#</th>" +
                                "    <th>Poster</th>" +
                                "    <th>Title</th>" +
                                "    <th>Genre</th>" +
                                "    <th>Year</th>" +
                                "    <th>Time</th>" +
                                "  </tr>" +
                                "  <tr>"
                );

        for (int i = 0; i < length; i++) {
            int num = i + 1;
            System.out.println("    <td>" + num + "</td>");
            System.out.println("    <td><img src=");
            System.out.println("\"" + MovieDatabase.getPoster(similarRatings.get(i).getItem()) + "\"");
            System.out.println(" /> </td>");
            System.out.println("    <td>" + MovieDatabase.getTitle(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("    <td>" + MovieDatabase.getCountry(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("    <td>" + MovieDatabase.getYear(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("    <td>" + MovieDatabase.getMinutes(similarRatings.get(i).getItem()) + " Minutes" + "</td>");
            System.out.println("  </tr>");
        }

    }


    public static void main(String[] args) {
        RecommendationRunner runner = new RecommendationRunner();
        runner.printRecommendationsFor("71");
    }
}
