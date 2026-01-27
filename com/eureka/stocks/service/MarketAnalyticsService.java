package com.eureka.stocks.service;
import com.eureka.stocks.vo.*;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDAO;
import com.eureka.stocks.dao.StocksPriceHistoryDAO;
import com.eureka.stocks.sorting.SubSectorNameComparator;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentalsVO;
import com.eureka.stocks.vo.StocksPriceHistoryVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class MarketAnalyticsService {
    // Instance variable
    // It's also a dependency because
    // MarketAnalyticsService will not work unless LookupDAO object is provided
    private LookUpDAO lookUpDAO;
    private StockFundamentalsDAO stockFundamentalsDAO;
    private StocksPriceHistoryDAO stocksPriceHistoryDAO;

    /**
     * Parameterized constructor that forces an instance of lookUpDao to be provided for the MarketAnalyticsService to function
     *
     * @param lookUpDAO
     */
    public MarketAnalyticsService(LookUpDAO lookUpDAO, StockFundamentalsDAO stockFundamentalsDAO, StocksPriceHistoryDAO stocksPriceHistoryDAO) {
        this.lookUpDAO = lookUpDAO;
        this.stockFundamentalsDAO = stockFundamentalsDAO;
        this.stocksPriceHistoryDAO = stocksPriceHistoryDAO;
    }

    /**
     * Business method that fetches all Sectors from the database
     *
     * @return
     */
    public List<SectorVO> getAllSectors() {
        List<SectorVO> allSectorsList = lookUpDAO.getAllSectors();

        //Use on List with 1-1 relational mapping between instance variables
        Map<Integer, String> sectorMap = allSectorsList.stream()
                .collect(Collectors.toMap( //Generating a map from the list
                        SectorVO::getSectorID, //keyMapping function
                        SectorVO::getSectorName //ValueMapping function
                ));

        Collections.sort(allSectorsList);
        return allSectorsList;
    }


    /**
     * Business method that fetches single SubSector from the database as per the input provided
     *
     * @param subSectorID
     * @return
     */
    public SubSectorVO getSingleSubSector(int subSectorID) {
        return lookUpDAO.getSubSector(subSectorID);
    }

    public List<StockFundamentalsVO> getStockFundamentals() {
        List<StockFundamentalsVO> stockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        // Collections.sort(stockFundamentalsList, new SFMarketCapAscComparator().reversed());

        // Sort by Market Cap using Anonymous Inner Class
        // stockFundamentalsList.sort(new Comparator<StockFundamentalsVO>() {
        //     @Override
        //     public int compare(StockFundamentalsVO o1, StockFundamentalsVO o2) {
        //         return o2.getMarketCap().compareTo(o1.getMarketCap());
        //     }
        // });

        // Same comparator using lambda function
        // stockFundamentalsList.sort((o1, o2) -> o2.getMarketCap().compareTo(o1.getMarketCap()));

        // using lambda
        // stockFundamentalsList.sort(
        //         Comparator.comparing(
        //                 (StockFundamentalsVO stockFundamentalsVO) -> stockFundamentalsVO.getMarketCap()
        //         ).reversed()
        // );

        // using method reference
        // stockFundamentalsList.sort(Comparator.comparing(StockFundamentalsVO::getMarketCap).reversed());

        // sort by market cap desc, and then by ticker symbol desc
        stockFundamentalsList.sort(
                Comparator.comparing(StockFundamentalsVO::getMarketCap).reversed()
                        .thenComparing(Comparator.comparing(StockFundamentalsVO::getTickerSymbol).reversed())
        );
        return stockFundamentalsList;
    }

    public List<SubSectorVO> getAllSubSectors() {
        List<SubSectorVO> allSubSectorsList = lookUpDAO.getAllSubSectors();
        // Natural Order
        Collections.sort(allSubSectorsList);

        // Other order, sort by SubsectorName
        Collections.sort(allSubSectorsList, new SubSectorNameComparator());
        allSubSectorsList.sort(new SubSectorNameComparator().reversed());

        // sort by sector id, then subsector name desc
        allSubSectorsList.sort(
                Comparator.comparing(SubSectorVO::getSectorID)
                        .thenComparing(Comparator.comparing(SubSectorVO::getSubSectorName).reversed())
        );

        return allSubSectorsList;
    }

    public List<StocksPriceHistoryVO> getStockPriceHistoryByTickerSymbolAndDates(String tickerSymbol, LocalDate fromDate, LocalDate toDate) {
        List<StocksPriceHistoryVO> priceHistoryList = stocksPriceHistoryDAO.getPriceHistoryForStock(tickerSymbol, fromDate, toDate);

        // SQL Equivalent is order close_price desc, high_price, trading_date desc
        priceHistoryList.sort(
                Comparator.comparing(StocksPriceHistoryVO::getClosePrice).reversed()
                        .thenComparing(StocksPriceHistoryVO::getHighPrice)
                        .thenComparing(
                                Comparator.comparing(StocksPriceHistoryVO::getTradingDate).reversed()
                        )
        );
        return priceHistoryList;
    }

    public void dealingWithHealthCareStocks() {

        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        List<StockFundamentalsVO> healthCareList = new ArrayList<>();

        //getting only Healthcare stocks -oldway
        allStockFundamentalsList.forEach(stockFundamentalsVO -> {
            if (stockFundamentalsVO.getSectorID() == 34) {
                healthCareList.add(stockFundamentalsVO);
            }
        });
        System.out.println("healthcare list size is" + healthCareList.size());

        //getting only Healthcare stocks- coolway
        List<StockFundamentalsVO> coolHealthCareList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() != 0)
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .sorted(Comparator.comparing(StockFundamentalsVO::getMarketCap).reversed())
                .collect(Collectors.toList());
        System.out.println("Cool healthcare List is" + coolHealthCareList.size());

        //Count No of Healthcare stocks
        long countHealthcareStocks = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .count();
        System.out.println("no of health care stocks is " + countHealthcareStocks);

        //List of all ticker symbols for healthcare stocks
        List<String> healthcareTickersList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .map(StockFundamentalsVO::getTickerSymbol)// Using method reference
                //.map(stockFundamentalsVO -> stockFundamentalsVO.getTickerSymbol()) //Using Lambda expression
                .sorted(Comparator.reverseOrder())//reverse  the natural order
                .sorted(Comparator.comparing(String::toString).reversed()) //another way of sorting by tickeSYMBOL desc
                .collect((Collectors.toList()));
        System.out.println(healthcareTickersList);

//        allStockFundamentalsList.stream()
//                .map(StockFundamentalsVO::getTickerSymbol);

        //Get a comma seperated list of healthcare tickersymbols


        String allTickersString = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .map(StockFundamentalsVO::getTickerSymbol)
                .collect(Collectors.joining(","));
        System.out.println("All ticker string is" + allTickersString);

        //Calculate the total marketcap for all healthcare stocks
        Optional<BigDecimal> totalMktCapOptional = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .map(StockFundamentalsVO::getMarketCap)
                // .reduce((a,b)->a.add(b));// Reduce terminal Using Lambda Expression
                .reduce(BigDecimal::add);//reduce terminal function Using method referance
        totalMktCapOptional.ifPresent(x -> System.out.println("total market cap of healthcare stocks is " + x));

    }

    public void splitStocksBySector() {
        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        Map<Integer, List<StockFundamentalsVO>> perSectorMap = allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVO::getSectorID));//Split original list into sublists based on sectorID

    }
    // Method to identify Blue Chip Stocks. Blue Chips have a market cap > 10 Billion USD.
    // Get total market cap of Blue Chips

    public void identifyBlueChips() {
        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        List<StockFundamentalsVO> blueChipsStocksList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getMarketCap().compareTo(new BigDecimal("10000000000")) > 0)
                .sorted(Comparator.comparing(StockFundamentalsVO::getMarketCap))
                .collect(Collectors.toList());
        System.out.println("# of Bluechips stocks are " + blueChipsStocksList.size());

        Optional<BigDecimal> blueChipsMKTotalOptional = blueChipsStocksList.stream()
                .map(StockFundamentalsVO::getMarketCap)
                .reduce(BigDecimal::add);
        blueChipsMKTotalOptional.ifPresent(x -> System.out.println("Total of blue chips market cap is" + x));

    }

    //Method to identify Small Cap Stocks. Small Caps have a market cap between 250 Million and 2 Billion//
    // Find market cap total for Small caps
    public void calculateSmallCapsMktCapTotal() {

        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        Optional<BigDecimal> smallCapTotalOptional = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getMarketCap().compareTo(new BigDecimal("250000000")) > 0)
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getMarketCap().compareTo(new BigDecimal("2000000000")) < 0)
                .map(StockFundamentalsVO::getMarketCap)
                .reduce(BigDecimal::add);
        smallCapTotalOptional.ifPresent(x -> System.out.println("Total marketcap of all small caps is" + x));

    }
    //get the average marketcap for each subsector of the economy. show subsectorName along with its average market cap

    public void getAverageMktCapBySubSector() {
        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        List<SubSectorVO> allSubsectorList = lookUpDAO.getAllSubSectors();
        //Generate a map with each entry representing a Unique subsector, with key being subsectorID and value SubsectorName
        Map<Integer, String> subSectorNameByIDMap = allSubsectorList.stream()
                .collect(Collectors.toMap(
                        SubSectorVO::getSubSectorID,
                        SubSectorVO::getSubSectorName
                ));


        Map<String, BigDecimal> finalOutputMap = new HashMap<>();
        //map which contains sublist of stocks objects belonging to each subsectorID
        Map<Integer, List<StockFundamentalsVO>> subSectorMap = allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVO::getSubSectorID));

        subSectorMap.forEach((subSectorID, stocksList) -> {
            Optional<BigDecimal> subSectorTotalOptional = stocksList.stream()
                    .map(StockFundamentalsVO::getMarketCap)
                    .reduce(BigDecimal::add);
            subSectorTotalOptional.ifPresent(subSectorTotal -> {
                finalOutputMap.put(subSectorNameByIDMap.get(subSectorID), subSectorTotal.divide(new BigDecimal(stocksList.size()), 2, RoundingMode.HALF_UP));
            });
        });
        System.out.println(finalOutputMap);

    }


