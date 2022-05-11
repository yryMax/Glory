package AtCoder.ABC.ABC187.ProblemC;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class SAT {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String[] strings = new String[N];
        for (int i = 0; i < N; i++)
            strings[i] = in.next();
        long[][] code = new long[N][2];
        for (int i = 0; i < N; i++) {
            code[i][0] = hash(strings[i]);
            code[i][1] = i;
        }
        Arrays.sort(code, Comparator.comparingLong((long[] c) -> Math.abs(c[0])));
        for (int i = 0; i < N - 1; i++) {
            if (code[i][0] == -code[i + 1][0]) {
                if (code[i][0] > 0) System.out.println(strings[(int) code[i][1]]);
                else System.out.println(strings[(int) code[i + 1][1]]);
                return;
            }
        }
        System.out.println("satisfiable");
    }

    private static long hash(String string) {
        if (string.startsWith("!")) return - hashFunc(string.substring(1));
        else return hashFunc(string);
    }

    private static long hashFunc(String string) {
        long hash = 31;

        for (int i = 0; i < string.length(); i++) {
            hash = hash * 131 + (string.charAt(i) - 'a');
        }
        return hash;
    }
}
