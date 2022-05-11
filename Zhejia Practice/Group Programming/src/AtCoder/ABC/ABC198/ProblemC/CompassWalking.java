package AtCoder.ABC.ABC198.ProblemC;

import java.util.Scanner;

public class CompassWalking {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(), X = in.nextInt(), Y = in.nextInt();
        double XPrime = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
        while (XPrime < R) XPrime += R;
        int cursor = 0;
        int count = 0;
        while (XPrime - cursor > 2 * R) {
            cursor += R;
            count++;
        }
        System.out.println(count + ((cursor + R == XPrime) ? 1 : 2));
    }
}
