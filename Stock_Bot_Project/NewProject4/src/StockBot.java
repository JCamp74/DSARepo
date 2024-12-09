import java.io.*;
import java.util.ArrayList;

/**
 * Stock Bot class. Used in creation of all CSV files except for original data.
 * Populates the ArrayList values, calculates a Relative Strength Index, creates
 * a moving average trendline, and plots all necessary data. Runs a dummy version
 * of what would be the full simulation if properly finished.
 * File reading and writing programming adapted from Jessica Smith in Probability and Applied Statistics.
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class StockBot {

    private ArrayList<DateData> data = new ArrayList<>();
    private ArrayList<Double> openListSmoothed = new ArrayList<>();
    private ArrayList<Double> highListSmoothed = new ArrayList<>();
    private ArrayList<Double> lowListSmoothed = new ArrayList<>();
    private ArrayList<Double> closeListSmoothed = new ArrayList<>();

    private double balance = 1000.00;
    private double shareBalance = 0;
    private int amountOfShares = 0;
    private double dailyInterest = 0;


    /**
     * Populate values method. Reads from the original data, and creates a double after parsing each needed section.
     * Adds the date and newly created doubles to a new DateData class instance in the data ArrayList.
     * File reading/writing adapted from Jessica Smith.
     */
    public void populateValues() {
        try {
            BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\Jackson Campbell\\OneDrive - go.Stockton.edu\\DSAProjectsVSCode\\NewProject4\\src\\IntelStock.csv"));
            String line = read.readLine();

            while (line != null) {
                String date = line.split(",")[0].trim();
                double open = Double.parseDouble(line.split(",")[1].trim());
                double high = Double.parseDouble(line.split(",")[2].trim());
                double low = Double.parseDouble(line.split(",")[3].trim());
                double close = Double.parseDouble(line.split(",")[4].trim());

                data.add(0, new DateData(date, open, high, low, close));

                line = read.readLine();
            }
            read.close();
        } catch (IOException e) {
            System.out.println("IOException! Error handling current file!");
        }
    }

    /**
     * Calculate the Relative Strength Index.
     * Uses the steps and formula given on blackboard to create the RSI for
     * future usage.
     *
     * @param index the current index to calculate RSI.
     * @return the Relative Strength Index at the given array index.
     */
    public double calculateRSI(int index) {
        double upValueTotal = 0;
        double downValueTotal = 0;
        double window = 14; //This is your N value!
        int lowBound = (int) (index - window);

        if(lowBound < 0) {
            lowBound = 0;
        }
        if(lowBound == index) {
            return 0;
        }

        for(int j = lowBound + 1; j < index; j++) {
            double change = data.get(j).getClose() - data.get(j-1).getClose();
            if (change > 0) {
                upValueTotal += change;
            } else {
                downValueTotal -= change;
            }

        }
        double upValAvg = upValueTotal / window;
        double downValAvg = downValueTotal / window;
        double relStrength = upValAvg / downValAvg;

        return (100.0 - (100 / (1.0 + relStrength)));
    }

    /**
     * Create a moving average/rolling average.
     * Creates a moving average, and plots it to a now smoothed set of data.
     * Method adapted from Jessica Smith for file reading/writing, myself for
     * rolling average creation.
     */
    public void createMA() {
        ArrayList<Double> openList = new ArrayList<>();
        ArrayList<Double> highList = new ArrayList<>();
        ArrayList<Double> lowList = new ArrayList<>();
        ArrayList<Double> closeList = new ArrayList<>();


        int upDownWindow = 10;
        double rollAvgOpen = 0;
        double rollAvgHigh = 0;
        double rollAvgLow = 0;
        double rollAvgClose = 0;
        double numOfValues = 0;
        try {
            BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\Jackson Campbell\\OneDrive - go.Stockton.edu\\DSAProjectsVSCode\\NewProject4\\src\\IntelStock.csv"));
            String line = read.readLine();

            while (line != null) {
                double open = Double.parseDouble(line.split(",")[1].trim());
                double high = Double.parseDouble(line.split(",")[2].trim());
                double low = Double.parseDouble(line.split(",")[3].trim());
                double close = Double.parseDouble(line.split(",")[4].trim());

                openList.add(0, open);
                highList.add(0, high);
                lowList.add(0, low);
                closeList.add(0, close);

                line = read.readLine();
            }
            read.close();
        } catch (IOException e) {
            System.out.println("IOException! Trouble while reading current file!");
        }

        for(int i = 0; i < openList.size(); i++) {
            int lowBound = i - upDownWindow;
            int highBound = i + upDownWindow;
            if(lowBound < 0) {
                lowBound = 0;
            }
            if(highBound > openList.size()) {
                highBound = openList.size();
            }
            for(int j = lowBound; j < highBound; j++) {
                rollAvgOpen += openList.get(j);
                rollAvgHigh += highList.get(j);
                rollAvgLow += lowList.get(j);
                rollAvgClose += closeList.get(j);
                numOfValues++;
            }
            rollAvgOpen = rollAvgOpen / numOfValues;
            rollAvgHigh = rollAvgHigh / numOfValues;
            rollAvgLow = rollAvgLow / numOfValues;
            rollAvgClose = rollAvgClose / numOfValues;

            openListSmoothed.add(rollAvgOpen);
            highListSmoothed.add(rollAvgHigh);
            lowListSmoothed.add(rollAvgLow);
            closeListSmoothed.add(rollAvgClose);

            numOfValues = 0;
            rollAvgOpen = 0;
            rollAvgHigh = 0;
            rollAvgLow = 0;
            rollAvgClose = 0;
        }

        try {
            FileWriter newFile = new FileWriter("C:\\Users\\Jackson Campbell\\OneDrive - go.Stockton.edu\\DSAProjectsVSCode\\NewProject4\\src\\IntelDataSmoothed.csv");
            PrintWriter output = new PrintWriter(newFile);
            for(int i = 0; i < openListSmoothed.size(); i++) {
                output.println(openListSmoothed.get(i) + "," + highListSmoothed.get(i) + "," + lowListSmoothed.get(i) + "," + closeListSmoothed.get(i));
            }
            output.close();
        } catch (IOException e) {
            System.out.println("IOException! Trouble writing to the file!");
        }
    }

    /**
     * Plots the RSI to a new CSV file containing only
     * the given RSI values.
     * File reading/writing adapted from Jessica Smith.
     */
    public void plotRSI() {
        try {
            FileWriter newFile = new FileWriter("C:\\Users\\Jackson Campbell\\OneDrive - go.Stockton.edu\\DSAProjectsVSCode\\NewProject4\\src\\RSIPlotted.csv");
            PrintWriter output = new PrintWriter(newFile);
            for(int i = 0; i < data.size(); i++) {
                output.println(calculateRSI(i));
            }
            output.close();
        } catch (IOException e) {
            System.out.println("IOException! Error while creating new file!");
        }
    }

    /**
     * Trade evaluation simulation. A dummy version of what would be
     * the fully finished trade evaluator. Uses an aggressive buy/sell strategy pieced
     * together that would most likely not be viable in real world trading strategies, unfortunately.
     */
    public void tradeEval() {
        double balance = 1000.00;
        double shareBalance = 0;

        System.out.println("Stock Trading! Current balance (USD): $" + balance);
        System.out.println("Current date: " + data.get(0).getDate());
        System.out.println("Buying starting shares (15)... Current price of shares (open): $" + data.get(0).getOpen());
        System.out.println("Value of 15 shares: $" + (data.get(0).getOpen() * 15.00));
        balance = balance - (data.get(0).getOpen() * 15.00);
        shareBalance = data.get(0).getOpen() * 15.00;
        amountOfShares += 15;
        System.out.println("Current balance after buys: $" + balance + ", current balance of shares: $" + shareBalance);
        System.out.println();
        aggroBuySell();
    }

    /**
     * Aggressive Buy/Sell tactic. Looks for parameters to sell stocks if the RSI and money is right,
     * and looks to buy if the RSI dips alongside the interest value.
     * Automates through the entire data set, printing values as each step is taken.
     */
    private void aggroBuySell() {
        System.out.println("AGGRESSIVE BUY/SELL MODE");
        for(int i = 5; i < data.size(); i++) {
            System.out.println("Current date: " + data.get(i).getDate());
            System.out.println("Current RSI: " + calculateRSI(i));
            System.out.println("Current balance: $" + balance);
            dailyInterest = (((data.get(i).getOpen() - data.get(i - 1).getOpen()) / data.get(i - 1).getOpen()));
            System.out.println("Current share balance: $" + (shareBalance * (1.0 + dailyInterest)) + ", amount of shares: " + amountOfShares);
            System.out.println("Would you like to buy or sell?");

            if(calculateRSI(i) > calculateRSI(i - 1) || (shareBalance * (1.0 + dailyInterest)) > 1000) {
                System.out.println("Possible sell...");
                if(dailyInterest > (.1)) {
                    System.out.println("Interest is greater than ten percent increase (" + dailyInterest + ")! Selling 5 shares!");
                    System.out.println("Current share price: $" + data.get(i).getOpen());
                    System.out.println("Price of 5 shares: $" + (data.get(i).getOpen() * 5));
                    balance += data.get(i).getOpen() * 5;
                    shareBalance -= data.get(i).getOpen() * 5;
                    amountOfShares -= 5;
                }
            }
            if(calculateRSI(i) < calculateRSI(i - 1) || openListSmoothed.get(i) < openListSmoothed.get(i - 1)) {
                System.out.println("Possible buy...");
                if(dailyInterest < (-.1)) {
                    System.out.println("Interest is less than ten percent decrease (" + dailyInterest + ")! Buying 5 shares!");
                    System.out.println("Current share price: $" + data.get(i).getOpen());
                    System.out.println("Price of 5 shares: $" + (data.get(i).getOpen() * 5));
                    balance -= data.get(i).getOpen() * 5;
                    shareBalance += data.get(i).getOpen() * 5;
                    amountOfShares += 5;
                }
            }
            System.out.println();
        }
    }


    /**
     * Run the simulation. Populates the given values, plots the RSI, creates
     * the moving average for each data set, and runs the trading simulation.
     */
    public void run() {
        populateValues();
        plotRSI();
        createMA();
        tradeEval();
    }
}