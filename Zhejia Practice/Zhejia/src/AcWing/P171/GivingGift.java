package AcWing.P171;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class GivingGift {
    static long W;
    static int N;
    static int mid;
    static long[] weights;
    static TreeSet<Long> firstHalf;
    static long first;
    static long ans;


    private static void searchFirst(int ind, long acc) {
        if (ind == mid) {
            firstHalf.add(acc);
            return;
        }
        if (acc + weights[ind] <= W) searchFirst(ind + 1, acc + weights[ind]);
        if (acc <= W) searchFirst(ind + 1, acc);
    }

    private static void searchSecond(int ind, long acc) {
        if (ind == N) {
            Long compliment = W - acc;
            Long another = firstHalf.floor(compliment);
            if (another != null) ans = Math.max(ans, another + acc);
            return;
        }
        if (acc + first <= W) searchSecond(ind + 1, acc);
        if (acc + weights[ind] + first <= W) searchSecond(ind + 1, acc + weights[ind]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        W = in.nextLong();
        N = in.nextInt();
        weights = new long[N + 1];

        for (int i = 0; i < N; i++)
            weights[i] = -in.nextLong();
        Arrays.sort(weights);
        for (int i = 0; i < N; i++)
            weights[i] = -weights[i];
        firstHalf = new TreeSet<>();
        int ind = 0;
        while (weights[ind] > W) ind++;
        mid = (ind + N) >> 1;

        searchFirst(ind, 0);
        first = firstHalf.first();
        ans = Long.MIN_VALUE;
        searchSecond(mid, 0);
        System.out.println(ans);
    }
}
