/**
 * The manager is responsible for opening and closing the nightclub.
 */
public class Manager implements Runnable {
    private final String name;
    private NightClub nightClub;
    private final int sleepScaler = 10000;
    
    /**
     * constructor for Manager object
     * @param name name of the manager
     */
    Manager(String name){
        this.name = name;
    }
    
    /**
     * constructor for Manager object
     * @param name name of the manager
     * @param nightClub which the manager is associated with
     */
    Manager(String name, NightClub nightClub){
        this.name = name;
        this.nightClub = nightClub;
    }

    /**
     * for the manager to accept the job at given nightclub
     * @param nightClub nightclub to accept job in
     */
    public void acceptJob(NightClub nightClub){
        this.nightClub = nightClub;
    }

    /**
     * for the manager to leave the job at given nightclub
     */
    public void leaveJob(){
        this.nightClub = null;
    }

    // TODO Is the manager an active or a passive process? What would you need
	// if it was an active process?
    /**
     * run method starts the manager thread
     */
    @Override
    public void run() {
        while (true) {
            try {
                double randomOpenPeriod = Math.random();
                double randomClosurePeriod = Math.random();
                nightClub.open();
                System.out.println("Manager: " + name + " has opened Nightclub: " + nightClub.getName());
                Thread.sleep((long) (randomOpenPeriod * sleepScaler));
                nightClub.close();
                System.out.println("Manager: " + name + " has closed Nightclub: " + nightClub.getName());
                Thread.sleep((long) (randomClosurePeriod * sleepScaler));
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Manager Thread");
                return;
            }
        }
    }
}
