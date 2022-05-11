package AcWing.P170;

import java.io.PrintWriter;
import java.util.Scanner;

public class AdditionChains {
    static int[] path;
    static int num;
    private static boolean dfs(int index ,int depth) {
        if (index == depth) return path[index - 1] == num;
        boolean[] visited = new boolean[101];
        for (int i = index - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int sum = path[i] + path[j];
                if (sum >= path[index - 1] && sum <= num && !visited[sum]) {
                    visited[sum] = true;
                    path[index] = sum;
                    if (dfs(index + 1, depth)) return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNext()) {
            num = in.nextInt();
            if (num == 0) return;
            path = new int[102];
            int depth = 1;
            path[0] = 1;
            while (!dfs(1, depth)) depth++;
            for (int number: path) {
                if (number == 0) break;
                out.print(number + " ");
            }
            out.print("\n");
            out.flush();
        }
    }
}
