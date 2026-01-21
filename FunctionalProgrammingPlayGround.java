import java.math.BigDecimal;
import java.util.function.*;

public class FunctionalProgrammingPlayGround {
    public static void main(String[] args) {
        // example of a Unary operator
        stringConcat("Eureka");
        UnaryOperator<String> stringConcatUO = (str) -> str + str;
//        UnaryOperator<String> stringConcatUO = (String str) ->{
//            return  str+str;
//        };
        System.out.println("concat Strings using UO" + stringConcatUO.apply("Eereka"));
        System.out.println("Method call for UO is " + stringConcat("Eureka"));


        // Example of binary operator
//        System.out.println("concat of two strings using method is"+concatTwoStrings("Eureka", "Technologies"));
        System.out.println("Adding 2 Big decimals" + addBD(new BigDecimal("100"), new BigDecimal("150")));
        BinaryOperator<BigDecimal> addBD_BO = (BigDecimal bd1, BigDecimal bd2)->bd1.add(bd2);
        BinaryOperator<BigDecimal> addBD_BO_MR=BigDecimal::add;

// example of consumer
        someConsumingMethod(10);
        Consumer<Integer> someConsumer = (Integer i) -> System.out.println("using consumer functional interface"+i);
        Consumer<Integer> someOtherConsumer = System.out::println;//Method reference equivalent
        someOtherConsumer.accept(15);
        someConsumer.accept(10);
 //example of supplier
        System.out.println("value  generated from a supplier method is"+someRandomSupplier());
        Supplier<Double> someRandomFISipplier = () -> Math.random();// Lambda Expression
        Supplier<Double> someRandomFIMRSupplier = Math::random;//Method referance
        System.out.println("Value generated froma FI supplier is "+someRandomFISipplier.get());
  //Example of a predicate
        System.out.println("is the string long enough:"+isStringLong("Eureka"));
        Predicate<String> isStringLongPD =(str) -> str.length()>10;//lambda expression
        System.out.println("is the string long enough with predicate ?"+isStringLongPD.test("Eureka"));
  //Example of Bipredicate
        System.out.println("Some Junk logic method:"+someJunkLogicMethod("Eureka", new BigDecimal(100)));
        BiPredicate<String, BigDecimal> someJunkLogicBPD = (x,y) ->
                y.multiply(new BigDecimal(x.length())).compareTo(new BigDecimal("1000"))>0; //Lambda Expression;
        System.out.println("some junk logic using BPD"+someJunkLogicBPD.test("Eureka",new BigDecimal("100")));


  //Example of Function
        System.out.println("Function logic something is"+someFunctionLogic(new BigDecimal(50)));
        Function<BigDecimal, String> someLogicFNC =bigDecimal -> "whatever"+bigDecimal;//Lambda expression
        System.out.println("function logic something using FNC FI is"+someLogicFNC.apply(new BigDecimal(50)));

  //Another example of function
        System.out.println("Calculate String length nul"+strLenMul("Eureka"));
        Function<String, Integer> strlenMulFNC = (str)->str.length()*10;// Lambda expression
        System.out.println("String length null using Fnc is "+strlenMulFNC.apply("Eureka"));

  //chaining multiple functions
        Function<String, Integer> firstOperation = String::length;// Method referance
        System.out.println("output after fist operation is "+firstOperation.apply("Eureka"));
        Function<String, Integer> secondOperation = firstOperation.andThen(integer -> integer*10);// Lambda expression
        System.out.println("output after second operation:"+secondOperation.apply("Eureka"));

   //Example of Bifunction
        System.out.println("some Bifuncion method that needs to be figureedout"+someBiFunctionMethod("Eureka", new BigDecimal(100)));
        BiFunction<String, BigDecimal ,Integer> someBiFuncFI = (str, bd) -> { //lambda expression
            BigDecimal someValue = bd.multiply(new BigDecimal(str.length()));
            return Integer.parseInt(someValue.toPlainString());
        };
        System.out.println("some Bifunctional interface output is "+someBiFuncFI.apply("Eureka", new BigDecimal(100)));


    }
// method equivalent of Bifunction
    private static Integer someBiFunctionMethod(String str, BigDecimal bd) {
        BigDecimal someValue = bd.multiply(new BigDecimal(str.length()));
        return Integer.parseInt(someValue.toPlainString());
    }

    private static Integer strLenMul(String str) {
        return str.length()*10;
    }

    private static String someFunctionLogic(BigDecimal bigDecimal) {
        return "whatever"+bigDecimal;
    }


    private static Boolean someJunkLogicMethod(String str, BigDecimal bd) {
        return bd.multiply(new BigDecimal(str.length())).compareTo(new BigDecimal("1000"))>0;
    }

    private static Boolean isStringLong(String str) {
        return  str.length()>10;
    }

    private static Double someRandomSupplier() {
        return Math.random();
    }

    //Method equivalent of consumer
    private static void someConsumingMethod(Integer i) {
        System.out.println("In consumer method, value is"+i);
    }
    //method equivalent of binary operator

    private static BigDecimal addBD(BigDecimal bd1, BigDecimal bd2) {
        return bd1.add(bd2);
    }


    //method equivalent of Unary operater
    private static String stringConcat(String str) {
        return str+str;
    }
}
