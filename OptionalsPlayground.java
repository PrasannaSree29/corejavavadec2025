import java.util.Optional;

public class OptionalsPlayground {
    public static void main(String[] args) {

        String inputString = "Eureka";
        //String inputstring=null;
        String outputString = null;

//        if(inputString!=null){
//            outputString=inputString;
//        }
//        else{
//            outputString="whatever";
//        }
//        System.out.println(outputString);

        Optional<String> inputStrOptional = Optional.ofNullable(inputString);
        if(!inputStrOptional.isEmpty()){
            outputString=inputStrOptional.get();
        }else{
            outputString="whatever";
        }
        System.out.println("output string is :"+outputString);
        String anotherOutputString = Optional.ofNullable(inputString).orElse("whatever");
        System.out.println("another output string is:"+anotherOutputString);

        inputStrOptional.ifPresent((String str)-> System.out.println(str));//lambda expression for the consumer
        inputStrOptional.ifPresent(System.out::println);// Method reference

    }
}
