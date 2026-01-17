package com.eureka.stocks.dao;

import com.eureka.stocks.vo.StockFundamentals;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockFundamentalsDao extends BaseDAO{
    //    "Query the STOCK_FUNDAMENTALS table and print the data. We need the columns TICKER_SYMBOL, SECTOR_ID, MARKET_CAP, CURRENT_RATIO
    //    Create a class 'StockFundamentals' in package vo
    //    Create a class 'StockFundamentalsDao' in package dao. Add a method 'getStockFundamentals()' which returns a list of StockFundamentals objects
    //    Add an instance variable 'StockFundamentalsDao stockFundamentalsDao' in MarketAnalyticsService.
    //    Print the list that is returned."
    public StockFundamentalsDao(){
        super();
    }


    public static List<StockFundamentals> getAllStockFundamentals() {
        String sectorQuery = """
                select
                	sf.ticker_symbol ,
                	sf.sector_id ,
                	sf.market_cap ,
                	sf.current_ratio
                from
                	endeavour.stock_fundamentals sf
                """;
        List<StockFundamentals> stockFundamentalsList = new ArrayList<>();
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(sectorQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                String tickerSymbol = resultSet.getString("ticker_symbol");
                int sectorId = resultSet.getInt("sector_id");
                BigDecimal marketCap = resultSet.getBigDecimal("market_cap");
                double currentRatio = resultSet.getDouble("current_ratio");

                StockFundamentals stockFundamentals =
                        new StockFundamentals(tickerSymbol, sectorId, marketCap, currentRatio);

                stockFundamentalsList.add(stockFundamentals);            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockFundamentalsList;
    }
}

