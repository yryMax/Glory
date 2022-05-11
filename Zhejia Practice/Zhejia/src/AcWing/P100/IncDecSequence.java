package AcWing.P100;

import java.util.Scanner;

public class IncDecSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        long[] numbers =  new long[total];
        for (int i = 0; i < total; i++)
            numbers[i] = scanner.nextLong();

        flattenNumbers(numbers);
    }

    private static void flattenNumbers(long[] numbers) {
        long[] difference = new long[numbers.length + 1];
        difference[0] = numbers[0];
        difference[numbers.length] = 0;

        for (int index = 1; index < difference.length - 1; index++)
            difference[index] = numbers[index] - numbers[index - 1];

        long positiveNumber = 0;
        long negativeNumber = 0;
        for (int index = 1; index < difference.length - 1; index++)
            if (difference[index] > 0) positiveNumber += difference[index];
            else negativeNumber -= difference[index];
        System.out.println(Math.max(positiveNumber, negativeNumber));
        System.out.println(Math.abs(positiveNumber - negativeNumber) + 1);
    }
}
