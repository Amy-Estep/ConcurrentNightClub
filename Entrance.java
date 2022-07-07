/**
 * The class which will act as the entrance for your nightclub, modelling
 * people arriving at and entering your nightclub. 
 */
public class Entrance implements Runnable{
    private final NightClub nightClub;
    private final int sleepScaler = 10;
    private String name = "Entrance";

    /**
     * constructor for Entrance object
     * @param nightClub which the entrance is associated with
     */
    Entrance(NightClub nightClub) {
        this.nightClub = nightClub;
    }

    /**
     * constructor for Entrance object
     * @param nightClub which the entrance is associated with
     * @param name of the entrance
     */ 
    Entrance(NightClub nightClub, String name) {
        this.nightClub = nightClub;
        this.name = name;
    }

    /**
     * run method starts the Entrance thread
     */
    @Override
    public void run() {
        // TODO Enter code for how the entrance to be used.
        while (true){
            try {
                double random = Math.random();
                Thread.sleep((long) (random*sleepScaler));
                nightClub.enter();
                System.out.println("Someone has entered via the " + name + "\n" + "Number of people in "
                + nightClub.getName() + ": " + nightClub.getPeopleCount());
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Entrance Thread");
                return;
            }
        }
    }

}
