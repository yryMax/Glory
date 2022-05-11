package AcWing.P167;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Sticks {
    static Integer[] sticks;
    static boolean[] used;
    static int numPiece;
    static int sum;
    static int minLength;
    static int illegal;


    private static boolean DFSearch(int numFixed, int lengthFixed, int index) {
        if (numFixed * minLength == sum) return true;
        if (lengthFixed == minLength) return DFSearch(numFixed + 1, 0, 0);

        for (int i = index; i < numPiece - illegal; i++) {
            if (used[i]) continue;
            int currLength = sticks[i];
            if (currLength + lengthFixed > minLength) continue;
            used[i] = true;
            if (DFSearch(numFixed, lengthFixed + currLength, index + 1)) return true;
            used[i] = false;
            if (lengthFixed == 0) return false;
            if (lengthFixed + currLength == minLength) return false;
            int j = i;
            while (j < numPiece - illegal && Objects.equals(sticks[j], sticks[i])) j++;
            i = j - 1;
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNextInt()) {
            numPiece = in.nextInt();
            if (numPiece == 0) return;
            sticks = new Integer[numPiece];
            used = new boolean[numPiece];
            sum = 0;
            illegal = 0;
            minLength = Integer.MIN_VALUE;
            for (int i = 0; i < numPiece; i++) {
                sticks[i] = in.nextInt();
                if (sticks[i] > 50) {
                    sticks[i] = 0;
                    illegal++;
                    continue;
                }
                sum += sticks[i];
                minLength = Math.max(minLength, sticks[i]);
            }


            Arrays.sort(sticks, Collections.reverseOrder());

            while (minLength <= sum) {
                if (sum % minLength == 0 && DFSearch(0, 0, 0)) {
                    out.println(minLength);
                    out.flush();
                    break;
                }
                minLength++;
            }
        }

    }
}
