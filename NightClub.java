
/**
 * The primary class for your NightClub, which will have a name, capacity and
 * a manager. People can arrive and leave your club asynchronously once it is
 * opened. Once the manager tries to close the club, the entrance should be
 * closed, then close the exit once everyone has left.
 */
public class NightClub {
    // TODO You need a variable for counting people named: peopleCount
    // you need a variable here for the capacity of the club: capacity
    private final int capacity;
    private int peopleCount = 0;
    private String name = "default";
    private boolean isOpen = false;
    private Entrance entrance;
    private Exit exit;
    private final Manager manager;
    private Thread managerTh;
    private Thread entranceTh;
    private Thread exitTh;

    /**
     * constructor for NightClub object
     * @param name of the nightclub
     * @param capacity of the nightclub
     * @param manager of the nightclub
     */ 
    NightClub(String name, int capacity, Manager manager) {
        this.name = name;
        this.capacity = capacity;
        this.manager = manager;
        manager.acceptJob(this);
    }

    /**
     * starts the nightclub
     */
    public void start() {
        System.out.println("We are starting club: " + name);
        // TODO Create a thread for the manager
        this.managerTh = new Thread(manager);
        entrance = new Entrance(this, "Entrance");
        exit = new Exit(this, "Exit");
        // TODO Start a thread for the manager
        managerTh.start();
    }

    /**
     * ends the nightclub
     * @throws InterruptedException
     */
    public void end() throws InterruptedException {
        this.close();
        // kill the thread that is running the manager
        manager.leaveJob();
        managerTh.interrupt();
        System.out.println("The simulation has ended.");
    }

    /**
     * opens the nightclub
     * checks if already open if not then starts the 
     * entrance and exit threads so that people can
     * enter and exit the nightclub   
     */
    public void open() {
        if (!isOpen){
            isOpen = true;
            // TODO Create threads so that users can access or leave the club.
            // Should you also start these threads here?
            entranceTh = new Thread(entrance);
            exitTh = new Thread(exit);
            entranceTh.start();
            exitTh.start();
        }else {
            System.out.println("The club is already open!");
        }
    }

    /**
     * closes the nightclub
     * checks if the nightclub is already closed
     * if not then sets isOpen status to false
     * and kills the entrance thread so people can
     * no longer enter the nightclub, then kills
     * the exit thread once everyone has left
     * @throws InterruptedException may be thrown when thread is joined
     */
    public void close() throws InterruptedException {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing the entrance.");
            // TODO kill the threads that are facilitating accessing and
            // leaving feature. 
            entranceTh.interrupt();
            entranceTh.join();
            while (getPeopleCount() > 0) {
                leave();
                System.out.println("People are leaving, number of people left in club: " + getPeopleCount());
            }
            System.out.println("Everyone has left the club.");
        } else {
            System.out.println("The club is already closed!");
        }
        exitTh.interrupt();
        exitTh.join();
    }

    /**
     * @return peopleCount - the number of people in the nightclub at current
     */
    public int getPeopleCount() {
        return peopleCount;
    }

    /**
     * @return name of the nightclub
     */
    public String getName() {
        return name;
    }

    /**
     * checks if nightclub is full,
     * if not then increase people count
     * to imitate people arriving at the nightclub
     * @throws InterruptedException may be thrown when thread is waiting
     */
    public synchronized void enter() throws InterruptedException {
        // TODO Must wait for space in a while loop for space in the club.
        while (peopleCount == capacity) {
            wait();
        }
        if (peopleCount < capacity) {
            peopleCount++;
        }
        notifyAll();
    }

    /**
     * checks if nightclub is empty,
     * if not then decrease people count
     * to imitate people leaving
     * @throws InterruptedException may be thrown when thread is waiting
     */
    public synchronized void leave() throws InterruptedException {
        // TODO Must have enough people to allow this feature. Is there a 
        // condition to check?
        while (peopleCount == 0) {
            wait();
        }
        if (peopleCount > 0) {
            peopleCount--;
        }
        notifyAll();
    }
}
