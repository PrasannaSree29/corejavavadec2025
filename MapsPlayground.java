import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapsPlayground {
   public static void main(String[] args) {

       Map<Integer, String> sectorMap=new HashMap<>();
       sectorMap.put(10, "Healthcare");// adding values to a map
       sectorMap.put(11, "Technologies");
       sectorMap.put(12,"RealEstate");

       System.out.println("size of the map is "+sectorMap.size());

       System.out.println("value with key 11 is"+sectorMap.get(11));
       sectorMap.put(13, "Energy");
       sectorMap.put(10,"mafia"); //Duplicate key is not allowed previous entry in the map with the same key is overwritten
       sectorMap.put(82,"Energy");//Duplicate values are allowed
       sectorMap.remove(82);
       System.out.println(sectorMap);

       // compute-ifabsent and compute if present examples(***Both these instance methods modify the underlying Map***)

       // if the key is absent in the map, then calculate a value using the input function and add this entry(key,value) back into map
       // if the key is present in the map, just return the value for that entry and the map remains unchanged
       String newValue=sectorMap.computeIfAbsent(11, (key)->"whatever"+key);
       System.out.println("New calculated value is"+newValue);

       //ifthe key is absent, a null value is returned and the map is left untouched
       //if the key is present , the bifunction is used to calculate a new value and updated the corresponding entry in the map

       String newPresentValue = sectorMap.computeIfPresent(10,(sectorID,sectorName) ->{
           return sectorID+sectorName;
       });

       List<String> tickerList = List.of("NVDA", "GOOGL", "MSFT", "AAPL", "AMD", "AMD", "INTL", "GOOGL", "AAPL", "AMD");
       //count the number of times each stock has been repeated
       Map<String, Integer> finalOutputMap = new HashMap<>();
       tickerList.forEach(eachTicker ->{
           Integer count = finalOutputMap.get(eachTicker);
           if(count==null){
               count=0;
           }
           count=count+1;
           finalOutputMap.put(eachTicker, count);
       });
       System.out.println(finalOutputMap);

       //count the number of times each stock has been repeated- The cool way
       Map<String, Integer> anotherFinalOutputMap = new HashMap<>();
       tickerList.forEach(eachTicker ->{
           Integer count = anotherFinalOutputMap.computeIfAbsent(eachTicker, (key) -> 0)+1;
           anotherFinalOutputMap.put(eachTicker,count);
       });
       System.out.println(anotherFinalOutputMap);

       //generateMapUsingRealObjects();
   }

    private static void generateMapUsingRealObjects(Map<Integer, String> sectorMap) {
        Map<String,Stock> stocksMap= new HashMap<>();
        stocksMap.put("AAPL", new Stock("AAPL","APPLe Inc"));
        stocksMap.put("MSFT", new Stock("MSFT","Microslop"));
        stocksMap.put("NVDA", new Stock("NVDA","Nvidia Inc"));
        stocksMap.put("AMD", new Stock("AMD","Advanced Micro devices"));

        System.out.println(sectorMap);

        System.out.println("Is microsoft present in the Map?"+stocksMap.containsKey("MSFT"));
        System.out.println("is a stock with name microsoft present in value of the map?"
                +stocksMap.containsValue(new Stock("MSFT","Microslop")));

        //There are two ways to iterate a map- Method 1
        Set<String> tickerKeySet = stocksMap.keySet();
        tickerKeySet.forEach(ticker -> System.out.println("Key:"+ticker+"Value:"+stocksMap.get(ticker)));

        //Iterate a map- Method 2
        stocksMap.forEach((ticker, stock) -> {
            System.out.println("Using Biconsumer Key:"+ticker+"Value:"+stock);
        });
    }
}
