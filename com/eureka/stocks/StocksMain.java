package com.eureka.stocks;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.service.MarketAnalyticsService;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentals;
import com.eureka.stocks.vo.SubSectorVO;

import java.util.List;

public class StocksMain {
    public static void main(String[] args) {
        try(LookUpDAO lookUpDAO = new LookUpDAO()){ //try-with resources construct

            //Injecting an instance of LookUpDAO to the constructor of MarketAnalyticsService, as it is a dependency that
            //is needed for the MarketAnalyticsService instance to function
            MarketAnalyticsService marketAnalyticsService = new MarketAnalyticsService(lookUpDAO);

            //Get All Sector Information from the database
            List<SectorVO> allSectorsList = marketAnalyticsService.getAllSectors();
            System.out.println("NUmber of sectors returned from db is "+allSectorsList.size());

            int subSectorID = 283;
            //Get SubSector details from the database for the subSectorID listed above
            SubSectorVO subSector = marketAnalyticsService.getSingleSubSector(subSectorID);
            System.out.println("SubSector retrieved is "+subSector);

            List<StockFundamentals> allstockFundamentalsVOList = marketAnalyticsService.getAllStockFundamentals();
            System.out.println("total stocks "+allstockFundamentalsVOList.size());


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
