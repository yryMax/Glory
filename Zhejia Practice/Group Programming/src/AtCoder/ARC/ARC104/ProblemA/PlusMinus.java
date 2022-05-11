package AtCoder.ARC.ARC104.ProblemA;

import java.io.PrintWriter;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int x = in.nextInt();
        int y = in.nextInt();
        out.print((x + y) / 2 + " ");
        out.print((x - y) / 2);
        out.flush();
    }
}
