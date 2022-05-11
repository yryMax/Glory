package AtCoder.ABC.ABC195.ProblemF;

import java.util.*;

public class CoprimePresent {
    static long A;
    static long B;
    static List<Integer> primes = new ArrayList<>();
    static {
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);
        primes.add(11);
        primes.add(13);
        primes.add(17);
        primes.add(19);
        primes.add(23);
        primes.add(29);
        primes.add(31);
        primes.add(37);
        primes.add(41);
        primes.add(43);
        primes.add(47);
        primes.add(53);
        primes.add(59);
        primes.add(61);
        primes.add(67);
        primes.add(71);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        A = in.nextLong();
        B = in.nextLong();
        long[] dp = new long[(int) Math.pow(2, 20)];
        int[] bits = new int[get(B) + 1];
        for (long curr = A; curr <= B; curr++)
            for (int factor = 19; factor >= 0; factor--)
                    bits[get(curr)] = 2 * bits[get(curr)] + ((curr % primes.get(factor) == 0) ? 1 : 0);

        long ans = 0;

        dp[0] = 1;
        for (long currNum = A; currNum <= B; currNum++) {


            for (int bitmask = 0; bitmask < Math.pow(2, 20); bitmask++) {
                if ((bitmask & bits[get(currNum)]) == 0) {
                    int next = bits[get(currNum)] | bitmask;
                    dp[next] += dp[bitmask];
                }
            }
        }
        for (long each : dp)
            ans += each;
        System.out.println(ans);
    }

    private static int get(long x) {
        return (int) (x - A);
    }
}
