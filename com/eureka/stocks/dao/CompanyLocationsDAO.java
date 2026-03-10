package com.eureka.stocks.dao;

import com.eureka.stocks.vo.CompanyLocationsVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyLocationsDAO extends BaseDAO{

    public CompanyLocationsDAO(){
        super();
    }

//    public List<CompanyLocationsVO> getCompanyAddressByTicker(String tickerSymbol) {
//
//        String companyLocationQuery = """
//            select
//                cl.ticker_symbol,
//                cl.address,
//                cl.city,
//                cl.state,
//                cl.zip,
//                cl.country
//            from
//                endeavour.company_locations cl
//            where
//                cl.ticker_symbol = ?
//            """;
//
//        List<CompanyLocationsVO> companyLocationList = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(companyLocationQuery);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//
//                CompanyLocationsVO companyLocationsVO =
//                        new CompanyLocationsVO(resultSet.getString("ticker_symbol"));
//
//                companyLocationsVO.setAddress(resultSet.getString("address"));
//                companyLocationsVO.setCity(resultSet.getString("city"));
//                companyLocationsVO.setState(resultSet.getString("state"));
//                companyLocationsVO.setZip(resultSet.getString("zip"));
//                companyLocationsVO.setCountry(resultSet.getString("country"));
//
//                companyLocationList.add(companyLocationsVO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }    return companyLocationList;
//
//    }
    public void getCompaniesByState(String state) {

        String companyByStateQuery = """
            select
                cl.ticker_symbol,
                cl.address,
                cl.city,
                cl.state
            from
                endeavour.company_locations cl
            where
                cl.state = ?
            """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(companyByStateQuery);
            preparedStatement.setString(1, state);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Ticker Symbol : " + resultSet.getString("ticker_symbol"));
                System.out.println("Address       : " + resultSet.getString("address"));
                System.out.println("City          : " + resultSet.getString("city"));
                System.out.println("State         : " + resultSet.getString("state"));
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}