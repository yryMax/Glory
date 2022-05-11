package AcWing.P135;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MinimumSubSequenceSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seqNumber = scanner.nextInt();
        int subSeqNumber = scanner.nextInt();

        long[] array = new long[seqNumber + 1];
        for (int i = 1; i <= seqNumber; i++)
            array[i] = scanner.nextLong();

        long[] sum = new long[seqNumber + 1];
        for (int i = 1; i <= seqNumber; i++)
            sum[i] = sum[i - 1] + array[i];

        long maxSum = Long.MIN_VALUE;
        Deque<Long[]> increasing = new LinkedList<>(); // {value, index}
        increasing.offerLast(new Long[]{0L, 0L});
        for (int i = 1; i <= seqNumber; i++) {
            assert increasing.getFirst() != null;
            while (!increasing.isEmpty() && i - increasing.getFirst()[1] > subSeqNumber)
                increasing.removeFirst();
            maxSum = Math.max(maxSum, sum[i] - increasing.getFirst()[0]);
            while (!increasing.isEmpty() && sum[i] <= increasing.getLast()[0])
                increasing.removeLast();
            increasing.offerLast(new Long[]{sum[i], (long) i});
            assert increasing.getLast() != null;
        }
        System.out.println(maxSum);
    }
}
