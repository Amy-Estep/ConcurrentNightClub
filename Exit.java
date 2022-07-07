/**
 * The class which will act as the exiting for your nightclub, modelling
 * people leaving your nightclub. 
 */
public class Exit implements Runnable{
    private final NightClub nightClub;
    private final int sleepScaler = 10;
    private String name = "Exit";

    /**
     * constructor for Exit object
     * @param nightClub which the exit is associated with
     */
    Exit(NightClub nightClub) {
        this.nightClub = nightClub;
    }

    /**
     * constructor for Exit object
     * @param nightClub which the exit is associated with
     * @param name of the exit
     */    
    Exit(NightClub nightClub, String name) {
        this.nightClub = nightClub;
        this.name = name;
    }
    // TODO Does this need an additional method here? Does the class perform a 
	// similar role to the Entrance class?
    /**
     * run method starts the Exit thread
     */
    @Override
    public void run() {
        while (true){
            try {
                double random = Math.random();
                Thread.sleep((long) (random*sleepScaler));
                nightClub.leave();
                System.out.println("Someone has left via the " + name + "\n" + "Number of people in "
                + nightClub.getName() + ": " + nightClub.getPeopleCount());
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Exit Thread");
                return;
            }
        }
    }

}