//    Print sector names and number of stocks in each sector:
//    Healthcare: 510
//    Basic Materials: 108
//    Financial Services: 486
//    Industrials: 391
//    Technology: 368
    public void getSectorStocksCount(){
        List<StockFundamentalsVO> allStockFundamentalsList =stockFundamentalsDAO.getStockFundamentals();
        List<SectorVO> allSectorsList =lookUpDAO.getAllSectors();
        Map<String,Long> finalOutputMap =new TreeMap<>();
        Map<Integer, String> sectorNameByIDMap = allSectorsList.stream()
                .collect(Collectors.toMap(
                        SectorVO::getSectorID,
                        SectorVO::getSectorName
                ));

        Map<Integer, Long> sectorCountByIDMap =allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVO::getSectorID,Collectors.counting()));

        sectorCountByIDMap.forEach((sectorID, stocksCount)->{
            finalOutputMap.put(sectorNameByIDMap.get(sectorID),stocksCount);
        });
        System.out.println(finalOutputMap);
    }

    //    Print sector names and number of stocks in each sector:
//    Healthcare: 510
//    Basic Materials: 108
//    Financial Services: 486
//    Industrials: 391
//    Technology: 368
    public void getSectorStocksCountCoolWay(){
        List<StockFundamentalsVO> allStockFundamentalsList =stockFundamentalsDAO.getStockFundamentals();
        List<SectorVO> allSectorsList =lookUpDAO.getAllSectors();

        Map<Integer, String> sectorNameByIDMap = allSectorsList.stream()
                .collect(Collectors.toMap(
                        SectorVO::getSectorID,
                        SectorVO::getSectorName
                ));

        Map<String, Integer> finalOutputMap = new TreeMap<>();

        //Using compute If Absent
        allStockFundamentalsList.forEach(stockFundamentalsVO -> {
                    int count= finalOutputMap.computeIfAbsent(sectorNameByIDMap.get(stockFundamentalsVO.getSectorID()),(key)->0)+1;
                    finalOutputMap.put(sectorNameByIDMap.get(stockFundamentalsVO.getSectorID()),count);
                });
    }

    /**
     * For each year, Calculate the lowest price for a given stock
     * @param tickerSymbol
     * @param fromDate
     * @param toDate
     */
    public void getLowestPriceForGivenStock(String tickerSymbol, LocalDate fromDate, LocalDate toDate){
        List<StocksPriceHistoryVO> priceHistoryList = stocksPriceHistoryDAO.getPriceHistoryForStock(tickerSymbol, fromDate, toDate);

        //Perform groupby on the list by trading year and get the map with year as the key and list of pricehistory for that year as value
        Map<Integer, List<StocksPriceHistoryVO>> priceHistoryListByYearMap = priceHistoryList.stream()
                .collect(Collectors.groupingBy((stocksPriceHistoryVO -> stocksPriceHistoryVO.getTradingDate().getYear())));

        Map<Integer, StocksPriceHistoryVO> finalOutputMap = new TreeMap<>();

        priceHistoryListByYearMap.forEach((year,stockList)->{
            //for each entry in the map, identify the object that has the lowest value by close price
            Optional<StocksPriceHistoryVO> lowestCPOptional = stockList.stream()
                    .min(Comparator.comparing(StocksPriceHistoryVO::getClosePrice));
//                    .sorted(Comparator.comparing(StocksPriceHistoryVO::getClosePrice))
//                    .findFirst();
            lowestCPOptional.ifPresent(lowestCP->finalOutputMap.put(year,lowestCP));
        });
        System.out.println(finalOutputMap);

    }


    public void streamsRecap() {

        //Generating a sublist from a given list using filter
        List<StockFundamentalsVO>  allStockFundamentalsList = stockFundamentalsDAO.getStockFundamentals();
        List<SectorVO> allSectorsList = lookUpDAO.getAllSectors();
        List<SubSectorVO> allSubSectorsList = lookUpDAO.getAllSubSectors();

        List<StockFundamentalsVO>  healthCareList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID()==34)
                .collect(Collectors.toList());
        //sort healthcare stocks by current ratio
        allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID()==34)
                .sorted(Comparator.comparing(StockFundamentalsVO::getCurrentRatio, Comparator.nullsFirst(BigDecimal::compareTo)).reversed())
                .collect(Collectors.toList());

        //Top 5 healthcare stocks by current ratio ranked 2 to 6

        List<String> top5HealthcareTickers = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .sorted(Comparator.comparing(StockFundamentalsVO::getCurrentRatio, Comparator.nullsFirst(BigDecimal::compareTo)).reversed())
                .skip(1)
                .limit(5)
                .map(StockFundamentalsVO::getTickerSymbol)
                .collect(Collectors.toList());

        //convert a 1-1 list to a map

        Map<Integer, String> sectorNameByIDMap = allSectorsList.stream()
                .collect(Collectors.toMap(
                        SectorVO::getSectorID,
                        SectorVO::getSectorName
                ));
        //convert a one to many list to a map
        SectorVO mafiaSector = new SectorVO(45);
        mafiaSector.setSectorName("mafia");
        allSectorsList.add(mafiaSector);

        SectorVO junkSector = new SectorVO(45);
        junkSector.setSectorName("Somejunk");
        allSectorsList.add(junkSector);

        Map<Integer, String> anotherSectorNameByIDMap = allSectorsList.stream()
                .collect(Collectors.toMap(
                        SectorVO::getSectorID,
                        SectorVO::getSectorName,
                        (x, y) -> x.concat(y) //Binary operator Merge value Function that resolves the values that go into the Map entry
                ));
        
        //Extract a single string with all sectorNames comma seperated
        String allSectorNamesString = allSectorsList.stream()
                .map(SectorVO::getSectorName)
                .sorted()
                .collect(Collectors.joining(","));

        //Calculate total market cap for HealthCare stocks

        Optional<BigDecimal> healthCareTotalOptional = healthCareList.stream()
                .map(StockFundamentalsVO::getMarketCap)
                .reduce((a, b) -> a.add(b));
        // .reduce(BigDecimal::add); //Method referance

        healthCareTotalOptional.ifPresent(System.out::println);
        
        //Split all stock price history by Month for AMD
        LocalDate fromDate = LocalDate.parse("2024-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<StocksPriceHistoryVO> amdPriceHistoryList = stocksPriceHistoryDAO.getPriceHistoryForStock("AMD", fromDate, fromDate.plusYears(1));
        Map<Month, List<StocksPriceHistoryVO>> priceHistoryByMonthMap = new TreeMap(amdPriceHistoryList.stream()
                .collect(Collectors.groupingBy(stocksPriceHistoryVO -> stocksPriceHistoryVO.getTradingDate().getMonth())));


    }
    /**
     * --  During the period of 2022 to 2024, identify the top performing stock for each state in each year and its performance
     * --  Best performing stock is % of growth between Closing Price EOY and Opening Price SOY
     */
    public void calculateStockPerformanceByState(LocalDate fromDate, LocalDate toDate){

        Instant start = Instant.now();
        List<StocksPriceHistoryVO> priceHistoryList = stocksPriceHistoryDAO.getStockPriceHistoryAllStocks(fromDate, toDate);
        priceHistoryList.sort(
                Comparator.comparing(StocksPriceHistoryVO::getTickerSymbol)
                        .thenComparing(Comparator.comparing(StocksPriceHistoryVO::getTradingDate).reversed()));

        //Group the list by Trading year
        Map<Integer, List<StocksPriceHistoryVO>> priceHistoryListByYearMap = priceHistoryList.stream()
                .collect(Collectors.groupingBy(stocksPriceHistoryVO -> stocksPriceHistoryVO.getTradingDate().getYear()));

        //The key of our map is a custom object that we created
        Map<TickerByYearVO, List<StocksPriceHistoryVO>> priceHistoryListByTckYrMap = new HashMap<>();

        //For every year, get the Price History of that year, group by Ticker Symbol again, and then put it into the priceHistoryListByTckYrMap
        priceHistoryListByYearMap.forEach((year, phList) -> {

            //This map contains Price History for a given ticker Symbol for a given year
            Map<String, List<StocksPriceHistoryVO>> phListByTickerMap = phList.stream()
                    .collect(Collectors.groupingBy(StocksPriceHistoryVO::getTickerSymbol));

            phListByTickerMap.forEach((ticker, phTickerList) -> {
                TickerByYearVO tickerByYearVO = new TickerByYearVO(year, ticker);
                priceHistoryListByTckYrMap.put(tickerByYearVO, phTickerList);
            });
        });

        List<StockPerformanceVO> performanceList = new ArrayList<>();

//        Map<newVO, List<PriceHistory> -> get list -> Identify phO
//        with lowest date and highest date and get prices from them
//        and into a performanceVo object
        priceHistoryListByTckYrMap.forEach(((tickerByYearVO, phByYearTkrList) ->{
            //Optional contains a StockPrice History object for the max Trading date for that year and ticker
            Optional<StocksPriceHistoryVO> maxTradingDateOptional = phByYearTkrList.stream()
                    .max(Comparator.comparing(StocksPriceHistoryVO::getTradingDate));
            //Optional contains a StockPrice History object for the min Trading date for that year and ticker
            Optional<StocksPriceHistoryVO> minTradingDateOptional = phByYearTkrList.stream()
                    .min(Comparator.comparing(StocksPriceHistoryVO::getTradingDate));
            if(maxTradingDateOptional.isPresent() && minTradingDateOptional.isPresent()){
                StockPerformanceVO stockPerformanceVO = new StockPerformanceVO();
                stockPerformanceVO.setYear(tickerByYearVO.getYear());
                stockPerformanceVO.setTickerSymbol(tickerByYearVO.getTickerSymbol());
                stockPerformanceVO.setState(maxTradingDateOptional.get().getState());

                //Performance calculation 100*(CPYE - OPSOY)/OPSOY
                BigDecimal performance = new BigDecimal(100).multiply((maxTradingDateOptional.get().getClosePrice()
                                .subtract(minTradingDateOptional.get().getOpenPrice())))
                        .divide(minTradingDateOptional.get().getOpenPrice(), 2, RoundingMode.HALF_UP);
                stockPerformanceVO.setPerformance(performance);
                performanceList.add(stockPerformanceVO);
            }

            Collections.sort(performanceList);

        } ));

        List<StockPerformanceVO> finalOutputList = new ArrayList<>();

        //Split performance List by year
        Map<Integer, List<StockPerformanceVO>> performanceByYearMap = performanceList.stream()
                .collect(Collectors.groupingBy(StockPerformanceVO::getYear));
        //performanceByYearMap contains List of performance metrics for a given Year
        performanceByYearMap.forEach((year, perfList) -> {
            //perfByStateMap which is temporary that contains List of performance metrics for a each Year and state
            Map<String, List<StockPerformanceVO>> perfByStateMap = perfList.stream()
                    .collect(Collectors.groupingBy(StockPerformanceVO::getState));
            //For each state and year, find the performance record with the max performance value
            perfByStateMap.forEach((stateCode, perfStateList) -> {
                Optional<StockPerformanceVO> byStateTopPerformingStockOptinal = perfStateList.stream()
                        .max(Comparator.comparing(StockPerformanceVO::getPerformance));
                byStateTopPerformingStockOptinal.ifPresent(stockPerformanceVO -> finalOutputList.add(stockPerformanceVO));
            });
        });
        Collections.sort(finalOutputList);

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Time taken to complete the requirement is "+timeElapsed);
        System.out.println(finalOutputList);
    }


}
