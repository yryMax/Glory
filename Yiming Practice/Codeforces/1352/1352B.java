import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int cases = sc.nextInt();
        while(cases -- > -0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (a % 2 == 0) {
                if (b <= a / 2) {
                    int cur = (b-1)*2;
                    int remain = a - cur;
                    pw.println("YES");
                    for (int i = 0; i < b-1; i++) {
                        pw.print("2 ");
                    }
                    pw.println(remain);
                } else {
                    if (b == a) {
                        pw.println("YES");
                        for (int i = 0; i < b; i++) {
                            if (i != b-1) {
                                pw.print("1 ");
                            } else {
                                pw.println("1");
                            }
                        }
                    } else if( b < a && b % 2 == a % 2) {
                        int cur = (b-1);
                        int remain = a - cur;
                        pw.println("YES");
                        for (int i = 0; i < b-1; i++) {
                            pw.print("1 ");
                        }
                        pw.println(remain);
                    } else {
                        pw.println("NO");
                    }
                }
            } else {
                if (b % 2 != a % 2 || b > a) {
                    pw.println("NO");
                } else {
                    int cur = (b-1);
                    int remain = a - cur;
                    pw.println("YES");
                    for (int i = 0; i < b-1; i++) {
                        pw.print("1 ");
                    }
                    pw.println(remain);
                }
            }
        }
        pw.close();
    }
}