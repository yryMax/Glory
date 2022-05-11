package CodeForce.P538B;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuasiBinary_MySolution {

    static List<Integer> answers = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(decomposeToQuasiBinary(number));
        answers.sort(Integer::compare);
        for (int answer: answers)
            System.out.print(answer + " ");
    }

    private static int decomposeToQuasiBinary(int number) {
       if (isQuasiBinary(number)) {
           answers.add(number);
           return 1;
       }
       else if (number == 0) return 0;
       else {
           List<Integer> digits = getDigits(number);
           int largestBinary = parseNumber(digits.stream().map(x -> x = (x >= 1) ? 1 : 0).collect(Collectors.toList()));
           answers.add(largestBinary);
           return 1 + decomposeToQuasiBinary(number - largestBinary);
       }
    }

    public static boolean isQuasiBinary(int number) {
        List<Integer> digits = getDigits(number);
        for (int digit: digits)
            if (digit > 1) return false;
        return true;
    }

    public static List<Integer> getDigits(int number) {
        List<Integer> digits = new ArrayList<>();
        while (number > 0) {
            digits.add(0, number % 10);
            number = number / 10;
        }
        return digits;
    }

    public static int parseNumber(List<Integer> digits) {
        int sum = 0;
        for (int i = 0; i < digits.size(); i++)
            sum += digits.get(i) * ((int) Math.pow(10, digits.size() - 1 - i));
        return sum;
    }
}
