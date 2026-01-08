//Write a method that takes in a String and 2 doubles as input, and returns the
// concatenated output of the input String and sum of the doubles.
//Example: Eureka 15.00 16.50 should return Eureka31.50 as output.

public class Assignment {
public static void main(String[] args) {
    System.out.println("Assignment");
    String text="Eureka";
    double d1=15.00;
    double d2=16.50;
    System.out.println("Concatenated output of the string and sum  "+concatenateAndSum(text,d1,d2));
    }
    private static String concatenateAndSum(String text, double d1, double d2) {
        double sum = d1 + d2;
        return text + sum;
    }
}
