package CodeForce.P1296E1;

import java.util.Scanner;

public class StringColouring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] dp = new int[length];
        dp[0] = 1;
        String chars = scanner.next();
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (index(chars.charAt(j)) > index(chars.charAt(i))) dp[i] = Math.max(dp[i], dp[j]);
            }
            if (dp[i] + 1 >= 3) {
                System.out.println("NO");
                return;
            }
            dp[i]++;
        }

        int max = index(chars.charAt(0));
        StringBuilder result = new StringBuilder().append(0);
        for (int i = 1; i < chars.length(); i++) {
            int ind = index(chars.charAt(i));
            if (ind < max) result.append(1);
            else {
                if (ind > max) max = ind;
                result.append(0);
            }
        }
        System.out.println("YES");
        System.out.println(result);
    }

    private static int index(char ch) {
        return ch - 'a';
    }

    private static int lowBits(int n) {
        return n & (-n);
    }
}
