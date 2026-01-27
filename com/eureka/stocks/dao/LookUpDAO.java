package com.eureka.stocks.dao;

import com.eureka.stocks.exception.StockException;
import com.eureka.stocks.vo.SectorVO;
import com.eureka.stocks.vo.SubSectorVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LookUpDAO  extends BaseDAO{

    /**
     * Default constructor will call the Superclass constructor that creates the database connection
     */
    public LookUpDAO() {
        super();
    }

    /**
     *Method that returns all sectors o the economy
     * @return
     */
    public List<SectorVO> getAllSectors(){
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
                //Each instance of SectorVO represents a row returned by the above query
                SectorVO sectorVO = new SectorVO(resultSet.getInt("sector_id")); //Get value of sector_id column from the DB and use it to create a SectorVO object/instance
                sectorVO.setSectorName(resultSet.getString("sector_name"));//Get value of sector_name column from the DB and set it into the sectorVO instance
                //Each row object gets added to the List so that it could hold multiple rows of data
                sectorList.add(sectorVO);
            }
            return sectorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return sectorList;
    }

    /**
     * This method will return only 1 SubSector Record as per the input Sector ID provided
     * @param subSectorID
     * @return
     */
    public SubSectorVO getSubSector(int subSectorID){
        String subSectorQuery = """
                select
                    sl.subsector_id,
                    sl.sector_id,
                    sl.subsector_name
                from
                    endeavour.subsector_lookup sl
                where
                    sl.subsector_id = ?
                """;
        SubSectorVO subSectorVO = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(subSectorQuery);
            preparedStatement.setInt(1, subSectorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                subSectorVO = new SubSectorVO(resultSet.getInt("subsector_id"));
                subSectorVO.setSectorID(resultSet.getInt("sector_id"));
                subSectorVO.setSubSectorName(resultSet.getString("subsector_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subSectorVO;
    }

    /**
     *Method that returns all sectors o the economy
     * @return
     */

    public List<SubSectorVO> getAllSubSectors(){
        String subSectorQuery= """
                select
                    sl.subsector_id,
                    sl.sector_id,
                    sl.subsector_name
                from
                    endeavour.subsector_lookup sl
                """;
        List<SubSectorVO> subSectorList= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(subSectorQuery);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                SubSectorVO subSectorVO = new SubSectorVO(resultSet.getInt("subsector_id"));
                subSectorVO.setSectorID(resultSet.getInt("sector_id"));
                subSectorVO.setSubSectorName(resultSet.getString("subsector_name"));
                subSectorList.add(subSectorVO);
            }
            return subSectorList;
        }catch (SQLException e){
            throw new StockException("unable to retreive stock fundamentals ",e);

        }

    }
}



