package CodeForce.P652D;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class NestedSegments {
    static int[] indexTree;
    static int[] lefts;
    static int[] mapping;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int num = in.nextInt();
        indexTree = new int[num];
        int[][] segments = new int[num][3];
        lefts = new int[num];
        for (int i = 0; i < num; i++) {
            segments[i][0] = in.nextInt();
            segments[i][1] = in.nextInt();
            segments[i][2] = i;
            lefts[i] = segments[i][0];
        }

        int[] results = new int[num];
        Arrays.sort(segments, (int[] s1, int[] s2) -> (s1[1] != s2[1]) ? s1[1] - s2[1] : s1[0] - s2[0]);
        discrete();
        update(find(segments[0][0]));
        results[segments[0][2]] = 0;
        for (int s = 1; s < num; s++) {
            results[segments[s][2]] = s - query(find(segments[s][0]));
            update(find(segments[s][0]));
        }
        for (int i = 0; i < num; i++) out.print(results[i] + " ");
        out.flush();
    }

    private static void discrete() {
        mapping = Arrays.stream(lefts).distinct().toArray();
        Arrays.sort(mapping);
    }

    private static int find(int target) {
        return Arrays.binarySearch(mapping, target);
    }

    private static int query(int index) {
        if (index == 0) return indexTree[index];
        int ans = 0;
        for (; index > 0; index -= (index & -index)) ans += indexTree[index];
        ans += indexTree[0];
        return ans;
    }

    private static void update(int index) {
        if (index == 0) {
            indexTree[0]++;
            return;
        }
        for (; index < indexTree.length; index += (index & -index)) indexTree[index]++;
    }
}
