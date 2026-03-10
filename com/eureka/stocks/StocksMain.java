package com.eureka.stocks;

import com.eureka.stocks.dao.CompanyLocationsDAO;
import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDAO;
import com.eureka.stocks.dao.StocksPriceHistoryDAO;
import com.eureka.stocks.exception.StockException;
import com.eureka.stocks.service.MarketAnalyticsService;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentalsVO;
import com.eureka.stocks.vo.StocksPriceHistoryVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StocksMain {
    public static void main(String[] args) {
        // try with resources construct
        try (
                LookUpDAO lookUpDAO = new LookUpDAO();
                StockFundamentalsDAO stockFundamentalsDAO = new StockFundamentalsDAO();
                StocksPriceHistoryDAO stocksPriceHistoryDAO = new StocksPriceHistoryDAO();
        ) {
            // Injecting an instance of lookUpDAO to the constructor of MarketAnalyticsService,
            // as it is a dependency that is needed for the MarketAnalyticsService instance to function
            MarketAnalyticsService

                    marketAnalyticsService = new MarketAnalyticsService(lookUpDAO, stockFundamentalsDAO, stocksPriceHistoryDAO);

           // Get All Sector Information from the database
            List<SectorVO> allSectorsList = marketAnalyticsService.getAllSectors();
            System.out.println("Number of sectors returned from DB is " + allSectorsList.size());
            System.out.println(allSectorsList);

            // Get SubSector details from the database for the subSectorID listed above
            int subSectorID = 283;
            SubSectorVO subSector = marketAnalyticsService.getSingleSubSector(subSectorID);
            System.out.println("SubSector retrieved is " + subSector);

            // Query the STOCK_FUNDAMENTALS table and print the data.
            // We need the columns TICKER_SYMBOL, SECTOR_ID, MARKET_CAP, CURRENT_RATIO
            List<StockFundamentalsVO> stockFundamentalsVOList = marketAnalyticsService.getStockFundamentals();
            System.out.println("============================================");
            System.out.printf("We have %d records in the Stock Fundamentals result set\n", stockFundamentalsVOList.size());
            // System.out.println("Result Set:");
            // for (StockFundamentalsVO stockFundamentalsVO : stockFundamentalsVOList) {
            //     System.out.println(stockFundamentalsVO);
            // }

            // Get All Subsectors
            List<SubSectorVO> allSubSectorsList = marketAnalyticsService.getAllSubSectors();
            System.out.println("# of subsectors of the economy are "+allSubSectorsList.size());


            // Get Price History for AMD between 2 dates: fromDate and toDate
            LocalDate fromDate = LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = fromDate.plusMonths(1);
            String tickerSymbol = "AMD";
            List<StocksPriceHistoryVO> amdStocksPriceHistory = marketAnalyticsService.getStockPriceHistoryByTickerSymbolAndDates(tickerSymbol, fromDate, toDate);
            System.out.println("amdStocksPriceHistory.size() = "+amdStocksPriceHistory.size());

            //Deal with Healthcare Stocks
            marketAnalyticsService.dealingWithHealthCareStocks();

            //Per Sector method
            marketAnalyticsService.splitStocksBySector();

            //Identify BlueChips
            marketAnalyticsService.identifyBlueChips();

            //Calculate Small Cap MktCap Totals
            marketAnalyticsService.calculateSmallCapsMktCapTotal();

            //Calculate Avg Market Cap by SubSector
            marketAnalyticsService.getAverageMktCapBySubSector();

            // sector names and number of stocks in each sector
            // coolway
            marketAnalyticsService.getSectorStocksCountCoolWay();



           LocalDate anotherFromDate =  LocalDate.parse("2018-01-01",DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            //For each year, Calculate the lowest price for a given stock
            marketAnalyticsService.getLowestPriceForGivenStock("INTC", anotherFromDate, anotherFromDate.plusYears(7));

            //StreamsRecap
            marketAnalyticsService.streamsRecap();
            marketAnalyticsService.dealingWithMyTantrums();
           // marketAnalyticsService.getHiLoMktCapByState();

            //Practice Demo
            marketAnalyticsService.getAllSectorsNew();

            //CompanyLocationsDAO companyLocationsDAO = new CompanyLocationsDAO();
           // companyLocationsDAO.getCompanyAddressByTicker("NVDA");

//
//            CompanyLocationsDAO dao = new CompanyLocationsDAO();
//            dao.getCompaniesByState("CA");

            LocalDate performanceFromDate = LocalDate.parse("2022-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //Performance requirement
            marketAnalyticsService.calculateStockPerformanceByState(performanceFromDate, performanceFromDate.plusYears(3));


        } catch (StockException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}