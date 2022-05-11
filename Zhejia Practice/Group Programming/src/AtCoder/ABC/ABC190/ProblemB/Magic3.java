package AtCoder.ABC.ABC190.ProblemB;

import java.util.Scanner;

public class Magic3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int S = in.nextInt();
        int D = in.nextInt();
        while (N-- > 0) {
            int X = in.nextInt();
            int Y = in.nextInt();
            if (X < S && Y > D) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}
