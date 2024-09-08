import java.util.Scanner;

public class J16B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long p = sc.nextInt(); // prime 1
        long q = sc.nextInt(); // prime 2
        long e = sc.nextInt();
        long c = sc.nextInt();
        long n = p * q;
        long d = key(p,q,e);
        // M = C**d (mod n)
        long m = 1;
        for (int i = 0; i < d; i++) {
            m *= c;
            m %= n;
        }
        System.out.println(m);
    }

    public static long key(long p, long q, long e) {
        long n = p * q;
        long nn = n - (p + q - 1);
        // d * e = 1 (mod nn)
        // d * e / nn = ?? .... 1
        long d = 0;
        for (int i = 1; i < e; i++) {
            if (((i * nn + 1d)/e) % 1 == 0) {
                d = (i * nn + 1)/e;
                break;
            }
        }
//        System.out.println(d);
        return d;
    }
}
