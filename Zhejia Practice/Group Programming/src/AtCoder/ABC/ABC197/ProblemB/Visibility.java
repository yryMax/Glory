package AtCoder.ABC.ABC197.ProblemB;

import java.util.Scanner;

public class Visibility {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt(), W = in.nextInt(), X = in.nextInt(), Y = in.nextInt();
        boolean[][] field = new boolean[H + 1][W + 1];
        in.nextLine();
        for (int r = 1; r <= H; r++) {
            String currRow = in.nextLine();
            for (int c = 0; c < W; c++) if (currRow.charAt(c) == '#')
                field[r][c + 1] = true;
        }
        if (field[X][Y]) {
            System.out.println(0);
            return;
        }
        long ans = 1;
        for (int r = X - 1; r > 0; r--) {
            if (field[r][Y]) break;
            ans++;
        }
        for (int r = X + 1; r <= H; r++) {
            if (field[r][Y]) break;
            ans++;
        }
        for (int c = Y - 1; c > 0; c--) {
            if (field[X][c]) break;
            ans++;
        }
        for (int c = Y + 1; c <= W; c++) {
            if (field[X][c]) break;
            ans++;
        }
        System.out.println(ans);
    }
}
