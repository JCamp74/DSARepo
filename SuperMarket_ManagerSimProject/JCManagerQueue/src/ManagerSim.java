import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * ManagerSim Class. Contains instances of Executives within Queues,
 * and uses LinkedLists of Department to manage departments added.
 * All methods used are private, as run will contain all necessary
 * code to execute the user-controlled simulation.
 *
 * @author Jackson Campbell
 * @version 1.0
 */
public class ManagerSim {

    private LinkedList<Department> departments;
    private Queue<Executive> unemployed;

    /**
     * Instantiates a new Manager simulation.
     * Sets "departments" and "unemployed" to new LinkedLists.
     */
    public ManagerSim() {
        departments = new LinkedList<>();
        unemployed = new LinkedList<>();
    }

    /**
     * Add method.
     * Creates a new department within the departments LinkedList.
     * @param deptName the new department to be added.
     */
    private void add(String deptName) {
        for(Department dept : departments) {
            if(dept.getDeptName().equals(deptName)) {
                System.out.println("Department: " + deptName + " already exists.");
                return;
            }
        }
        Department newDept = new Department(deptName);
        departments.add(newDept);
        System.out.println("New department: " + deptName + " has been created successfully.");

    }

    /**
     * Hire method.
     * Creates a new executive in the unemployed LinkedList.
     * Initializes salary to 0 dollars.
     * @param name the new executive to create.
     */
    private void hire(String name) {
        Executive newExec = new Executive(name, 0);
        unemployed.offer(newExec);
        System.out.println("New executive: " + name + " was hired. No department assigned currently.");

    }

    /**
     * Join method.
     * Joins an unemployed executive to a department in the list.
     * @param name the unemployed executive to hire.
     * @param deptName the department to hire to.
     */
    private void join(String name, String deptName) {
        Executive execToDept = null;
        Department deptToJoin = null;
        for(Executive exec : unemployed) {
            if(exec.getName().equals(name)) {
                execToDept = exec;
                break;
            }
        }
        if(execToDept == null) {
            System.out.println("Executive: " + name + " does not exist in unemployed.");
            return;
        }

        for(Department dept : departments) {
            if(dept.getDeptName().equals(deptName)) {
                deptToJoin = dept;
            }
        }
        if(deptToJoin == null) {
            System.out.println("Department: " + deptName + " does not exist.");
            return;
        }

        unemployed.remove(execToDept);
        deptToJoin.hire(execToDept);
        execToDept.setSalary(40000);
        updateSalary(deptToJoin);
        System.out.println(name + " has been added to department: " + deptName + " successfully.");
    }

    /**
     * Quit method.
     * Fires an executive from their given department.
     * @param name the executive to fire.
     */
    private void quit(String name) {
        Executive quitter = findExec(name);
        if(quitter != null) {
            for(Department dept : departments) {
                if(dept.getExecutives().contains(quitter)) {
                    dept.fire(quitter);
                    unemployed.offer(quitter);
                    quitter.setSalary(0);
                    updateSalary(dept);
                    System.out.println(name + " has quit their department and is now unemployed.");
                    return;
                }
            }
        }
        System.out.println(name + " is currently not employed to a department.");
    }

    /**
     * Change method.
     * Changes an executive's department to the newly given department.
     * @param name the executive to change.
     * @param deptName the department to move the executive to.
     */
    private void change(String name, String deptName) {
        Executive execToJoin = findExec(name);
        Department deptToLeave = null;
        Department deptToJoin = null;

        if(execToJoin == null) {
            System.out.println("Executive " + name + " does not exist.");
            return;
        }

        for(Department dept : departments) {
            for(Executive exec : dept.getExecutives()) {
                if(exec.getName().equals(name)) {
                    deptToLeave = dept;
                    break;
                }
            }
        }

        for(Department dept : departments) {
            if(dept.getDeptName().equals(deptName)) {
                deptToJoin = dept;
                break;
            }
        }

        if(deptToLeave != null && deptToJoin != null) {
            deptToLeave.fire(execToJoin);
            deptToJoin.hire(execToJoin);

            updateSalary(deptToLeave);
            updateSalary(deptToJoin);

            System.out.println(name + " has been moved from " + deptToLeave.getDeptName() + " and is now hired to department: " + deptName);
        }
    }

