package iMarkovModel;

/**
 *
 * CourseraJavaCourse
 *
 * @Author : Jerry Okafor
 * @Date : 28/07/2021
 */

public interface IMarkovModel {
    public void setTraining(String text);

    public String getRandomText(int numChars);

    public void setRandom(int seed);

}
