package com.eureka.stocks.service;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDAO;
import com.eureka.stocks.dao.StocksPriceHistoryDAO;
import com.eureka.stocks.sorting.SubSectorNameComparator;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentalsVO;
import com.eureka.stocks.vo.StocksPriceHistoryVO;
import com.eureka.stocks.vo.SubSectorVO;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        Collections.sort(allSectorsList);
        return allSectorsList;
    }
    //Use on List with 1-1 relational mapping between instance variables
    Map<Integer, String> sectorMap = allSectorsList.stream()
            .collect(Collectors.toMap( //Generating a map from the list
                    SectorVO::getSectorID, //keyMapping function
                    SectorVO::getSectorName //ValueMapping function
            ));


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
                        .thenComparing(Comparator.comparing(SubSectorVO::getSubsectorName).reversed())
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

    public void dealingWithHealthCareStocks(){

        List<StockFundamentalsVO> allStockFundamentalsList = StockFundamentalsDAO.getAllStocksFundamentals();
        List<StockFundamentalsVO> healthCareList = new ArrayList<>();

        //getting only Healthcare stocks -oldway
        allStockFundamentalsList.forEach(stockFundamentalsVO -> {
            if(stockFundamentalsVO.getSectorID()==34){
                healthCareList.add(stockFundamentalsVO);
            }
        });
        System.out.println("healthcare list size is"+healthCareList.size());

        //getting only Healthcare stocks- coolway
        List<StockFundamentalsVO> coolHealthCareList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() != 0)
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .sorted(Comparator.comparing(StockFundamentalsVO::getMarketCap).reversed())
                .collect(Collectors.toList());
        System.out.println("Cool healthcare List is"+coolHealthCareList.size());

        //Count No of Healthcare stocks
        long countHealthcareStocks = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID() == 34)
                .count();
        System.out.println("no of health care stocks is "+countHealthcareStocks);

        //List of all ticker symbols for healthcare stocks
        List<String> healthcareTickersList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID()==34)
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
        System.out.println("All ticker string is" +allTickersString);

        //Calculate the total marketcap for all healthcare stocks
        Optional<BigDecimal> totalMktCapOptional = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getSectorID()==34)
                .map(StockFundamentalsVO::getMarketCap)
               // .reduce((a,b)->a.add(b));// Reduce terminal Using Lambda Expression
                       .reduce(BigDecimal::add);//reduce terminal function Using method referance
        totalMktCapOptional.ifPresent(x-> System.out.println("total market cap of healthcare stocks is "+x));

    }
    public void splitStocksBySector(){
        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getAllStockFundamentals();
        Map<Integer,List<StockFundamentalsVO>> perSectorMap = allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVO::getSectorID));//Split original list into sublists based on sectorID

    }
   // Method to identify Blue Chip Stocks. Blue Chips have a market cap > 10 Billion USD.
    // Get total market cap of Blue Chips

    public void identifyBluechips(){
        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getAllStockFundamentals();
        List<StockFundamentalsVO> blueChipsStocksList = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getMarketCap().compareTo(new BigDecimal("10000000000"))>0)
                .sorted(Comparator.comparing(StockFundamentalsVO::getMarketCap))
                .collect(Collectors.toList());
        System.out.println("# of Bluechips stocks are "+blueChipsStocksList.size());

        Optional<BigDecimal> blueChipsMKTotalOptional = blueChipsStocksList.stream()
                .map(StockFundamentalsVO::getMarketCap)
                .reduce(BigDecimal::add);
        blueChipsMKTotalOptional.ifPresent(x-> System.out.println("Total of blue chips market cap is"+x));

    }

    //Method to identify Small Cap Stocks. Small Caps have a market cap between 250 Million and 2 Billion//
    // Find market cap total for Small caps
    public void calculateSmallCapsMktCapTotal() {

        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getAllStockFundamentals();
        Optional<BigDecimal> smallCapTotalOptional = allStockFundamentalsList.stream()
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getMarketCap().compareTo(new BigDecimal("250000000")) > 0)
                .filter(stockFundamentalsVO -> stockFundamentalsVO.getMarketCap().compareTo(new BigDecimal("2000000000")) < 0)
                .map(StockFundamentalsVO::getMarketCap)
                .reduce(BigDecimal::add);
        smallCapTotalOptional.ifPresent(x -> System.out.println("Total marketcap of all small caps is" + x));

    }
    //get the average marketcap for each subsector of the economy. show subsectorName along with its average market cap

    public void getAverageMktCapBySubSector(){
        List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getAllStockFundamentals();
        List<SubSectorVO> allSubsectorList = lookUpDAO.getAllSubSectors();
        //Generate a map with each entry representing a Unuque subsector, with key being subsectorID and value SubsectorName
        Map<Integer, String> subSectorNameByIDMap = allSubSectorsList.stream()
                .collect(Collectors.toMap(
                        SubSectorVO::getSubSectorID,
                        SubSectorVO::getSubSectorName
                ));


        Map<String, BigDecimal> finalOutputMap = new HashMap<>();
        //map which contains sublist of stocks objects belonging to each subsectorID
        Map< Integer, List<StockFundamentalsVO>> subSectorMap = allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVO::getSubSectorID));

        subSectorMap.forEach((subSectorID, stocksList)->{
            Optional<BigDecimal> subSectorTotalOptional = stocksList.stream()
                    .map(StockFundamentalsVO::getMarketCap)
                    .reduce(BigDecimal::add);
            subSectorTotalOptional.ifPresent(subSectorTotal->{
                finalOutputMap.put(subSectorNameByIDMap.get(subSectorID),subSectorTotal);
            });
        });
        System.out.println(finalOutputMap);

    }
}
