import java.util.Scanner;

public class J16C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean early = false;
        long l = sc.nextLong(); // fpc len
        long n = sc.nextLong(); // student amount
        double s = sc.nextDouble(); // best Sec
        double minS = 100000007;
        for (int i = 0; i < n; i++) {
            double s1 = l / sc.nextDouble();
            double s2 = l / sc.nextDouble();
            if (s1+s2 < minS) minS = s1+s2;
            if (minS < s) {
                early = true;
                break;
            }
        }

        System.out.println(early ? "HOPE" : "DOOMED");
    }
}
