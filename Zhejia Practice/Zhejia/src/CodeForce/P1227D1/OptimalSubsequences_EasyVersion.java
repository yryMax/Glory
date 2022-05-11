package CodeForce.P1227D1;

import java.util.*;

public class OptimalSubsequences_EasyVersion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lengthSeq = scanner.nextInt();
        int[] numbers = new int[lengthSeq];
        PriorityQueue<Integer[]> maxes = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] arr1, Integer[] arr2) {
                return (!arr1[0].equals(arr2[0])) ? -arr1[0] + arr2[0] : arr1[1] - arr2[1];
            }
        });

        for (int i = 0; i < lengthSeq; i++) {
            numbers[i] = scanner.nextInt();
            maxes.add(new Integer[] {numbers[i], i});
        }

        assert maxes.peek() != null;
        int[][] dp = new int[lengthSeq][lengthSeq];
        dp[0][0] = maxes.peek()[1];
        maxes.poll();
        for (int r = 1; r < lengthSeq; r++)  {
            assert maxes.peek() != null;
            int index = maxes.poll()[1];
            int c = 0;
            while (c < r && dp[r -1][c] < index) {
                dp[r][c] = dp[r - 1][c];
                c++;
            }
            if (c == r) {
                dp[r][r] = index;
                continue;
            }
            dp[r][c++] = index;
            while (c <= r) {
                dp[r][c] = dp[r - 1][c - 1];
                c++;
            }
        }

        int numQuery = scanner.nextInt();
        while (numQuery-- > 0) {
            int len = scanner.nextInt();
            int pos = scanner.nextInt();
            System.out.println(numbers[dp[len - 1][pos - 1]]);

        }
    }
}




