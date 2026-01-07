/**
 * First class written in java that introduces primitive datatypes and simple methods
 */
public class Eureka {
    public static void main(String[] args) {  //default method in java
        System.out.println("Eureka!!!!");

        int i = 56; //variable declaration and assignment of a value
        int j; //variable declaration
        j = 45; //value assignment

        int k = i + j; //Statically typed or type-safe language
        System.out.println("Value of i is " + i);
        System.out.println("Sum of i and j is " + (i + j));

//        float floatSum = sumFloatMethod();//calling code taking the return value of sumFloatMethod and assigning it to floatSum variable
//        System.out.println("Sum of floats f1 and f2 is "+ floatSum);
        float f1 = 53.25f;
        float f2 = 94.56f;

        double doublef1= (double) f1; // Typecasting- converting values from one type to another
        double anotherDoublef1 =f1; //Implicit typecasting

        System.out.println("Sum of floats f1 and f2 is " + sumFloatMethod(f1, f2)); //calling code-overloaded methods
        System.out.println("Sum of static floats f1 and f2 is " + sumFloatMethod()); //calling code-overloaded methods



        double d1 = 13.56; //int, float, double,boolean are primitive datatypes
        double d2 = 14.98;
        System.out.println("Product of d1 and d2 is " + multiplyDoubleValues(d1, d2));

        boolean b1 = true;
        // b1 = false;
        System.out.println("Value of b1 is " + b1);

        String x = "Eureka"; //string literal
        String y = new String("Technologies"); // using new keyword
        System.out.println("Concatenation of both strings x and y is " + x + " " + y);
        System.out.println("Concatenation of both strings using a method is " + concatStrings(x, y));

        char firstChar = 'x'; //Camel-case -first noun is lower case, subsequent noun starts with UpperCase
        char secondChar = 'y';
        System.out.println("Concat of 2 chars firstChar and secondChar " + firstChar + secondChar);
    }

    /**
     * Method that returns the product of 2 double inputs
     *
     * @param d1 First double input
     * @param d2 Second double input
     * @return Product of the inputs
     */

    private static Double multiplyDoubleValues(double d1, double d2) {
        return d1 * d2;
    }

    //method overloading
    private static float sumFloatMethod(float f1, float f2) {
        return f1 + f2;
    }

    /**
     * This method returns the sum of 2 float numbers - JavaDoc
     *
     * @return float sum of given float input
     */

    private static float sumFloatMethod() {
        float f1 = 23.08f;
        float f2 = 81.25f;
        return f1 + f2;
    }

    private static String concatStrings(String s1, String s2) {
        return s1 + " " + s2;
    }
}