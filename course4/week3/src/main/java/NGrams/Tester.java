package NGrams;
import java.util.ArrayList;

/**
 * CourseraJavaCourse
 * @author : Jerry Okafor
 * @date : 28/07/2021
 *
 */
public class Tester {

    public void testGetFollows() {
        String trainingText = "this is just a test yes this is a simple test";
        MarkovWordOne markovOne = new MarkovWordOne();
        markovOne.setTraining(trainingText);

//        ArrayList<String> follows = markovOne.getFollows("this");
//        ArrayList<String> follows = markovOne.getFollows("is");
        ArrayList<String> follows = markovOne.getFollows("just");
        for (String s : follows) {
            System.out.println(s);
        }

        System.out.println("Follows count: " + follows.size());
    }

    public static void main(String[] args) {
//        MarkovWordOne markovWordOne = new MarkovWordOne();
//        markovWordOne.testIndexOf();
        Tester tester = new Tester();
        tester.testGetFollows();

    }
}
