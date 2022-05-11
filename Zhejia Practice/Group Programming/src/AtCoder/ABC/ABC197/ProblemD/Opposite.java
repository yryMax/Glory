package AtCoder.ABC.ABC197.ProblemD;

import java.util.Scanner;

public class Opposite {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), x_0 = in.nextInt(), y_0 = in.nextInt(), x_half = in.nextInt(), y_half = in.nextInt();
        double angle = 2 * Math.PI / N;
        double x_centre = (x_0 + x_half) / 2.0, y_centre = (y_0 + y_half) / 2.0;
        double x_0_moved = x_0 - x_centre, y_0_moved = y_0 - y_centre;
        double x_1_moved = Math.cos(angle) * x_0_moved - Math.sin(angle) * y_0_moved, y_1_moved = Math.sin(angle) * x_0_moved + Math.cos(angle) * y_0_moved;
        System.out.println((x_1_moved + x_centre) + " " + (y_1_moved + y_centre));
    }
}
