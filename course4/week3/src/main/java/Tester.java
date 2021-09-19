import edu.duke.FileResource;
import markovModel.MarkovOne;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows() {
//        FileResource fileResource = new FileResource("/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course4/week3/data/melville.txt");
        FileResource fileResource = new FileResource("/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course4/week3/data/confucius.txt");
        String trainingText = fileResource.asString();//"this is a test yes this is a test."
        MarkovOne markovOne = new MarkovOne();
        markovOne.setTraining(trainingText);

        ArrayList<String> follows = markovOne.getFollows("he");
        for (String s : follows) {
            System.out.println(s);
        }

        System.out.println("Follows count: " + follows.size());
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testGetFollows();

    }
}
