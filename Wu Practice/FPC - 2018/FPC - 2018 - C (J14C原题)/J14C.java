// just check if the number is prime
// actually loop to 10^5 is fast enough. No need for fancy things.
import java.util.Scanner;

public class J14C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long in = sc.nextLong();
        boolean toLoop = true;
        // basic checks
        if (in < 2) {
            System.out.println("BROKEN");
            toLoop = !toLoop;
        } else if (in == 2 || in == 3) {
            System.out.println("SAFE");
            toLoop = !toLoop;
        } else if (in % 2 == 0 || in % 3 == 0 || (in % 6 != 1 && in % 6 != 5)) {
            System.out.println("BROKEN");
            toLoop = !toLoop;
        }
        if (!toLoop) System.exit(0);
        int bound = (int) Math.ceil(Math.sqrt(in));
        for (int i = 5; i < bound; i+=2) {
            if (in % i == 0) {
                System.out.println("BROKEN");
                System.exit(0);
            }
        }
        System.out.println("SAFE");
    }
}
