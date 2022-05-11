package AtCoder.ARC.ARC104.ProblemB;

import java.io.PrintWriter;
import java.util.Scanner;


public class DNASequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int len = in.nextInt();
        String string = in.next();
        int result = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum = getNum(string.charAt(i));
            for (int j = i + 1; j < len; j++) {
                sum += getNum(string.charAt(j));
                if (sum == 0)
                    result++;
            }
        }
        out.println(result);
        out.flush();
    }

    private static int getNum(char ch) {
        if (ch == 'A') return 5479;
        else if (ch == 'C') return 5484;
        else if (ch == 'G') return -5484;
        else return -5479;
    }
}
