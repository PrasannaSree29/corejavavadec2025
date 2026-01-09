import java.math.BigInteger;

/**
 * Class that represents real world stock being traded in the US stock exchanges
 * Encapsulation is a concept where in the Instance variables of a class are made private and
 * access is restricted via getter and setter
 */
public class Stock {

    //Code in the static block runs only once when the class is engaged either by
    // calling a static method on it or creating objects out of it
    static {
        System.out.println("Inside the static block in the stock class");
    }

    //default constructor
    //static variables are generally used to define constants
    private static final String junkvar = "Whatever";

    /**
     * Some documentation for this junk static method
     * @param someInput
     * @return
     */
    //static methods are used to implement functionality purely based on inputs
    //or to provide utility functionality
    public static String someJunkMethod(String someInput){
        return junkvar+someInput;
    }
    public Stock() {
    }

    /**
     * parameterised Constructor that forces creating a stock with present parameters
     * @param tickerSymbol
     * @param tickerName
     * @param sectorID
     * @param subSectorID
     */
    public Stock(String tickerSymbol, String tickerName, int sectorID, int subSectorID) {
        this.tickerSymbol = tickerSymbol;
        this.tickerName = tickerName;
        this.sectorID = sectorID;
        this.subSectorID = subSectorID;
        //System.out.println("Inside 2nd constructor of stock class");
    }

    private String tickerSymbol; //Instance variable
    private String tickerName;
    private BigInteger marketCap;
    private int sectorID;
    private int subSectorID;
    private double currentRatio;

    //Getter and Setter methods

    public String getTickerSymbol() {
        return tickerSymbol;
    }

//    public void setTickerSymbol(String tickerSymbol) {
//        this.tickerSymbol = tickerSymbol; // this keyword refers to the current object
//    }

    public String getTickerName() {
        return tickerName;
    }

//    public void setTickerName(String tickerName) {
//        this.tickerName = tickerName;
//    }

    public int getSectorID() {
        return sectorID;
    }

//    public void setSectorID(int sectorID) {
//        this.sectorID = sectorID;
//    }

    public BigInteger getMarketCap() {
        return marketCap; //this.marketcap is a instance variable of class, Marketcap is a method
    }

    public void setMarketCap(BigInteger marketCap) {
        System.out.println(someJunkMethod(junkvar));//static  vaiable/method can be used in Instanced method
        this.marketCap = marketCap;
    }

    public int getSubSectorID() {
        return subSectorID;
    }

//    public void setSubSectorID(int subSectorID) {
//        this.subSectorID = subSectorID;
//    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(double currentRatio) {
        this.currentRatio = currentRatio;
    }


    /**
     * Write an instance method in Stock class that prints all the values of instance varibles
     */
    public void printAllVariables(){
        System.out.println("Ticker Symbol: " + tickerSymbol);
        System.out.println("Ticker Name: " + tickerName);
        System.out.println("Sector Id: " + sectorID);
        System.out.println("Sub Sector Id: " + subSectorID);
        System.out.println("Market Cap: " + marketCap);
        System.out.println("Current Ratio: " + currentRatio);
    }

}
