package CodeForce.P1374C;

import java.util.Scanner;
import java.util.Stack;

public class MoveBrackets_Solution_UsingStack {
    public static void main(String[] args) {
        Scanner scannerTerminal = new Scanner(System.in);
        int numberTestCases = Integer.parseInt(scannerTerminal.nextLine());
        while (numberTestCases-- > 0) {
            int numberBrackets = Integer.parseInt(scannerTerminal.nextLine());
            String brackets = scannerTerminal.nextLine();
            findMinimumMove(numberBrackets, brackets);
        }
    }

    private static void findMinimumMove(int numberBrackets, String brackets) {
        Stack<Character> bracketStack = new Stack<>();
        for (int index = 0; index < numberBrackets; index++) {
            if (brackets.charAt(index) == ')' && !bracketStack.empty() && bracketStack.peek() == '(')
                bracketStack.pop();
            else bracketStack.push(brackets.charAt(index));
        }
        System.out.println(bracketStack.size() / 2);
    }
}
