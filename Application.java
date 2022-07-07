/**
 * You do not need to modify this class for submission, but may modify it 
 * to implement and test your solution.
 *
 * When marking your solution, we will use a new version of this file thus any
 * changes you make will not be marked.
*/
public class Application {

    /**
     * main method to run the nightclub application
     * @param args
     * @throws InterruptedException may be thrown when thread is sleeping
     */
    public static void main(String[] args) throws InterruptedException {
        String name = "Fiction";
        final int capacity = 20;
        final int runTime = 60000;
        Manager manager = new Manager("John Doe");
        NightClub fiction = new NightClub(name, capacity, manager);
        fiction.start();
        Thread.sleep(runTime);
        fiction.end();
    }
}
