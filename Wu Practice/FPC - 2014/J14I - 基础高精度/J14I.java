import java.math.BigInteger;
import java.util.Scanner;

public class J14I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger tmp = BigInteger.valueOf(n);
        BigInteger ans = BigInteger.valueOf(0);
        while (n-- > 0) {
            ans = ans.add(tmp);
            tmp = tmp.multiply(BigInteger.valueOf(n));
        }
        System.out.println(ans);
    }
}
