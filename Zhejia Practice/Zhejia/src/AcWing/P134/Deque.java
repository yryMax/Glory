package AcWing.P134;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Deque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        long[][] array = new long[number][2];
        for (int i = 0; i < number; i++) {
            array[i][0] = scanner.nextLong();
            array[i][1] = i;
        }
        Arrays.sort(array, new Comparator<long[]>() {
            @Override
            public int compare(long[] row1, long[] row2) {
                return (row1[0] != row2[0]) ?
                        (row1[0] > row2[0]) ? 1 : -1 :
                        (row1[1] != row2[1]) ? (row1[1] > row2[1]) ? 1: -1: 0;
            }
        });

        long min = 1;
        long lastIndex = Long.MAX_VALUE;
        boolean descending = true;

        for (int i = 0; i < number;) {
            int equal = i;
            while (equal < number && array[equal][0] == array[i][0]) equal++;

            long minOriginalIndex = array[i][1];
            long maxOriginalIndex = array[equal - 1][1];

            if (descending) {
                if (lastIndex > maxOriginalIndex) lastIndex = minOriginalIndex;
                else {
                    descending = false;
                    lastIndex = maxOriginalIndex;
                }

            } else {
                if (lastIndex < minOriginalIndex) lastIndex = maxOriginalIndex;
                else {
                    min++;
                    descending = true;
                    lastIndex = minOriginalIndex;
                }
            }
            i = equal;
        }
        System.out.println(min);
    }
}
