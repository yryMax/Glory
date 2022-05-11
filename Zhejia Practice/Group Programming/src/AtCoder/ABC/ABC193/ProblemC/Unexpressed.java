package AtCoder.ABC.ABC193.ProblemC;

import java.util.Scanner;

public class Unexpressed {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        long could = 0;
        int largeBase = (int) (Math.sqrt(N));
        boolean[] bases = new boolean[largeBase + 1];
        for (int base = 2; base <= largeBase; base++) {
            if (bases[base]) continue;
            int curr = base;
            while (curr > 0 && curr <= largeBase) {
                bases[curr] = true;
                curr *= base;
            }
            could += Math.floor(Math.log(N) / Math.log(base)) - 1;
        }
        System.out.println(N - could);
    }
}
