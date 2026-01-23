package com.eureka.stocks.dao;

import com.eureka.stocks.exception.StockException;
import com.eureka.stocks.vo.StockFundamentalsVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockFundamentalsDAO extends BaseDAO{

    public StockFundamentalsDAO() {
        super();
    }

    public List<StockFundamentalsVO> getAllStockFundamentals() {
        String sfQuery = """
                select
                    sf.ticker_symbol,
                    sf.sector_id ,
                     sf.subsector_id,
                    sf.market_cap,
                    sf.current_ratio
                from
                    endeavour.stock_fundamentals sf
                """;
        List<StockFundamentalsVO> sfList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sfQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                StockFundamentalsVO stockFundamentalsVO = new StockFundamentalsVO(resultSet.getString("ticker_symbol"));
                stockFundamentalsVO.setSectorID(resultSet.getInt("sector_id"));
                stockFundamentalsVO.setSubSectorID(resultSet.getInt("subsector_id"));
                stockFundamentalsVO.setMarketCap(resultSet.getBigDecimal("market_cap"));
                stockFundamentalsVO.setCurrentRatio(resultSet.getDouble("current_ratio"));
                sfList.add(stockFundamentalsVO);
            }
            return sfList;
        } catch (SQLException e) {
            throw new StockException("Unable to retrieve Stock Fundamentals Data from the database", e); //Wrapping the original exception in our own custom exception
        }
    }
}
