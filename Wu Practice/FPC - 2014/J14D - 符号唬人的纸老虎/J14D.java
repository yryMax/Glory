import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class J14D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of diseases
        int m = sc.nextInt(); // number of symptoms
        int k = sc.nextInt(); // set of disease
        ArrayList<Integer> dis = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            dis.add(sc.nextInt());
        }
        int index = 1;
        HashSet<Integer> symps = new HashSet<>();
        while (n -- > 0) {
            if (dis.contains(index)) {
                int amount = sc.nextInt();
                while (amount-- > 0) {
                    symps.add(sc.nextInt());
                }
            } else {
                int amount = sc.nextInt();
                while (amount-- > 0) {
                    sc.nextInt();
                }
            }
            index++;
        }
        for (int i = 0; i < m; i++) {
            if (symps.contains(i+1)) continue;
            else {
                System.out.println("no");
                System.exit(0);
            }
        }
        System.out.println("yes");
    }
}
