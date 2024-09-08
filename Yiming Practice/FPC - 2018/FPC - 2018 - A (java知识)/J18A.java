import java.util.Scanner;

public class J18A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        int len = in.length();
        for (int i = 0; i < 26; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                int index = in.charAt(j) - i < 'A' ? in.charAt(j) - i + 26 : in.charAt(j) - i;
                sb.append((char) index);
            }
            System.out.println(sb);
        }
    }
}
