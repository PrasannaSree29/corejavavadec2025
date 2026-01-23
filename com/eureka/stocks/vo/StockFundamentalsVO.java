package com.eureka.stocks.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class StockFundamentalsVO implements Comparable<StockFundamentalsVO>{
    private String tickerSymbol;
    private int sectorID;
    private BigDecimal marketCap;
    private int subSectorID;
    private Double currentRatio;

    private StockFundamentalsVO() {
    }

    public StockFundamentalsVO(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    @Override
    public String toString() {
        return "StockFundamentalsVO{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", sectorID=" + sectorID +
                ", subSectorID=" + subSectorID +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}';
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public int getSectorID() {
        return sectorID;
    }

    public void setSectorID(int sectorID) {
        this.sectorID = sectorID;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }
    public int getSubSectorID() {
        return subSectorID;
    }

    public void setSubSectorID(int subSectorID) {
        this.subSectorID = subSectorID;
    }


    public Double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(Double currentRatio) {
        this.currentRatio = currentRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockFundamentalsVO that = (StockFundamentalsVO) o;
        return Objects.equals(tickerSymbol, that.tickerSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tickerSymbol);
    }

    /**
     * Natural order us by Ticker Name Ascending
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(StockFundamentalsVO o) {
        return this.getTickerSymbol().compareTo(o.getTickerSymbol());
    }
}
