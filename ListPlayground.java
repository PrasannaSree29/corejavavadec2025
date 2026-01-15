import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListPlayground {
    public static void main(String[] args) {
        //one way of initialising  or assignment
        //ArrayList<String> myFirstArrayList = new ArrayList<String>();//(not experienced ppl use this)

        //Using interface polymorphism
        List<String> myFirstArrayList = new ArrayList<>();

        List<String> removeList = new ArrayList<>();
        removeList.add("INTL");
        removeList.add("NVDA");


        //LIST MAINTAIN INSERTION ORDER
        myFirstArrayList.add("GOOGL");
        myFirstArrayList.add("AMD");
        myFirstArrayList.add("NVDA");
        myFirstArrayList.add("INTL");
        myFirstArrayList.add("AAPL");


        System.out.println("Size of the list is "+myFirstArrayList.size());
        System.out.println("contents of list "+myFirstArrayList);

        System.out.println("stock at position 4 is "+myFirstArrayList.get(3));//Index is Inclusive

        myFirstArrayList.add("AMD");
        myFirstArrayList.add(2,"INTL");//overloaded add method

        myFirstArrayList.remove(3);
        myFirstArrayList.remove("INTL");
        myFirstArrayList.remove("AMD");
        myFirstArrayList.removeAll(removeList);
        System.out.println(""+myFirstArrayList);

        System.out.println("Is my first list empty?"+myFirstArrayList.isEmpty());

        myFirstArrayList.addAll(removeList);
        System.out.println("first list after addition of another list is "+myFirstArrayList);

        System.out.println("Does my first list contain AMD stock in it ?"+myFirstArrayList.contains("AMD"));

        Collections.sort(myFirstArrayList);   //COLLECTIONS IS A UTIL CLASS
        System.out.println("my First list after the sort is"+myFirstArrayList);

//        List<Stock> stockList=List.of(new Stock("AAPL","Apple Inc"), IIMUTABLE STOCK LIST
//        new Stock("GOOGl","Alphabet Inc"),
//        new Stock("AAPL","Apple Inc")
//        );


        List<Stock> stocksList = new ArrayList<>();//LIST OF OUT CUSTOM OBJECT STOCK

        stocksList.add(new Stock("AAPL","Apple Inc"));
        stocksList.add(new Stock("AMD","Advanced microservices Inc"));
        stocksList.add(new Stock("GOOGL","Alphabet Inc"));
        stocksList.add(new Stock("TSLA","Tesla Inc"));
        stocksList.add(new Stock("AMEX","American Express"));


        Collections.sort(myFirstArrayList);   //COLLECTIONS IS A UTIL CLASS
        System.out.println("my First list after the sort is"+myFirstArrayList);

        System.out.println("stocks list"+stocksList);//toString  OVERRIDEN BY ARRAYLISY AND STOCK CLASS

        System.out.println("Does AMD exist in stock list "+stocksList.contains(new Stock("AMD","Advancedmicroservices Inc")));

        System.out.println("AMD stock in the list is at position :"+stocksList.indexOf(new Stock("AMD","Advanced microservices Inc")));

        List<Stock> reverseStocksList = stocksList.reversed();//THIS GENERATES A NEW LIST WITH REVERSE ORDER
        Collections.reverse(stocksList);//THIS WILL CHANGE THE ORIGINAL LIST ORDER
        System.out.println("reversed list is" +reverseStocksList);


        //START INDEX IS INCLUSIVE,END INDEX IS EXCLUSIVE
        List<Stock> stocksSubList = stocksList.subList(1,4);

        //LOOPS- ITERATION OF A LIST USING FOR  i LOOP
        for (int i = 0; i < stocksSubList.size(); i++) {
            System.out.println(stocksSubList.get(i));

        }
        System.out.println("===================");
        //For-each loop on the list

        for (Stock eachStock : stocksList) {
            System.out.println(eachStock);

        }
        //ALERT*****DO NOT DO THIS

        List<Object> someJunkList =new ArrayList<>();
        someJunkList.add(new Stock("V","Visa Inc"));
        someJunkList.add("whatever");
        someJunkList.add(new BigDecimal("100.0"));

        //WRAPPER CLASSES
        int i=47;
        Integer obji =47;//IMPLICIT TYPECASTING BEING DONE BY JAVA FROM PRIMITIVE INT 47 TO 47 BEING HELD IN AN OBJECT OF TYPE INTEGER
//        List<Integer> intList=new ArrayList<>();
//        intList.add(47);
//        intList.add(95);
//        intList.add(63);
//        intList.add(179);

        //ANOTHER WAY FOR LIST
        List<Integer> intList = List.of(47,95,63,179);//ALTERNATE WAY OF GENERATING A IMMUTABLE LIST USING OF STATIC METHOD IN LIST INTERFACE

        //intList.add(61); THIS IS NOT POSSIBLE

        int total = 0;
        for (Integer eachInt : intList) {
            total=total+eachInt;//ANOTHER IMPLICIT TYPECASTING BUT IN REVERSE
        }
        System.out.println("Total of intList values is "+total);
        System.out.println("Does intList contains number 63? "+intList.contains(63));//typecasting is in action:)






    }
}
