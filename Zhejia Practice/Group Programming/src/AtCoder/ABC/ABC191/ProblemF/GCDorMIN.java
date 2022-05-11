package AtCoder.ABC.ABC191.ProblemF;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GCDorMIN {
    static int N;
    static int[] numbers;
    static Map<Integer, Integer> check;

    private static int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) min = Math.min(min, numbers[i]);
        return min;
    }

    private static int getGCD(int x, int y) {
        if (x == 0) {
            return y;
        } else {
            return getGCD(y % x, x);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        numbers = new int[N + 1];
        check = new HashMap<>();
        for (int i = 1; i <= N; i++) numbers[i] = in.nextInt();
        int upperBound = getMin();
        for (int i = 1; i <= N; i++) {
            int currNum = numbers[i];
            for (int divisor = 1; divisor <= Math.min(upperBound, Math.sqrt(currNum)); divisor++) {
                if (currNum % divisor != 0) continue;
                if (!check.containsKey(divisor)) check.put(divisor, currNum);
                check.put(divisor, getGCD(check.get(divisor), currNum));
                int compliment = currNum / divisor;
                if (!check.containsKey(compliment)) check.put(compliment, currNum);
                check.put(compliment, getGCD(check.get(compliment), currNum));
            }
        }
        int count = 1;
        for (int divisor: check.keySet())
            if (divisor < upperBound && divisor == check.get(divisor)) count++;
        System.out.println(count);

    }
}

