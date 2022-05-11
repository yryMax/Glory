package AtCoder.ABC.ABC194.ProblemA;

import java.util.Scanner;

public class IScream {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        int perSolid = A + B;
        if (perSolid >= 15 && B >= 8)
            System.out.println(1);
        else if (perSolid >= 10 && B >= 3)
            System.out.println(2);
        else if (perSolid >= 3)
            System.out.println(3);
        else System.out.println(4);
    }
}
