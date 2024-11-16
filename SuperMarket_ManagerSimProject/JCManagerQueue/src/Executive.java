/**
 * Executive Class.
 * Holds the name and salary of each instantiated executive.
 *
 * @author Jackson Campbell
 * @version 1.0
 */
public class Executive {

    private String name;
    private int salary;

    /**
     * Instantiates a new Executive.
     * Sets both the name and salary.
     * @param name the name of the executive to set.
     * @param salary the salary to set.
     */
    public Executive(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * getName method.
     * Grabs the name of the current executive.
     * @return the name of the executive.
     */
    public String getName() {
        return name;
    }

    /**
     * getSalary method.
     * Grabs the salary of the current executive.
     * @return the salary of the executive.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * setSalary method.
     * Sets the salary of the given executive.
     * Used in updateSalary in ManagerSim.
     * @param salary the salary to set for the executive.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * toString override.
     * Overrides the java.lang toString method.
     * @return the executive's name.
     */
    @Override
    public String toString() {
        return name;
    }

}
