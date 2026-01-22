import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**

 tool to learn to deal with dates and timestamps in java*/
public class PlayingWithDates {
    public static void main(String[] args) {
        // Dates Examples - java.time package
        LocalDate currentDate = LocalDate.now();
        System.out.println("Today's date is "+currentDate+", and 5 months ago was "+currentDate.minusMonths(5));
        // Equivalent of extract
        System.out.println("Extracting from the date, year: "+currentDate.getYear()+", day of the week "+currentDate.getDayOfWeek());

        // Equivalent of SQL to_date function in Java
        LocalDate stringToDate = LocalDate.parse("2023-11-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Parsed date is "+stringToDate+", adding 63 days to that date, it will be "+stringToDate.plusDays(63));


        // Timestamp example
        LocalDateTime currentTimestamp = LocalDateTime.now();
        System.out.println("Current Time is "+currentTimestamp+", 37 minutes into the future, it will be "+currentTimestamp.plusMinutes(37));
    }
}