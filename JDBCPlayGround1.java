import java.sql.*;
import java.util.Collection;
import java.util.List;

public class JDBCPlayGround1 {

    private static final String JDBC_URL = "jdbc:postgresql://endeavourtech.ddns.net:50271/StocksDB";
    private static final String URL = "evr_sql_app";
    private static final String PASSWORD = "5LViU5pLkSjRHECec9NF4wRxxV";


    public static void main(String[] args) throws SQLException {

        Connection dbconnection = DriverManager.getConnection(JDBC_URL, URL, PASSWORD);

        List<Integer> sectorIDList = List.of(35,36,37,38,39);
        getsectorIDforGiven(dbconnection, sectorIDList);
//        getAllSectors(dbconnection);
//        getAllSubSectors(dbconnection,34);

    }
    public static void getAllSectors(Connection dbconnection) throws SQLException {
        String sectorQuery = """
                select
                sl.sector_id,
                sl.sector_name
                from
                endeavour.sector_lookup sl""";
        try {
            PreparedStatement preparedStatement = dbconnection.prepareStatement(sectorQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(("Sector ID : " + resultSet.getInt("sector_id")) + " ; "
                        + ("Sector Name : " + resultSet.getString("sector_name")));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void getAllSubSectors(Connection dbconnection,int wantedSectorID)throws SQLException{
        String subsectorquery= """
       				select
       				sl.subsector_id ,
       				sl.sector_id ,
       				sl.subsector_name
       				from endeavour.subsector_lookup sl
       				where sl.sector_id=?
       				""";

        try {
            PreparedStatement preparedStatement = dbconnection.prepareStatement(subsectorquery);
            preparedStatement.setInt(1,wantedSectorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int subsectorID=resultSet.getInt("subsector_id");
                int sectorID=resultSet.getInt("sector_id");
                String subSectorName=resultSet.getString("subsector_name");

                System.out.println("sectoId  ,"+sectorID+"subsectorname  ,"+subSectorName+"subsecotrID  ,"+subsectorID);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        public static void getsectorIDforGiven(Connection dbconnection, List<Integer>sectorIDList ){
//            String query= """
//                select
//                sl.sector_id,
//                sl.sector_name
//                from
//                	endeavour.sector_lookup sl
//                where
//                	sl.sector_id in(
//                """;
//            String dynamicQuestionMarks="?,".repeat(sectorIDList.size());
//            String Actualquery=query+dynamicQuestionMarks.substring(0,dynamicQuestionMarks.length()-1)+")";
//            try{
//                PreparedStatement preparedStatement = dbconnection.prepareStatement(Actualquery);
//                for (int i = 0; i < sectorIDList.size(); i++) {
//                    preparedStatement.setInt(i+1, sectorIDList.get(i));
//                }
//                ResultSet resultSet = preparedStatement.executeQuery();
//                while(resultSet.next()){
//                    System.out.println("Sector ID : "+resultSet.getInt("sector_id")+" , "+
//                            "Sector Name : "+resultSet.getString("sector_name"));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//        }


    }



    public static void getsectorIDforGiven(Connection dbconnection, List<Integer> sectorIDList ){
        String query= """
                select
                sl.sector_id,
                sl.sector_name
                from
                	endeavour.sector_lookup sl
                where
                	sl.sector_id in(
                """;
        String dynamicQuestionMarks="?,".repeat(sectorIDList.size());
        String Actualquery=query+dynamicQuestionMarks.substring(0,dynamicQuestionMarks.length()-1)+")";
        try{
            PreparedStatement preparedStatement = dbconnection.prepareStatement(Actualquery);
            for (int i = 0; i < sectorIDList.size(); i++) {
                preparedStatement.setInt(i+1, sectorIDList.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Sector ID : "+resultSet.getInt("sector_id")+" , "+
                        "Sector Name : "+resultSet.getString("sector_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}