    /**
     * Salary method.
     * Prints the salary of a given executive.
     * @param name the executive to print the salary of.
     */
    private void salary(String name) {
        Executive salOutput = findExec(name);

        if(salOutput != null) {
            System.out.println("Executive " + name + "'s salary: $" + salOutput.getSalary());
        }
    }

    /**
     * Payroll method.
     * Prints the payroll of all the executives, unemployed or hired.
     */
    private void payroll() {
        for(Department dept : departments) {
            System.out.println(dept.getDeptName() + " salaries:");
            for(Executive exec : dept.getExecutives()) {
                System.out.print(exec + ": $" + exec.getSalary() + "  ");
            }
            System.out.println();
        }
        System.out.println("Non-department Executives:");
        for(Executive exec : unemployed) {
            System.out.print(exec + ": $" + exec.getSalary() + "  ");
        }
        System.out.println();
    }

    /**
     * Exit method.
     * Exits the simulation.
     */
    private void exit() {
        System.exit(0);
    }


    /**
     * findExec helper method.
     * Finds the given executive within the unemployed list
     * and all departments.
     * @param execName the executive to find.
     * @return the found executive or null if not found.
     */
    private Executive findExec(String execName) {
        for(Executive exec : unemployed) {
            if(exec.getName().equals(execName)) {
                return exec;
            }
        }
        for(Department dept : departments) {
            for(Executive exec : dept.getExecutives()) {
                if(exec.getName().equals(execName)) {
                    return exec;
                }
            }
        }
        return null;
    }

    /**
     * updateSalary helper method.
     * Updates the salary of all the executives in the department.
     * @param dept the department to update salaries in.
     */
    private void updateSalary(Department dept) {
        Queue<Executive> updater = dept.getExecutives();
        LinkedList<Executive> temp = new LinkedList<>();

        while(!updater.isEmpty()) {
            temp.offer(updater.poll());
        }

        for(int i = 0; i < temp.size(); i++) {
            Executive execToUpdate = temp.get(i);
            execToUpdate.setSalary(40000 + ((temp.size() - i - 1) * 5000));
        }

        for(Executive exec : temp) {
            updater.offer(exec);
        }
    }

    /**
     * displayOptions method.
     * Displays the options to choose within the simulation.
     */
    private void displayOptions() {
        System.out.println("1. Add a department");
        System.out.println("2. Hire an executive to unemployed");
        System.out.println("3. Join an executive to a department");
        System.out.println("4. Quit an executive back to unemployed");
        System.out.println("5. Change an executive's department");
        System.out.println("6. Display the payroll of all executives in each department");
        System.out.println("7. Display a specific executive's salary");
        System.out.println("8. Quit Managerio");
    }

    /**
     * runOption method.
     * Uses a switch-case loop to run the given option within the given cases.
     * @param inp the given case for the loop.
     */
    private void runOption(int inp) {
        Scanner name = new Scanner(System.in);
        String inp1;
        String inp2;
        switch(inp) {
            case 1:
                System.out.print("Enter new department name: ");
                inp1 = name.nextLine();
                add(inp1);
                break;
            case 2:
                System.out.print("Enter new executive name: ");
                inp1 = name.nextLine();
                hire(inp1);
                break;
            case 3:
                System.out.print("Enter executive name: ");
                inp1 = name.nextLine();
                System.out.println();
                System.out.print("Enter department to hire to: ");
                inp2 = name.nextLine();
                join(inp1, inp2);
                break;
            case 4:
                System.out.print("Enter executive to fire: ");
                inp1 = name.nextLine();
                quit(inp1);
                break;
            case 5:
                System.out.print("Enter executive to move: ");
                inp1 = name.nextLine();
                System.out.println();
                System.out.print("Enter department to move to: ");
                inp2 = name.nextLine();
                change(inp1, inp2);
                break;
            case 6:
                payroll();
                break;
            case 7:
                System.out.print("Enter executive to show: ");
                inp1 = name.nextLine();
                salary(inp1);
                break;
            case 8:
                exit();
                break;
        }
    }


    /**
     * Run method.
     * Runs the entire simulation in a loop unless exited out of.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Managerio! Please choose your command.");
        System.out.println("EXECUTIVE NAME FORMAT: Last, First");
        while(true) {
            displayOptions();
            int userInp = input.nextInt();
            runOption(userInp);
        }
    }
}
