package CodeForce.P520B;

import java.util.Scanner;

public class TwoButtons_StandardSolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initialNumber = scanner.nextInt();
        int desiredNumber = scanner.nextInt();
        System.out.println(findMinimumClicks(initialNumber, desiredNumber, 0));
    }

    public static int findMinimumClicks(int initialNumber, int desiredNumber, int count) {
        if (initialNumber >= desiredNumber)
            return count + initialNumber - desiredNumber;
        else if (desiredNumber % 2 == 1)
            return findMinimumClicks(initialNumber, desiredNumber + 1, count + 1);
        else return findMinimumClicks(initialNumber, desiredNumber / 2, count + 1);
    }
}

