public class StringsPlayground {
    public static void main(String[] args){
        String s1="Eureka";//string literal definition
        String s2=new String("Tech");//using new keyword

        String s3= "Eureka";

        String s4= new String("Eureka");

        String concatString=s1+s2;

        String anotherConcatString=s1.concat(s2);

        System.out.println("concatenate 2  strings s1 and s2 "+concatString);
        System.out.println("Another concattenation of strings s1 and s2 "+anotherConcatString);

        System.out.println("Length  of string s4 is "+s4.length());

        System.out.println("Upper case of another concatstring is "+anotherConcatString.toUpperCase());
        System.out.println("value fo another concatstring after uppercase is "+anotherConcatString); //string  immutability

        System.out.println("character at position 3 of "+anotherConcatString+" is "+anotherConcatString.charAt(2));

        //SUBSTRINGS
        System.out.println("substring variation 1 for "+anotherConcatString+" is "+anotherConcatString.substring(2));
        System.out.println("substring variation 2 for "+anotherConcatString+" is "+anotherConcatString.substring(2,6));//End index is exclusive

        System.out.println("does the string "+anotherConcatString+" contains eka ?"+anotherConcatString.contains("eka"));
        System.out.println("does the string "+anotherConcatString+" contains xyz ?"+anotherConcatString.contains("xyz"));

        System.out.println("position of string eka in "+anotherConcatString+" is "+anotherConcatString.indexOf("eka"));

        System.out.println("comparing two strings "+anotherConcatString+" , "+concatString+" is "+anotherConcatString.compareTo(concatString));

        System.out.println("Does the string "+anotherConcatString+" starts with Eur? "+anotherConcatString.startsWith("Eur"));

        //String split, where comma is the delimiter
        String tickerSymbols= "AAPL,GOOGL,AMD,NVDA,UNH,V";
        String[] tickerArray=tickerSymbols.split(",");
        for(String eachTicker : tickerArray) {
            System.out.println("Ticker :" + eachTicker);
        }
//        String tickerSymbols= "AAPL|GOOGL|AMD|NVDA|UNH|V";
//        String[] tickerArray=tickerSymbols.split("\\|");
//        for(String eachTicker : tickerArray){
//            System.out.println("Ticker :"+eachTicker);















    }
}
