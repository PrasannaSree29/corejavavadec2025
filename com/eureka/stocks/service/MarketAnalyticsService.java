package com.eureka.stocks.service;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDao;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentals;
import com.eureka.stocks.vo.SubSectorVO;

import java.util.List;

public class MarketAnalyticsService {
    private LookUpDAO lookupDAO;  //Instance variable, its also a dependency
    private StockFundamentalsDao stockFundamentalsDao;

    /**
     * Parameterized constructor that forces an instance o lookingUpDad to be provided for the MarketAnalyticsService to function
     * @param lookupDAO
     */
    public MarketAnalyticsService(LookUpDAO lookupDAO){
        this.lookupDAO = lookupDAO;
    }

    public List<SectorVO> getAllSectors(){
        return lookupDAO.getAllSectors();
    }

    public List<StockFundamentals> getAllStockFundamentals(){
        return StockFundamentalsDao.getAllStockFundamentals();
    }

    /**
     * Business method that fetches single Subsector
     * @param subSectorID
     * @return
     */
    public SubSectorVO getSingleSubSector(int subSectorID){
        return lookupDAO.getSubSector(subSectorID);
    }
}

