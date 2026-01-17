package com.eureka.stocks.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class StockFundamentals {
//    "Query the STOCK_FUNDAMENTALS table and print the data. We need the columns TICKER_SYMBOL, SECTOR_ID, MARKET_CAP, CURRENT_RATIO
//    Create a class 'StockFundamentals' in package vo
//    Create a class 'StockFundamentalsDao' in package dao. Add a method 'getStockFundamentals()' which returns a list of StockFundamentals objects
//    Add an instance variable 'StockFundamentalsDao stockFundamentalsDao' in MarketAnalyticsService.
//    Print the list that is returned."

    private String tickerSymbol;
    private int sectorID;
    private BigDecimal marketCap;
    private double currentRatio;

    private StockFundamentals() {
    }

    public StockFundamentals(String tickerSymbol, int sectorID, BigDecimal marketCap, double currentRatio) {
        this.tickerSymbol = tickerSymbol;
        this.sectorID = sectorID;
        this.marketCap = marketCap;
        this.currentRatio = currentRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockFundamentals that = (StockFundamentals) o;
        return Objects.equals(tickerSymbol, that.tickerSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tickerSymbol);
    }

    @Override
    public String toString() {
        return "StockFundamentals{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", sectorID=" + sectorID +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}';
    }
}

