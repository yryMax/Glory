package AtCoder.ABC.ABC186.ProblemA;

import java.io.PrintWriter;
import java.util.Scanner;

public class Brick {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        int W = in.nextInt();
        out.println(N / W);
        out.flush();
    }
}
