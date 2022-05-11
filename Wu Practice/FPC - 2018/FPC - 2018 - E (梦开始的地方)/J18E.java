import java.util.Scanner;

public class J18E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long in = sc.nextLong();
        long ans = 0;
        while (in >= 5) {
            ans += in/5;
            in /=5;
        }
        System.out.println(ans);
    }
}
