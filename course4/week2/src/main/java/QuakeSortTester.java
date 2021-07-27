public class QuakeSortTester {

    public static final String dataDir = "/Users/fbistech-d6m/IdeaProjects/CourseraJavaCourse/course4";

    public static void main(String[] args) {
//        QuakeSortWithTwoArrayLists quakeSortWithTwoArrayLists = new QuakeSortWithTwoArrayLists();
//        quakeSortWithTwoArrayLists.testSort();

        QuakeSortInPlace quakeSortInPlace = new QuakeSortInPlace();
//        quakeSortInPlace.testSort();
        quakeSortInPlace.testSortByLargestDepth();
        quakeSortInPlace.testSortByMagnitudeWithBubbleSort();
    }
}
