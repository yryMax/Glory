package AtCoder.ABC.ABC190.ProblemA;

import java.util.Scanner;

public class VeryVeryPrimitiveGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();
        if (A > B) System.out.println("Takahashi");
        else if (A < B) System.out.println("Aoki");
        else System.out.println((C == 0) ? "Aoki" : "Takahashi");
    }
}
