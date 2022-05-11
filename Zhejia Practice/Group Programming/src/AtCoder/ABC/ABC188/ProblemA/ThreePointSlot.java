package AtCoder.ABC.ABC188.ProblemA;

import java.util.Scanner;

public class ThreePointSlot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();
        int Y = in.nextInt();
        System.out.println(Math.abs(X - Y) < 3 ? "Yes" : "No");
    }
}
