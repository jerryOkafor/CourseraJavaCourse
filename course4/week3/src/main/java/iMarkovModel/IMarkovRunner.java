package iMarkovModel;

import edu.duke.FileResource;

public class IMarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource("/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course4/week3/data/romeo.txt");
//        FileResource fr = new FileResource("/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course4/week3/data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;


//        MarkovZero mz = new MarkovZero();
//        runModel(mz, st, size, 32);

//        MarkovOne mOne = new MarkovOne();
//        runModel(mOne, st, size, 32);


//        MarkovModel mThree = new MarkovModel(5);
//        runModel(mThree, st, size, 531);

        /*
        markovModel.MarkovFour mFour = new markovModel.MarkovFour();
        runModel(mFour, st, size, 32);
        */

        EfficientMarkovModel model = new EfficientMarkovModel(5);
        runModel(model,st,500,615);
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public void testHashMap() {
//        FileResource fr = new FileResource();
        String st = "yes-this-is-a-thin-pretty-pink-thistle";//fr.asString();
        st = st.replace('\n', ' ');
        int size = 50;
        int seed = 42;

        EfficientMarkovModel m5 = new EfficientMarkovModel(2);
        runModel(m5, st, size, seed);
    }

    public void compareMethods() {

        FileResource fr = new FileResource("/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course4/week3/data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        MarkovModel mTwo = new MarkovModel(2);
        double begin = System.nanoTime();
        runModel(mTwo, st, size, seed);
        double end = System.nanoTime();
        double markovModelTime = (end-begin);

        EfficientMarkovModel mTwoEff = new EfficientMarkovModel(2);
        begin = System.nanoTime();
        runModel(mTwoEff, st, size, seed);
        end = System.nanoTime();
        double markovEfficientTime = (end-begin);
        System.out.println("Time for MarkovModel: " + markovModelTime + "\n"
                + "Time for EfficientMarkovModel: " + markovEfficientTime);
    }

    public static void main(String[] args) {
        IMarkovRunner runner = new IMarkovRunner();
        runner.runMarkov();
//        runner.testHashMap();
//        runner.compareMethods();



    }


}
