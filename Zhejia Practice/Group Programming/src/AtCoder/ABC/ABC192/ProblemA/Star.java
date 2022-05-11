package AtCoder.ABC.ABC192.ProblemA;

import java.util.Scanner;

public class Star {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();
        for (int i = 1; i <= 100; i++)
            if ((X + i) % 100 == 0) System.out.println(i);
    }
}
