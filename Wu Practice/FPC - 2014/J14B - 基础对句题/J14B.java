import java.util.Scanner;

public class J14B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        int n = in.length();

        String left = in.substring(0,n/2);
        String right;
        if (n % 2 == 0) {
            right = in.substring(n/2,n);
        } else {
            right = in.substring(n/2 + 1, n);
        }
        for (int i = 0; i < n/2; i++) {
            if (left.charAt(i) != right.charAt((n/2)-1-i)) {
                System.out.println("boop");
                System.exit(0);
            }
        }
        System.out.println("beep");
    }
}
