// prime baby
import java.util.Scanner;

public class J16D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long in = sc.nextLong();

        if (in == 2) {
            System.out.println("yes");
            System.exit(0);
        }

        if (in == 0 || in == 1 || in % 2 == 0) {
            System.out.println("no");
            System.exit(0);
        }

        long bound = (long) Math.ceil(Math.sqrt(in));
        for (int i = 3; i < bound;) {
            if (in % i == 0) {
                System.out.println("no");
                System.exit(0);
            }
            i+=2;
        }
        System.out.println("yes");

    }
}
