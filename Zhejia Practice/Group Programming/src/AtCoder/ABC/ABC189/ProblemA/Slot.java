package AtCoder.ABC.ABC189.ProblemA;

import java.util.Scanner;

public class Slot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println((s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2)) ? "Won" : "Lost");
    }
}
