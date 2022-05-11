package AtCoder.ABC.ABC199.ProblemA;

import java.util.Scanner;

public class SquareInequality {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt(), B = in.nextInt(), C = in.nextInt();
        System.out.println((A * A + B * B < C * C) ? "Yes" : "No");
    }
}
