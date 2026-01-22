package com.eureka.stocks.dao;

import com.eureka.stocks.exception.StockException;
import com.eureka.stocks.vo.StocksPriceHistoryVO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StocksPriceHistoryDAO extends BaseDAO {

    public List<StocksPriceHistoryVO> getPriceHistoryForStock(String tickerSymbol, LocalDate fromDate, LocalDate toDate) {
        String query = """
                select
                    sph.ticker_symbol,
                    sph.trading_date,
                    sph.open_price,
                    sph.close_price,
                    sph.low_price,
                    sph.high_price
                from
                    endeavour.stocks_price_history sph
                where
                    sph.ticker_symbol = ?
                    and sph.trading_date between ? and ?
                """;

        List<StocksPriceHistoryVO> priceHistoryList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tickerSymbol);
            // preparedStatement.setDate(2, Date.valueOf(fromDate));
            // preparedStatement.setDate(3, Date.valueOf(toDate));
            preparedStatement.setObject(2, fromDate);
            preparedStatement.setObject(3, toDate);
            System.out.println("Prepared Query: "+preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                StocksPriceHistoryVO stocksPriceHistoryVO = new StocksPriceHistoryVO(
                        resultSet.getString("ticker_symbol"),
                        resultSet.getDate("trading_date").toLocalDate()
                );
                stocksPriceHistoryVO.setClosePrice(resultSet.getBigDecimal("close_price"));
                stocksPriceHistoryVO.setOpenPrice(resultSet.getBigDecimal("open_price"));
                stocksPriceHistoryVO.setHighPrice(resultSet.getBigDecimal("high_price"));
                stocksPriceHistoryVO.setLowPrice(resultSet.getBigDecimal("low_price"));

                priceHistoryList.add(stocksPriceHistoryVO);
            }
        } catch (SQLException e) {
            throw new StockException("Unable to get stock price history data for ticker "+tickerSymbol, e);
        }
        return priceHistoryList;
    }
}