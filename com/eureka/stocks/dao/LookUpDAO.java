package com.eureka.stocks.dao;

import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LookUpDAO extends BaseDAO{

    /**
     * this default constructer call the super class constructer that creates the database connection
     */

    public LookUpDAO() {
        super();
    }

    public List<SectorVO>  getAllSectors(){
        String sectorQuery = """
                select
                  sl.sector_id,
                  sl.sector_name
                from
                  endeavour.sector_lookup sl
                """;
        List<SectorVO> sectorList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sectorQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                SectorVO sectorVO = new SectorVO(resultSet.getInt("sector_id"));//get value of sectorid from Db and use it to create sector VO object
                sectorVO.setSectorName(resultSet.getString("sector_name"));//get value of sector name column from the Db and set it into sector Vo instance
                sectorList.add(sectorVO);
            }
            return sectorList;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("Before closing DB connection");
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return sectorList;
    }

    public SubSectorVO getSubsector(int subSectorID){
        String subSectorQuery = """
                select
                                sl.subsector_id,
                                sl.sector_id ,
                                sl.subsector_name
                                from
                                endeavour.subsector_lookup sl
                                where
                                sl.subsector_id = ?
                
                """;
        try {
          PreparedStatement preparedStatement= connection.prepareStatement(subSectorQuery);
          preparedStatement.setInt(1,subSectorID);
          ResultSet resultSet=preparedStatement.executeQuery();
          while (resultSet.next()){
              subSectorVO = new SubSectorVO(resultSet.getInt("subsector_id"));
              subSectorVO.setSectorID(resultSet.getInt("sector_id"));
              subSectorVO.setSubSectorName(resultSet.getString("subsector_name"));
          }
          return subSectorVO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
