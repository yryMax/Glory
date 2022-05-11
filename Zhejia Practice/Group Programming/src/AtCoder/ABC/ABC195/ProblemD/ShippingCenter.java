package AtCoder.ABC.ABC195.ProblemD;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ShippingCenter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        int M = in.nextInt();
        int Q = in.nextInt();
        int[][] baggages = new int[N][2];
        for (int i = 0; i < N; i++) {
            baggages[i][0] = in.nextInt();
            baggages[i][1] = in.nextInt();
        }

        int[] boxes = new int[M + 1];
        for (int i = 1; i <= M; i++)
            boxes[i] = in.nextInt();

        Arrays.sort(baggages, new Comparator<int[]>() {
            @Override
            public int compare(int[] b1, int[] b2) {
                return (b1[1] != b2[1]) ? -b1[1] + b2[1] : b1[0] - b2[0];
            }
        });

        while (Q-- > 0) {
            int left = in.nextInt();
            int right = in.nextInt();
            long ans = 0;
            boolean[] occupied = new boolean[M + 1];
            for (int baggage = 0; baggage < baggages.length; baggage++) {
                int boxInd = 0;
                for (int box = 1; box <= M; box++) {
                    if (box >= left && box <= right) continue;
                    if (boxes[box] < baggages[baggage][0]) continue;
                    if (occupied[box]) continue;
                    if (boxInd == 0 || boxes[box] < boxes[boxInd]) {
                        occupied[boxInd] = false;
                        boxInd = box;
                        occupied[box] = true;
                    }
                }
                if (boxInd != 0) ans += baggages[baggage][1];
            }
            System.out.println(ans);
        }

    }
}
