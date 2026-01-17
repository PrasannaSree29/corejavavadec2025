package com.eureka.stocks.service;

import com.eureka.stocks.dao.LookUpDAO;
import com.eureka.stocks.dao.StockFundamentalsDAO;
import com.eureka.stocks.sorting.SFMarketCapAscComparator;
import com.eureka.stocks.sorting.SubSectorNameComparator;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.StockFundamentalsVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarketAnalyticsService {
    private LookUpDAO lookUpDAO; //instance variable, it's also a dependency
    private StockFundamentalsDAO stockFundamentalsDAO;

    private MarketAnalyticsService() {
    }
     * MarketAnalyticsService to function
     * @param lookUpDAO
     */
    public MarketAnalyticsService(LookUpDAO lookUpDAO) {
    public MarketAnalyticsService(LookUpDAO lookUpDAO, StockFundamentalsDAO stockFundamentalsDAO) {
            this.lookUpDAO = lookUpDAO;
            this.stockFundamentalsDAO = stockFundamentalsDAO;
        }

        /**
         * Business method that fetches all Sectors from the database
         * @return
         * @return List of SectorVO
         */
        public List<SectorVO> getAllSectors(){
            return lookUpDAO.getAllSectors();
            List<SectorVO> allSectorsList = lookUpDAO.getAllSectors();
            Collections.sort(allSectorsList);
            return allSectorsList;
        }

        /**
         * Business method that fetches single SubSector from the database as per the input provided
         * @return
         * @return single SubSectorVO
         */
        public SubSectorVO getSingleSubSector(int subSectorID){
            return lookUpDAO.getSubSector(subSectorID);
        }

        /**
         * Business method that fetches all Stock Fumdamental records from the database
         * @return List of StockFundamentalsVO
         */
        public List<StockFundamentalsVO> getAllStockFundamemntals(){
            List<StockFundamentalsVO> allStockFundamentalsList = stockFundamentalsDAO.getAllStockFundamentals();
            Collections.sort(allStockFundamentalsList);//Natural order by Ticker Symbol Asc

//        allStockFundamentalsList.sort(new SFMarketCapAscComparator());//Sort by Market Cap Asc
//        allStockFundamentalsList.sort(new SFMarketCapAscComparator().reversed()); //Sort by Market Cap Desc

            //Sort by Market Cap Desc using Anonymous Inner class
            allStockFundamentalsList.sort(new Comparator<StockFundamentalsVO>() {
                @Override
                public int compare(StockFundamentalsVO o1, StockFundamentalsVO o2) {
                    return o2.getMarketCap().compareTo(o1.getMarketCap());
                }
            });

            return allStockFundamentalsList;
        }

        /**
         * Business method that fetches all SubSectors from the database
         * @return List of SubSectorVO
         */
        public List<SubSectorVO> getAllSubSectors(){
            List<SubSectorVO> allSubSectorsList = lookUpDAO.getAllSubSectors();
            Collections.sort(allSubSectorsList); //Natural order

//        Collections.sort(allSubSectorsList, new SubSectorNameComparator());//Other order, sort by SubSectorName ascending
            allSubSectorsList.sort(new SubSectorNameComparator());

            return allSubSectorsList;
        }
    }
