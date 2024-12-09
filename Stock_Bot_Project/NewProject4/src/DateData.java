/**
 * DateData class. This class holds all the data for each given day.
 * Has methods to grab necessary sets of data, and to print all values of a given day.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class DateData {

    private double low;
    private double high;
    private double open;
    private double close;
    private String date;

    /**
     * Instantiates a new Date data with user-inputted values.
     * Helps allow for a smooth creation of the array once values are
     * grabbed from the CSV file.
     *
     * @param date  the date of the given day.
     * @param open  the open value of the share when the market opens.
     * @param high  the highest value of the share that day.
     * @param low   the lowest value of the share that day.
     * @param close the closing value of the share when the market closes.
     */
    public DateData(String date, double open, double high, double low, double close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    /**
     * Gets the given date.
     *
     * @return the given date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the opening value of the share.
     *
     * @return the opening value of the share.
     */
    public double getOpen() {
        return open;
    }

    /**
     * Gets the closing value of the share.
     *
     * @return the closing value of the share.
     */
    public double getClose() {
        return close;
    }

    /**
     * Displays the data within the created DateData class. Used to display data
     * for testing or other purposes.
     */
    public void displayData() {
        System.out.println("Date: " + date);
        System.out.println("Open price: " + open);
        System.out.println("High price: " + high);
        System.out.println("Low price: " + low);
        System.out.println("Close price: " + close);
    }
}