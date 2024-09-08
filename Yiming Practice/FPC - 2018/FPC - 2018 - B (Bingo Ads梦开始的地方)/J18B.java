import java.util.HashMap;
import java.util.Scanner;

// bingo

public class J18B {

    public static int n;
    public static int[][] paper;
    public static HashMap<String, int[]> grid;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        paper = new int[n][n];
        grid = new HashMap<>();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                String cur = line[j];
                grid.put(cur, new int[]{i,j});
            }
        }
        paper[n/2][n/2]++;
        if (n == 1) {
            System.out.println(0);
            System.exit(0);
        }
        while (m-- > 0) {
            counter++;
            String event = sc.nextLine();
            if (grid.keySet().contains(event)) {
                int x = grid.get(event)[0];
                int y = grid.get(event)[1];
                paper[x][y]++;
            }
            if (grid.get(event) == null) continue;
            if (checkRowColumn(grid.get(event)[0], grid.get(event)[1])) {
                System.out.println(counter);
                System.exit(0);
            }
        }

        System.out.println(":-(");
    }

    public static boolean checkRowColumn(int r,int c) {
        boolean a = true;
        boolean b = true;
        boolean d1 = true;
        boolean d2 = true;
        for (int i = 0; i < n; i++) {
            if (paper[r][i] == 0) {
                a = false;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (paper[i][c] == 0) {
                b = false;
                break;
            }
        }
        if (c != r) {
            return a || b;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if(paper[j][j] == 0) {
                        d1 = false;
                        break;
                    }
                } else {
                    if(paper[j][n-j-1] == 0) {
                        d2 = false;
                        break;
                    }
                }
            }
        }
        return (a || b || d1 || d2);
    }
}
