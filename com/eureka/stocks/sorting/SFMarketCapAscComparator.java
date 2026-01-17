package com.eureka.stocks.sorting;

import com.eureka.stocks.vo.StockFundamentalsVO;

import java.util.Comparator;

/**
 * Sorts Stock Fundamentals based on Market Cap asc
 */
public class SFMarketCapAscComparator implements Comparator<StockFundamentalsVO> {

    @Override
    public int compare(StockFundamentalsVO o1, StockFundamentalsVO o2) {
        return o1.getMarketCap().compareTo(o2.getMarketCap());
    }

//    @Override
//    public Comparator<StockFundamentalsVO> reversed() {
//        return Comparator.super.reversed();
//    }
}
