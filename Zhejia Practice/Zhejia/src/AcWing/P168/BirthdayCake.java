package AcWing.P168;

import java.io.PrintWriter;
import java.util.Scanner;

public class BirthdayCake {
    static int vol;
    static int depth;
    static int[] radius;
    static int[] heights;
    static int[] minVol;
    static int[] minSurface;
    static int ans;

    private static void DFSearch(int currDepth, int currVol, int currSurface) {

        if (currVol + minVol[currDepth] > vol) return;
        if (currSurface + minSurface[currDepth] >= ans) return;
        if (currSurface + 2 * (vol - currVol) / radius[currDepth + 1] >= ans) return;

        if (currDepth == 0) {
            if (currVol == vol) ans = currSurface;
            return;
        }

        for (int r =  Math.min((int) Math.sqrt(vol - currVol), radius[currDepth + 1] - 1); r >= currDepth; r-- ) {
            for (int h = Math.min((int) ((vol - currVol) / (double) (r * r)), heights[currDepth + 1] - 1); h >= currDepth; h--) {
                int bottomSurface = 0;
                if (currDepth == depth) bottomSurface = r * r;
                radius[currDepth] = r;
                heights[currDepth] = h;
                DFSearch(currDepth - 1, currVol + r * r * h, currSurface + 2 * r * h + bottomSurface);

            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        vol = in.nextInt();
        depth = in.nextInt();

        radius = new int[depth + 2];
        heights = new int[depth + 2];
        minVol = new int[depth + 1];
        minSurface = new int[depth + 1];
        ans = Integer.MAX_VALUE;

        radius[depth + 1] = Integer.MAX_VALUE;
        heights[depth + 1] = Integer.MAX_VALUE;

        for (int i = 1; i <= depth; i++) {
            minVol[i] = minVol[i - 1] + i * i * i;
            minSurface[i] = minSurface[i - 1] + 2 * i * i;
         }

        DFSearch(depth, 0, 0);
        out.println((ans != Integer.MAX_VALUE)  ? ans : 0);
        out.flush();
    } 
}
