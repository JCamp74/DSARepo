import java.util.Queue;
import java.util.LinkedList;

/**
 * CheckoutQueue Class.
 * Basic Queue class that holds customers.
 * Contains methods to add customers or finish checking one of them out.
 *
 * @author Jackson Campbell
 * @version 1.0
 */
public class CheckoutQueue {

    private int standServed;
    private int standWaitTime;
    private String checkoutName;
    private Queue<Customer> custQueue;

    /**
     * Instantiates a new CheckoutQueue.
     * Sets the name of the checkout.
     * @param checkoutName the name of the checkout.
     */
    public CheckoutQueue(String checkoutName) {
        standServed = 0;
        standWaitTime = 0;
        this.checkoutName = checkoutName;
        custQueue = new LinkedList<Customer>();
    }

    /**
     * newCustomer method.
     * Adds a customer based on the time of the simulation, and can show more detail if given.
     * @param simClock the simulation's clock.
     * @param showMore show extra details if needed.
     */
    public void newCustomer(int simClock, boolean showMore) {
        custQueue.add(new Customer(simClock));
        if(showMore) {
            System.out.print("Time: " + simClock + ",  " + checkoutName + " new customer. Line size: " + custQueue.size());
        }
    }

    /**
     * finishCheckout method.
     * Checks a customer out of line, and shows more data if needed.
     * @param simClock the simulation's clock
     * @param showMore show extra details if needed.
     */
    public void finishCheckout(int simClock, boolean showMore) {
        Customer nextCust = custQueue.remove();
        int time = nextCust.getArrivalTime();
        int wait = simClock - time;
        standWaitTime += wait;
        standServed++;
        if(showMore) {
            System.out.println("Time: " + simClock + ". Serving next customer in: " + checkoutName + "!");
        }

    }

    /**
     * UNUSED
     * getStandServed.
     * @return the standard customers that were served.
     */
    public int getStandServed() {
        return standServed;
    }

    /**
     * UNUSED
     * getStandWaitTime.
     * @return the standard line wait time total.
     */
    public int getStandWaitTime() {
        return standWaitTime;
    }

    /**
     * isEmpty boolean.
     * checks if the current queue is empty (contains no elements).
     * @return the isEmpty value of the current queue.
     */
    public boolean isEmpty() {
        return custQueue.isEmpty();
    }

    /**
     * UNUSED
     * lineSize integer.
     * Grabs the size of the current queue.
     * @return the size of the queue.
     */
    public int lineSize() {
        return custQueue.size();
    }


}



































