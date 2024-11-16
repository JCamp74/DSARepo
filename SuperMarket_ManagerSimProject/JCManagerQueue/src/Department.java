import java.util.LinkedList;
import java.util.Queue;

/**
 * Department Class.
 * Used in ManagerSim to hold a queue of executives in a list.
 *
 * @author Jackson Campbell
 * @version 1.0
 */
public class Department {

    private Queue<Executive> execQueue;
    private String name;

    /**
     * Instantiates a new Department.
     * Sets the executive queue to a new LinkedList.
     * @param name the department's name to be set.
     */
    public Department(String name) {
        this.name = name;
        execQueue = new LinkedList<>();
    }

    /**
     * Hire method.
     * Adds an executive to the department list.
     * @param e the given executive to hire.
     */
    public void hire(Executive e) {
        execQueue.offer(e);
    }

    /**
     * Fire method.
     * Fires an executive from the department list.
     * @param e the given executive to fire.
     */
    public void fire(Executive e) {
        execQueue.remove(e);
    }

    /**
     * getExecutives method.
     * @return the executives in the given queue.
     */
    public Queue<Executive> getExecutives() {
        return execQueue;
    }

    /**
     * getDeptName method.
     * @return the name of the department.
     */
    public String getDeptName() {
        return name;
    }

}
