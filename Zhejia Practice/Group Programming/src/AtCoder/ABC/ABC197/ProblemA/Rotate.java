package AtCoder.ABC.ABC197.ProblemA;

import java.util.Scanner;

public class Rotate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        StringBuilder out = new StringBuilder();
        out.append(s.charAt(1));
        out.append(s.charAt(2));
        out.append(s.charAt(0));
        System.out.println(out.toString());
    }
}
