package AtCoder.ABC.ABC198.ProblemB;

import java.util.Scanner;

public class PalindromeWithLeadingZeros {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        if (input.length() == 1) {
            System.out.println("Yes");
            return;
        }
        int secondHead = 0;
        int begin = 0;
        for (int i = 0; i < input.length(); i++)
            if (input.charAt(begin) != '0') {
                begin = i;
                break;
            }

        for (int i = input.length() - 1; i >= 0; i--)
            if (input.charAt(i) == input.charAt(begin)) {
                secondHead = i;
                break;
            }

        for (int i = secondHead + 1; i < input.length(); i++)
            if (input.charAt(i) != '0') {
                System.out.println("No");
                return;
            }
        for (int i = begin; i <= secondHead; i++)
            if (input.charAt(i) != input.charAt(secondHead - i)) {
                System.out.println("No");
                return;
            }
        System.out.println("Yes");
    }
}
