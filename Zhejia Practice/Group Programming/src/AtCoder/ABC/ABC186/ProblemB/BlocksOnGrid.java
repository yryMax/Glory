package AtCoder.ABC.ABC186.ProblemB;

import java.io.PrintWriter;
import java.util.Scanner;

public class BlocksOnGrid {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int H = in.nextInt();
        int W = in.nextInt();
        int[][] arr = new int[H][W];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                arr[i][j] = in.nextInt();
                min = Math.min(min, arr[i][j]);
            }
        }

        int count = 0;
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                count += (arr[i][j] - min);
        out.println(count);
        out.flush();
    }
}
