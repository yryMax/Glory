package CodeForce.P1374C;

import java.util.Scanner;

public class MoveBrackets_MySolution {
    public static void main(String[] args) {
        Scanner scannerTerminal = new Scanner(System.in);
        int numberTestCases = Integer.parseInt(scannerTerminal.nextLine());
        while (numberTestCases > 0) {
            int numberBrackets = Integer.parseInt(scannerTerminal.nextLine());
            String brackets = scannerTerminal.nextLine();
            findMinimumMove(numberBrackets, brackets);
            numberTestCases--;
        }
    }

    private static void findMinimumMove(int numberBrackets, String brackets) {
        int minValue = 0, currentValue = 0;
        for (int i = 0; i < numberBrackets; i++) {
            currentValue = (brackets.charAt(i) == '(') ? currentValue + 1 : currentValue - 1;
            if (currentValue < minValue) minValue = currentValue;
        }
        System.out.println(-minValue);
    }
}
