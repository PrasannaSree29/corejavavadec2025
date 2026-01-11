import java.math.BigInteger;
import java.util.Objects;

/**
 * Class that represents a real world Stock being traded in the US Stock Exchanges
 * Encapsulation is a concept where in the Instance variables of a class are made private and
 * access is restricted via getter and setter methods
 */
public class Stock {

    //Code in the static block runs only once, the first time the Class is engaged - Either by calling a static method on it or creating objects out of it
    static {
        System.out.println("Inside the static block in the Stock class");
    }

    //Static variables are generally used to define constants
    private static final String junkVar = "Whatever";

    /**
     * Some documentation for this junk Static method
     * @param someInput
     * @return
     */
    //Static methods are used to implement functionality purely based on inputs or to provide utility functionality
    public static void someJunkMethod(String someInput){
        System.out.println(junkVar+someInput);
    }

    //Default constructor
    private Stock() {
    }

    /**
     * Parameterized Constructor that forces creating a Stock with preset parameters
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
    }

    private String tickerSymbol; //Instance Variable
    private String tickerName;
    private BigInteger marketCap;
    private int sectorID;
    private int subSectorID;
    private double currentRatio;

    //Getter and Setter methods
    public String getTickerSymbol() {
        return tickerSymbol;
    }

//    public void setTickerSymbol(String tickerSymbolFromOutside) {
//        this.tickerSymbol = tickerSymbolFromOutside; //this keyword refers to the current object
//    }

    public String getTickerName() {
        return tickerName;
    }

//    public void setTickerName(String tickerName) {
//        this.tickerName = tickerName;
//    }

    public BigInteger getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigInteger marketCap) {
        //System.out.println(someJunkMethod(junkVar));//Static variable/method can be used in Instanced method
        this.marketCap = marketCap; //this.marketCap is a Instance Variable of class, marketCap is a method parameter
    }

    public int getSectorID() {
        return sectorID;
    }

//    public void setSectorID(int sectorID) {
//        this.sectorID = sectorID;
//    }

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

    @Override
    public String toString() {
        return "Stock{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tickerName='" + tickerName + '\'' +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(tickerSymbol, stock.tickerSymbol) && Objects.equals(tickerName, stock.tickerName);
    }

//    /**
//     * Our own implementation of the equals method
//     * @param outsideObject   the reference object with which to compare.
//     * @return
//     */
//    @Override
//    public boolean equals(Object outsideObject){
//        boolean isEquals = false;
//        if(outsideObject!=null && (outsideObject instanceof Stock)) { //Making sure outside Object is not null and of type Stock
//            Stock outsideStockObj = (Stock)outsideObject; //typecasting from generic object to Stock
//            if(this.tickerSymbol.equals(outsideStockObj.getTickerSymbol())
//                    && this.tickerName.equals(outsideStockObj.getTickerName())){ //Checking if tickerSymbol and tickerName values in both objects are the same
//                isEquals = true;
//            }
//        }
//        return isEquals;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(tickerSymbol, tickerName);
    }
}
