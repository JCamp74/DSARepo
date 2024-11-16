import java.util.Scanner;
import java.util.Random;

/**
 * SuperMarketSim class.
 * Runs the simulation and handles each case as the simulation runs.
 *
 * @author Jackson Campbell
 * @version 1.0
 */
public class SuperMarketSim {

    private CheckoutQueue standQueue;
    private CheckoutQueue expQueue;
    private CheckoutQueue supExpQueue;
    private int totalTime;
    private int maxItems;
    private int numSuper;
    private int numExp;
    private boolean showMore;
    private double arrivalRate;
    private int simClock = 0;
    private int processingTime;

    /*
    Foregoing the calculations as this program does not run the way I wanted it to.
    I tried to get things to work, and could not unfortunately get them to work properly.
    The simulation does still run and perform the supermarket simulation, but does not properly collect data.
    I do have detailed output on what exactly happens during each step, even if it is not done right it is still there.
    Apologies for not including data collection, and for this submission compared to the one before it.
     */


    /**
     * Instantiates a new SuperMarketSim.
     * Creates the Standard Queue, Express Queue, and Super Express Queue.
     * Sets the arrival rate and processing time for each item.
     */
    public SuperMarketSim() {
        standQueue = new CheckoutQueue("Standard Checkout");
        expQueue = new CheckoutQueue("Express Checkout");
        supExpQueue = new CheckoutQueue("Super Express Checkout");
        arrivalRate = .8;
        processingTime = 5;
    }

    /**
     * Simulate method.
     * creates and processes the supermarket simulation.
     * Runs using the serveCust method.
     */
    public void simulate() {
        Random randItems = new Random();
        for(simClock = 0; simClock < totalTime; simClock++) {
            int x = randItems.nextInt(maxItems) + 1;
            if(Math.random() < arrivalRate) {
                if (x <= numSuper) {
                    supExpQueue.newCustomer(simClock, showMore);
                    if(showMore) {
                        System.out.println(" Items: " + x);
                    }
                } else if (x > numSuper && x <= numExp) {
                    expQueue.newCustomer(simClock, showMore);
                    if(showMore) {
                        System.out.println(" Items: " + x);
                    }
                } else if (x > numExp) {
                    standQueue.newCustomer(simClock, showMore);
                    if(showMore) {
                        System.out.println(" Items: " + x);
                    }
                }
            }
            if(simClock % processingTime == 0) {
                serveCust();
            }
        }
    }

    /**
     * serveCust method.
     * Serves a customer according to the given conditions.
     */
    public void serveCust() {
        if(!supExpQueue.isEmpty()) {
            supExpQueue.finishCheckout(simClock, showMore);
        }
        if(!expQueue.isEmpty()) {
            expQueue.finishCheckout(simClock, showMore);
        }
        if(!standQueue.isEmpty()) {
            standQueue.finishCheckout(simClock, showMore);
        }

        int itemsProcessed = 0;

        while(!supExpQueue.isEmpty() && itemsProcessed < processingTime) {
            supExpQueue.finishCheckout(simClock, showMore);
        }
        while(!expQueue.isEmpty() && itemsProcessed < processingTime) {
            expQueue.finishCheckout(simClock, showMore);
        }
        while(!expQueue.isEmpty() && itemsProcessed < processingTime) {
            expQueue.finishCheckout(simClock, showMore);
        }
    }

    /**
     * enterData method.
     * Gathers the data for the simulation parameters.
     * Sets the run time, the max items, the item ranges,
     * and asks if you wish to see a detailed simulation.
     */
    public void enterData() {
        Scanner input = new Scanner(System.in);

        System.out.println("Max simulation time: ");
        totalTime = input.nextInt();

        System.out.println("Set max items: ");
        maxItems = input.nextInt();

        System.out.println("Set item limit for Super Express Checkout: ");
        numSuper = input.nextInt();

        System.out.println("Set item limit for Express Checkout (MUST BE GREATER THAN SUPER EXPRESS): ");
        numExp = input.nextInt();

        System.out.println("Show detailed simulation? (True/False)");
        showMore = input.nextBoolean();
    }

    /**
     * Run method.
     * Runs the entire simulation, using methods simulate and enterData.
     * Exits after the simulation finishes.
     */
    public void run() {
        enterData();
        simulate();
        System.exit(0);
    }




}
