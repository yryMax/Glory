package AtCoder.ABC.ABC194.ProblemF;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DigitsParadiseInHexadecimal {
    static final Map<Character, Integer> corresponding = new HashMap<>();
    static final long LIMIT = (long) Math.pow(10, 9) + 7;
    static String N;
    static int K;
    static long[][] dp;

    static {
        corresponding.put('0', 0);
        corresponding.put('1', 1);
        corresponding.put('2', 2);
        corresponding.put('3', 3);
        corresponding.put('4', 4);
        corresponding.put('5', 5);
        corresponding.put('6', 6);
        corresponding.put('7', 7);
        corresponding.put('8', 8);
        corresponding.put('9', 9);
        corresponding.put('A', 10);
        corresponding.put('B', 11);
        corresponding.put('C', 12);
        corresponding.put('D', 13);
        corresponding.put('E', 14);
        corresponding.put('F', 15);
    }

    private static int smaller(char a, char b) {
        return Character.compare(a, b);
    }

    private static char get(int ind) {
        return N.charAt(ind - 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.next();
        K = in.nextInt();
        int lengthNum = N.length();
        dp = new long[lengthNum + 1][K + 1];
        dp[1][1] = corresponding.get(get(1));
        boolean[] appear = new boolean[16];
        int countAppear = 0;

        for (int digit = 1; digit < lengthNum; digit++) {
            char currentChar = get(digit);
            if (!appear[corresponding.get(currentChar)]) {
                appear[corresponding.get(currentChar)] = true;
                countAppear++;
            }
            for (int count = 1; count <= K; count++) {
                long currentVal = dp[digit][count];
                currentVal = (count == countAppear) ? currentVal - 1 : currentVal;
                dp[digit + 1][count] = (dp[digit + 1][count] + count * currentVal) % LIMIT;
                if (count + 1 <= K)
                    dp[digit + 1][count + 1] = (dp[digit + 1][count + 1] + (16 - count) * currentVal) % LIMIT;
            }
            dp[digit + 1][1] = (dp[digit + 1][1] + 15) % LIMIT;
            int valid = 0;
            for (int next = 0; next <= corresponding.get(get(digit + 1)); next++)
                valid = (appear[next]) ? valid + 1 : valid;
            if (countAppear <= K) dp[digit + 1][countAppear] = (dp[digit + 1][countAppear] + valid) % LIMIT;
            if (countAppear + 1 <= K) dp[digit + 1][countAppear + 1] = (dp[digit + 1][countAppear + 1] + (corresponding.get(get(digit + 1)) + 1 - valid)) % LIMIT;
        }


        System.out.println(dp[lengthNum][K]);
    }
}
