/**
 * Class that represents a real world Stock being traded in the US Stock Exchanges
 * Encapsulation is a concept where in the Instance variables of a class are made private and
 * access is restricted via getter and setter methods
 */
public class Stock {

    //Default constructor
    private Stock() {
    }

    /**
     * Constructor that forces creating a Stock with preset parameters
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
}
