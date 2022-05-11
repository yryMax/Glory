import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class J15A {
    static int[] gx;
    static int[] gy;
    static int[] gr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int g = sc.nextInt();
        gx = new int[g];
        gy = new int[g];
        gr = new int[g];
        int[] vis = new int[g];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < g; i++) {
            gx[i] = sc.nextInt();
            gy[i] = sc.nextInt();
            gr[i] = sc.nextInt();
        }
        for (int i = 0; i < g; i ++) {
            if (gx[i] - gr[i] <= 0 || gy[i] + gr[i] >= h) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            vis[cur]++;
            if (gx[cur] + gr[cur] >= w || gy[cur] - gr[cur] <= 0) {
                System.out.println("NO ESCAPE");
                System.exit(0);
            } else {
                for (int i = 0; i < g; i++) {
                    if (vis[i] == 0 && connected(cur,i)) {
                        queue.add(i);
                    }
                }
            }
        }
        System.out.println("ESCAPE");
    }
    public static boolean connected(int a, int b) {
        int difx = (gx[a] - gx[b]) * (gx[a] - gx[b]);
        int dify = (gy[a] - gy[b]) * (gy[a] - gy[b]);
        double dif = difx + dify; // real distance
        return dif <= (gr[a] + gr[b]) * (gr[a] + gr[b]);
    }
}
