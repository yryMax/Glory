package AtCoder.ARC.ARC104.ProblemC;

import java.io.PrintWriter;
import java.util.Scanner;

public class FairElevator {
    static int numPerson;
    static int[][] records;
    static boolean[] dp;
    static int[][] stairs;

    static boolean[] occupied;
    private static boolean check(int left, int right) {
        if ((right - left + 1) % 2 == 1) return false;
        occupied = new boolean[2 * numPerson + 1];
        for (int i = 0; i < numPerson; i++) {
            int on = records[i][0], off = records[i][1];
            if (on == -1 && off == -1) continue;
            else if (on != -1 && off != -1) {
                if ((on < left && off > right) || (on > right) || (off < left)) continue;
                if (off - on != (right - left + 1) / 2) return false;
                if (occupied[on] || occupied[off]) return false;
                occupied[on] = true;
                occupied[off] = true;
            } else if (on != -1) {
                if (on < left || on > right) continue;
                if (occupied[on]) return false;
                int potentialOff = on + (right - left + 1) / 2;
                if (potentialOff > right || occupied[potentialOff]) return false;
                occupied[on] = true;
                occupied[potentialOff] = true;
            } else {
                if (off < left || off > right) continue;
                if (occupied[off]) return false;
                int potentialOn = off - (right - left + 1) / 2;
                if (potentialOn < left || occupied[potentialOn]) return false;
                occupied[off] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        numPerson = in.nextInt();
        records = new int[numPerson][2];

        dp = new boolean[2 * numPerson + 1];
        stairs = new int[2 * numPerson + 1][2];
        for (int i = 0; i < numPerson; i++) {
            records[i][0] = in.nextInt();
            records[i][1] = in.nextInt();
            if (records[i][0] != -1)
                stairs[records[i][0]] = new int[] {i + 1, -1}; // -1 => on
            if (records[i][1] != -1)
                stairs[records[i][1]] = new int[] {i + 1, 1}; // 1 => off
        }

        dp[0] = true;
        for (int i = 0; i < 2 * numPerson; i++) {
            for (int j = i + 1; j <= 2 * numPerson; j++) {
                dp[j] |= dp[i] && check(i + 1, j);
            }
        }
        if (dp[2 * numPerson]) out.println("Yes");
        else out.println("No");
        out.flush();
    }
}
