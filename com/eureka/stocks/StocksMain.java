package com.eureka.stocks;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDAO;
import com.eureka.stocks.exception.StockException;
import com.eureka.stocks.service.MarketAnalyticsService;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentalsVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.util.List;

public class StocksMain {
    public static void main(String[] args) {
        try(LookUpDAO lookUpDAO = new LookUpDAO()){ //try-with resources construct
            try(LookUpDAO lookUpDAO = new LookUpDAO();
                StockFundamentalsDAO stockFundamentalsDAO = new StockFundamentalsDAO()){ //try-with resources construct

                //Injecting an instance of LookUpDAO to the constructor of MarketAnalyticsService, as it is a dependency that
                //is needed for the MarketAnalyticsService instance to function
                MarketAnalyticsService marketAnalyticsService = new MarketAnalyticsService(lookUpDAO);
                MarketAnalyticsService marketAnalyticsService = new MarketAnalyticsService(lookUpDAO, stockFundamentalsDAO);

                //Get All Sector Information from the database
                List<SectorVO> allSectorsList = marketAnalyticsService.getAllSectors();
                //Get SubSector details from the database for the subSectorID listed above
                SubSectorVO subSector = marketAnalyticsService.getSingleSubSector(subSectorID);
                System.out.println("SubSector retrieved is "+subSector);
            } catch (Exception e) {

                //Get All Stock Fundamentals
                List<StockFundamentalsVO> allStockFundamemntalsList = marketAnalyticsService.getAllStockFundamemntals();
                System.out.println("Number of SFundamentals retrieved from the DB is "+allStockFundamemntalsList.size());

                //Get All SubSectors
                List<SubSectorVO> allSubSectorsList = marketAnalyticsService.getAllSubSectors();
                System.out.println("# of SubSectors of the economy are "+allSubSectorsList.size());

            } catch (StockException e){
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
