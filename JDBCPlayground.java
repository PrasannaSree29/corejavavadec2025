import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.sql.*;
import java.util.List;
//import org.postgres;

public class JDBCPlayground {

    private static final String JDBC_URL = "jdbc:postgresql://endeavourtech.ddns.net:50271/StocksDB";
    private static final String USERNAME = "evr_sql_app";
    private static final String PASSWORD = "5LViU5pLkSjRHECec9NF4wRxxV";

    public static void main(String[] args)  {

        //DriverManager.registerDriver(new OracleDriver()); //This step is needed for Oracle and not needed for Postgres
        // Connection dbConnection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        Connection dbConnection=null;

        int neededSubSectorID=283;
        try{
            dbConnection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            List<Integer> sectorIDList=List.of(35,38,41,44);
            getAllSectorRecords(dbConnection);
//            getSubSectorRecord(dbConnection,neededSubSectorID);
//            getSectorsForGivenIDs(dbConnection,sectorIDList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }

        //getAllSectorRecords(dbConnection);
        getSubSectorRecord(dbConnection, neededSubSectorID);

    }

    private static void getSectorsForGivenIDs(Connection dbConnection, List<Integer> sectorIDList) {
        String sectorQuery= """
                select
                	s
                """;
        String dynamicQuestionMarks="?,".repeat(sectorIDList.size());
        String finalQuery=sectorQuery+dynamicQuestionMarks.substring(0,dynamicQuestionMarks.length()-1)+")";
        System.out.println("Finally generated query is "+finalQuery);
        try{
            PreparedStatement preparedStatement = dbConnection.prepareStatement(finalQuery);
            for (int i = 0; i < sectorIDList.size(); i++) {
                preparedStatement.setInt(i+1,sectorIDList.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int sectorID= resultSet.getInt("sector_id");
                String sectorName=resultSet.getString("sector_name");
                System.out.println("Sector ID :"+sectorID+", Sector Name: "+sectorName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("Code in pointless finally block");
        }
    }

    private static void getSubSectorRecord(Connection dbConnection, int neededSubSectorID) {
        String subSectorQuery= """
                select
                	*
                from
                	endeavour.subsector_lookup sl
                where
                	sl.subsector_id = ?
                """;
        try{
            PreparedStatement preparedStatement = dbConnection.prepareStatement(subSectorQuery);
            preparedStatement.setInt(1,neededSubSectorID); //Injecting value as input into the query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){ //each iteration of while loop is reading the row
                int subSectorID= resultSet.getInt("subSector_id");
                int sectorID=resultSet.getInt("sector_id");
                String subSectorName=resultSet.getString("subSector_name");
                System.out.println("SubSector ID: "+subSectorID+" SubSector Name: "+subSectorName+" Sector ID : "+sectorID);
                //throw new IOException("Throwing Exception for Timepass");
            }

        } //catch (SQLException | IOException | NullPointerException e) {
        catch(SQLException e){
            System.out.println("In the first line of SQLException catch block");
            e.printStackTrace(); //prints the exception details into the console
        }
        catch (Exception e) {
            System.out.println("In the first line of Exception catch block");
            e.printStackTrace();
        } finally {
            System.out.println("This code in finally always executes no matter what");
        }
    }

    private static void getAllSectorRecords(Connection dbConnection) throws SQLException {
        String sectorQuery = """
                Select
                    sl.sector_id,
                    sl.sector_name
                from
                    endeavour.sector_lookup sl
                """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sectorQuery); //Generate a Prepared statement from connection
        ResultSet resultSet = preparedStatement.executeQuery(); //Generate a ResultSet from by executing the given Query on PreparedStatement

        //Iterate the ResultSet, each iteration of the below while loop represents reading a row from the query results
        while(resultSet.next()){

            int sectorID= resultSet.getInt("sector_id");
            String sectorName=resultSet.getString("sector_name");

            System.out.println("Sector ID: "+sectorID+
                    ", Sector Name: "+sectorName);
//            System.out.println("Sector ID : "+resultSet.getInt("sector_id")+
//                    ", Sector Name : "+resultSet.getString("sector_name"));

        }
    }
}


