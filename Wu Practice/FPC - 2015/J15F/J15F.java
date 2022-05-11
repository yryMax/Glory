// very hard very magical
// One can notice immediately that this is a sequence existing in the OEIS
// Thus this problem can be solved within O(0) time.
import java.math.BigInteger;
import java.util.Scanner;

public class J15F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        BigInteger[] ans = new BigInteger[size+1];
        ans[0] = BigInteger.valueOf(1);
        for (int i = 1; i < size+1; i++) {
            if(i != 1) {
                ans[i] = ans[i-1].multiply(BigInteger.valueOf(4));
                ans[i] = ans[i].subtract(ans[i-2]);
            }
            else {
                ans[i] = ans[i-1].multiply(BigInteger.valueOf(4));
                ans[i] = ans[i].subtract(BigInteger.valueOf(1));
            }
        }
        System.out.println(ans[size]);
    }
}
