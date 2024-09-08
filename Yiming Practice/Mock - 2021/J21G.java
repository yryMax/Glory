import java.util.Scanner;
import java.util.Stack;

public class J21G {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        int size = in.length();
        if (size == 1) System.out.println(in);
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            char cur = in.charAt(i);
            switch (cur){
                case '(' -> stack.push(in.charAt(i-1));
                case ')' -> {
                    sb.append(in.charAt(i-1));
                    sb.append(stack.pop());
                }
                case ',' -> sb.append(in.charAt(i-1));
                default -> {}
            }
        }
        System.out.println(sb.toString().replace(")",""));
    }
}
