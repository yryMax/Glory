package AtCoder.ABC.ABC195.ProblemA;

import java.util.Scanner;

public class HealthMDeath {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int H = in.nextInt();
        System.out.println((H % M == 0) ? "Yes" : "No");
    }
}
