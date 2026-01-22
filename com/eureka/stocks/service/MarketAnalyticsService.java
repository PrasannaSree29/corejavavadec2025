package com.eureka.stocks.service;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDAO;
import com.eureka.stocks.dao.StocksPriceHistoryDAO;
import com.eureka.stocks.sorting.SubSectorNameComparator;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentalsVO;
import com.eureka.stocks.vo.StocksPriceHistoryVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
}
