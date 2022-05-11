package AtCoder.ARC.ARC103.ProblemC;

import java.util.Scanner;

public class UpsideDown {
    static int N;
    static int[] nums;
    static int[][] count;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        nums = new int[N + 1];
        count = new int[100005][2];
        for (int i = 1; i <= N; i++) {
            nums[i] = in.nextInt();
            if (i % 2 == 1) count[nums[i]][0]++;
            else count[nums[i]][1]++;
        }
        int oddMaxIndex = 0;
        int oddMaxCount = 0;
        int evenMaxIndex = 0;
        int evenMaxCount = 0;

        for (int i = 1; i < count.length; i++) {
            if (count[i][0] > oddMaxCount) {
                oddMaxCount = count[i][0];
                oddMaxIndex = i;
            }
            if (count[i][1] > evenMaxCount) {
                evenMaxCount = count[i][1];
                evenMaxIndex = i;
            }
        }
        if (oddMaxIndex != evenMaxIndex) {
            System.out.println(N - (oddMaxCount + evenMaxCount));
            return;
        }

        int secondOddMaxIndex = 0;
        int secondOddMaxCount = 0;
        int secondEvenMaxIndex = 0;
        int secondEvenMaxCount = 0;

        for (int i = 1; i < count.length; i++) {
            if (i != oddMaxIndex && count[i][0] > secondOddMaxCount) {
                secondOddMaxCount = count[i][0];
                secondOddMaxIndex = i;
            }
            if (i != evenMaxIndex && count[i][1] > secondEvenMaxCount) {
                secondEvenMaxCount = count[i][1];
                secondEvenMaxIndex = i;
            }
        }
        System.out.println(N - Math.max(oddMaxCount + secondEvenMaxCount, evenMaxCount + secondOddMaxCount));

    }
}
