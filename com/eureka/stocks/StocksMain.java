package com.eureka.stocks;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.service.MarketAnalyticsService;
import com.eureka.stocks.vo.SectorVO;

import java.util.List;

public class StocksMain {
    static void main(String[] args) {

        try{

        }catch (Exception e){
            e.printStackTrace();
        }


        //Getall sector information from the database
        LookUpDAO lookUpDAO =new LookUpDAO();
        //injecting an instance of lookup DAO as it is a dependancy that is needed for the market analytics service instance to function
        MarketAnalyticsService marketAnalyticsService = new MarketAnalyticsService(lookUpDAO);

        List<SectorVO> allSectorsList = marketAnalyticsService.getAllSectors();
        System.out.println("Number of sectors returned from db is "+allSectorsList.size());

        int subSectorID= 283;
        //get subsector details

    }
}
