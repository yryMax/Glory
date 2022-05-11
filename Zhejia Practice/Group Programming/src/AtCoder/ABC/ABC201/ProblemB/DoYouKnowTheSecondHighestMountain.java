package AtCoder.ABC.ABC201.ProblemB;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DoYouKnowTheSecondHighestMountain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Map<Integer, String> names = new HashMap<>();
        int[][] heights = new int[N][2];
        for (int i = 0; i < N; i++) {
            names.put(i, in.next());
            heights[i][0] = in.nextInt();
            heights[i][1] = i;
        }
        Arrays.sort(heights, (int[] h1, int[] h2) -> h1[0] - h2[0]);
        System.out.println(names.get(heights[N - 2][1]));
    }
}
