/**
 * First class written in Java that introduces primitive dataTypes and simple methods
 */
double d2 = 14.98;
        System.out.println("Product of d1 and d2 is "+multipleDoubleValues(d1,d2));

BigDecimal bdD1 = new BigDecimal("13.56");
BigDecimal bdD2 = new BigDecimal("14.98");
        System.out.println("Product of d1 and d2 using BigDecimal is "+bdD1.multiply(bdD2));

//        float floatSum = sumFloatMethod();//Calling code taking the return value of sumFloatMethod and assigning it to floatSum variable
//        System.out.println("Sum of floats f1 and f2 is "+floatSum);
float f1 = 53.25f;
char secondChar = 'y';
        System.out.println("Concat of 2 chars firstCharX and secondChar is "+firstCharX+secondChar);

playingWithObjects();

    }

private static void playingWithObjects() {
    //Instantiated a reference variable called appleStock of the type Stock
    //Assigning appleStock reference variable to a new instance of Stock class
//        Stock appleStock = new Stock(); //Default constructor was called
//        appleStock.setTickerSymbol("AAPL");
//        appleStock.setTickerName("Apple Inc.");
//        appleStock.setSectorID(34);
//        appleStock.setSubSectorID(176);
    Stock appleStock = new Stock("AAPL","Apple Inc.",35,176);
    BigInteger appleMarketCap = new BigInteger("3400000000000");
    appleStock.setMarketCap(appleMarketCap);
    appleStock.setCurrentRatio(3.83);
    System.out.println("Apple Stock values are "+appleStock.getTickerSymbol()+", ");

    Stock googleStock = new Stock("GOOG","Alphabet Inc",36,235);
    googleStock.setMarketCap(new BigInteger("2500000000000"));
    googleStock.setCurrentRatio(1.97);
    System.out.println("Google Stock Values are "+googleStock.getTickerSymbol()+",");

    double googleCurrentRatio = googleStock.getCurrentRatio();
    testPassByValueReference(googleStock, googleCurrentRatio);
    System.out.println("Value of googleCurrentRatio is "+googleCurrentRatio);
    System.out.println("Google Stock Values are "+googleStock.getTickerSymbol()+","+googleStock.getCurrentRatio());
}

/**
 * Method that is used to demonstrate pass by reference and pass by value and the manifestation of changes in values
 * @param stock Pass by Reference, that changes the underlying object
 * @param currentRatio Pass by Value, that generates another local variable with the same primitive value
 */
private static void testPassByValueReference(Stock stock, double currentRatio){
    //stock = new Stock();
    stock.setCurrentRatio(2.55);
    currentRatio = 6.5;
    System.out.println("Current ratio from method parameter is "+currentRatio);
}
