package AtCoder.ABC.ABC193.ProblemA;

import java.util.Scanner;

public class Discount {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double A = in.nextInt();
        double B = in.nextInt();
        System.out.println(((A - B) / A) * 100);
    }
}
