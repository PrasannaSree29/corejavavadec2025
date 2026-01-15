public class factorial {
    static void main(String[] args) {
        //6	Find the sum of factorial , if n value is 145 then sum of factorial=145.
//Ex: 1!+4!+5!=1+24+120=145

        int num = 145;
        int temp = num;
        int sum = 0;
        while (temp > 0) {
            int digit = temp % 10;
            int factorial = 1;
            for (int i = 1; i <= digit; i++) {
                factorial = factorial * i;
            }
            sum = sum + factorial;
            temp = temp / 10;
        }
        System.out.println("factorial "+num+" is " + sum);
    }
}