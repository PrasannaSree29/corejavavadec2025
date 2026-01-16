package com.eureka.stocks.service;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.util.List;

public class MarketAnalyticsService {
    private final LookUpDAO lookUpDAO

    { //instance variable, it's also a dependency
    }

    /**
     * Parameterized constructor that forces an instance of  lookUpDA to be provided for the
     * MarketAnalyticsService to function
     *
     * @param lookUpDAO
     */
    public MarketAnalyticsService(LookUpDAO lookUpDAO) {
        this.lookUpDAO = lookUpDAO;
    }

    /**
     * Business method that fetches all Sectors from the database
     *
     * @return
     */
    public List<SectorVO> getAllSectors() {
        return lookUpDAO.getAllSectors();
    }

    public SubSectorVO getSingleSubsector(int subSectorID) {

    }
}



