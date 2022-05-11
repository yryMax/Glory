package AtCoder.ABC.ABC188.ProblemF;

import java.util.HashMap;
import java.util.Scanner;

public class Plus1Minus1MultiplyBy2 {
    static long X;
    static long Y;
    static HashMap<Long, Long> memory;

    private static long solution(long num) {
        if (num == 1) return Math.abs(num - X);
        if (memory.containsKey(num)) return memory.get(num);
        long result;
        if (num % 2 == 1) {
            result = Math.min(Math.abs(num - X), Math.min(solution((num + 1) / 2), solution((num - 1) / 2)) + 2);
        }
        else {
            result = Math.min(Math.abs(num - X), solution(num / 2) + 1);
        }
        memory.put(num, result);
        return result;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        X = in.nextLong();
        Y = in.nextLong();
        if (X == Y) {
            System.out.println(0);
            return;
        }
        memory = new HashMap<>();
        System.out.println(solution(Y));
    }
}
