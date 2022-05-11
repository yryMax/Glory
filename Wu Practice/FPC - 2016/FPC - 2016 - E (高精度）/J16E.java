import java.math.BigInteger;
import java.util.Scanner;

public class J16E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        BigInteger last = BigInteger.valueOf(0);

        BigInteger power = null;

        for (int i = 1; i < n+1; i++) {
            power = BigInteger.valueOf(i);
            power = power.multiply(power);
            last = last.add(power);
        }
        System.out.println(last);
    }
}
