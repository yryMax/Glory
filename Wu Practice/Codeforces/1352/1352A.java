import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int cases = sc.nextInt();
        while (cases-- > 0) {
            int tmp = sc.nextInt();
            ArrayList<Integer> tmpL = new ArrayList<>();
            int counter = 0;
            int base = 1;
            while (tmp != 0) {
                int digit = tmp % 10;
                if (digit != 0) {
                    counter++;
                    tmpL.add(0,digit*base);
                }
                tmp /= 10;
                base *= 10;
            }
            pw.println(counter);
            for (int i = 0; i < tmpL.size(); i++) {
                if (i != tmpL.size() - 1) {
                    pw.print(tmpL.get(i) + " ");
                } else {
                    pw.println(tmpL.get(i));
                }
            }
        }
        pw.close();
    }
}
