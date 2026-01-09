import java.util.Arrays;

public class ArraysPlayground {

    public static void  main(String[] args){
        //Initializing an int array with and assign it values
        int[] numArray = new int[]{1,2,3,4,5};

        //Initialize an int array of size 5
        int[] squareArray=new int[5];

        System.out.println("Size of the numsarray is "+numArray.length);

        System.out.println("value at position 3 of the numsarray is "+numArray[2]); //Array indexs are inclusive, they go from 0 to length -1

        for(int index=0;index < numArray.length;index++){
            System.out.println("Value at index "+index+" of numarray is "+numArray[index]);
            squareArray[index]= numArray[index]*numArray[index];//This code gets repated as many times as the loop runs
        }

        for(int index=numArray.length-1;index>=0;index--){
            System.out.println("value at index "+index+" of numarray is "+numArray[index]);
        }
        //For each loop
        for (int eachElement : squareArray){
            System.out.println("Value is square array is "+eachElement);
        }
//for each loop on a string array,eachTicker will be of type string
        String[] tickerArray = new String[]{"AAPL","GOOGL","TSLA","AMD","NVDIA"};
        for (String eachTicker :tickerArray){
            System.out.println("Ticker symbol"+eachTicker);

        }
        Arrays.sort(tickerArray);
        System.out.println("Ticker Array after sort is "+Arrays.toString(tickerArray));

        int[] anotherNumArray= new int[]{8,34,76,4,91,24,52};

        Arrays.sort(anotherNumArray);
        //Arrays is a utility class provided by the java language,which only has static methods that
        //provide functionality based on inputs provided


//        for(int eachNumber : anotherNumArray){
//            System.out.println(eachNumber);
//        }

        System.out.println("Array anothernumarray is "+Arrays.toString(anotherNumArray));


    }
}


