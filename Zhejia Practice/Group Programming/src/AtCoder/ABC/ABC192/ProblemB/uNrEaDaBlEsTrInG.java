package AtCoder.ABC.ABC192.ProblemB;

import java.util.Scanner;

public class uNrEaDaBlEsTrInG {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        for (int i = 0; i < s.length(); i++)
            if (i % 2 == 0 && (s.charAt(i) < 97 || s.charAt(i) > 122)) {
                System.out.println("No");
                return;
            } else if (i % 2 == 1 && (s.charAt(i) < 65 || s.charAt(i) > 90)) {
                System.out.println("No");
                return;
            }
        System.out.println("Yes");
    }
}
