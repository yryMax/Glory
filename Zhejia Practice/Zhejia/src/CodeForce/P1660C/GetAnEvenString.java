package CodeForce.P1660C;

import java.util.Arrays;
import java.util.Scanner;

public class GetAnEvenString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTest = in.nextInt();
        in.nextLine();
        while (numTest-- > 0) {
            String input = in.nextLine();

            long[] chars = new long[26];

            for (int i = 0; i < input.length(); i++) {
                chars[input.charAt(i) - 'a']++;
                for (int ch = 0; ch < 26; ch++)
                    if (ch != input.charAt(i) - 'a' && chars[ch] % 2 == 0)
                        chars[input.charAt(i) - 'a'] = Math.max(chars[input.charAt(i) - 'a'], chars[ch] + 1);
                    else chars[input.charAt(i) - 'a'] = Math.max(chars[input.charAt(i) - 'a'], chars[ch]);
            }
            long ans = 0;
            for (long each : chars) if (each % 2 == 0) ans = Math.max(ans, each); else  ans = Math.max(ans, each - 1);
            System.out.println(input.length() - ans);
        }
    }
}
