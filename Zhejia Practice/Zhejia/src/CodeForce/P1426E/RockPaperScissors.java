package CodeForce.P1426E;

import java.io.PrintWriter;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int round = in.nextInt();
        int[] alice = new int[3];
        int[] bob = new int[3];
        alice[0] = in.nextInt();
        alice[1] = in.nextInt();
        alice[2] = in.nextInt();
        bob[0] = in.nextInt();
        bob[1] = in.nextInt();
        bob[2] = in.nextInt();

        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < 3; i++)
            if (alice[i] - (bob[i] + bob[mod3(i - 1)]) >= 0) min = Math.min(min, alice[i] - (bob[i] + bob[mod3(i - 1)]));
        for (int i = 0; i < 3; i++)
            if (bob[i] - (alice[i] + alice[mod3(i + 1)]) >= 0) min = Math.min(min, bob[i] - (alice[i] + alice[mod3(i + 1)]));
        if (min == Integer.MAX_VALUE) min = 0;
        out.print(min + " ");

        for (int i = 0; i < 3; i++)
            max += Math.min(alice[i], bob[mod3(i + 1)]);
        out.print(max);
        out.flush();
    }

    private static int mod3(int index) {
        if (index >= 3) index-= 3;
        else if (index < 0) index += 3;
        return index;
    }


}


