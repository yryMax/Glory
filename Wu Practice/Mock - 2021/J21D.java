import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class J21D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[r][];
        int[][] vis = new int[r][c];
        char[][] ans = new char[r][c];
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            grid[i] = sc.nextLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'D') {
                    queue.add(new Point(i,j));
                    vis[i][j]++;
                }
            }
        }
        int counter = 0;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (vis[cur.x][cur.y] != 1) continue;
            vis[cur.x][cur.y]++;

            if (cur.x >= 2) {
                if (vis[cur.x-1][cur.y] == 0 && grid[cur.x-1][cur.y] != '#' && grid[cur.x-2][cur.y] != '#') {
                    queue.add(new Point(cur.x-1,cur.y));
                    vis[cur.x-1][cur.y]++;
                    counter++;
                }
            }
            if (cur.x < r-2) {
                if (vis[cur.x+1][cur.y] == 0 && grid[cur.x+1][cur.y] != '#' && grid[cur.x+2][cur.y] != '#') {
                    queue.add(new Point(cur.x+1,cur.y));
                    vis[cur.x+1][cur.y]++;
                    counter++;
                }
            }
            if (cur.y >= 2) {
                if (vis[cur.x][cur.y-1] == 0 && grid[cur.x][cur.y-1] != '#' && grid[cur.x][cur.y-2] != '#') {
                    queue.add(new Point(cur.x,cur.y-1));
                    vis[cur.x][cur.y-1]++;
                    counter++;
                }
            }
            if (cur.y < r-2) {
                if (vis[cur.x][cur.y+1] == 0 && grid[cur.x][cur.y+1] != '#' && grid[cur.x][cur.y+2] != '#') {
                    queue.add(new Point(cur.x,cur.y+1));
                    vis[cur.x][cur.y+1]++;
                    counter++;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'D') {
                    ans[i][j] = 'O';
                    continue;
                }
                if (vis[i][j] > 0) {
                    ans[i][j] = 'O';
                } else {
                    ans[i][j] = 'X';
                }
            }
        }
        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                pw.print(ans[i][j]);
            }
            pw.println();
        }
//        pw.println(counter);
        pw.close();
    }
}
class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}