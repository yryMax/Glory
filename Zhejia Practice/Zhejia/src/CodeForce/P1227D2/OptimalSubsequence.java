package CodeForce.P1227D2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class OptimalSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lengthSeq = scanner.nextInt();
        int[] numbers = new int[lengthSeq + 1];
        PriorityQueue<Integer[]> maxes = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] arr1, Integer[] arr2) {
                return (!arr1[0].equals(arr2[0])) ? -arr1[0] + arr2[0] : arr1[1] - arr2[1];
            }
        });

        for (int i = 1; i <= lengthSeq; i++) {
            numbers[i] = scanner.nextInt();
            maxes.add(new Integer[] {numbers[i], i});
        }

        int numQuery = scanner.nextInt();
        int[] result = new int[numQuery];
        int[][] queries = new int[numQuery][3];
        for (int i = 0; i < numQuery; i++) {
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
            queries[i][2] = i;
        }
        Arrays.sort(queries, (int[] r1, int[] r2) -> {
            return (r1[0] != r2[0]) ? r1[0] - r2[0] : r1[1] - r2[1];
        });

        int[] appearance = new int[lengthSeq + 1];
        int[] bit = new int[lengthSeq + 1];
        int pointer = 0;
        for (int i = 1; i <=  queries[queries.length - 1][0]; i++) {
            Integer[] curr = maxes.poll();
            assert curr != null;
            appearance[curr[1]] = 1;
            update(bit, curr[1]);
            while (pointer < queries.length && queries[pointer][0] == i) {
                int targetIndex = queries[pointer][1];
                int left = 1;
                int right = lengthSeq;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (query(bit, mid) >= targetIndex) right = mid; else left = mid + 1;
                }
                result[queries[pointer][2]] = numbers[left];
                pointer++;
            }

        }
        for (int out: result) System.out.println(out);
    }

    private static void update(int[] bit, int index) {
        for (; index < bit.length; index += index & -index) bit[index]++;
    }

    private static int query(int[] bit, int index) {
        int answer = 0;
        for (; index > 0; index -= index & -index) answer += bit[index];
        return answer;
    }
}



