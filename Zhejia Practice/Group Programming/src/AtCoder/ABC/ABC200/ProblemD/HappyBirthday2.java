package AtCoder.ABC.ABC200.ProblemD;

import java.io.PrintWriter;
import java.util.*;

public class HappyBirthday2 {
    static int len;
    static int[] nums;

    private static void output(int num) {
        List<Integer> ans = new ArrayList<>();
        PrintWriter out = new PrintWriter(System.out);
        int count = 0;
        for (int ind = 0; ind < len; ind++) {
            if ((num & (1 << ind)) > 0) {
                ans.add(ind);
                count++;
            }
        }
        out.print(count + " ");
        for (int i = 0; i < ans.size(); i++) out.print(ans.get(i) + 1 + " ");
        out.print("\n");
        out.flush();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = in.nextInt();
        Map<Integer, Integer> count = new HashMap<>();
        len = Math.min(N, 8);
        for (int bit = 1; bit < (1 << len); bit++) {
            int sum = 0;
            for (int ind = 0; ind < len; ind++)
                if ((bit & (1 << ind)) > 0) sum = (sum + nums[ind]) % 200;
            if (!count.containsKey(sum)) count.put(sum, bit);
            else {
                System.out.println("Yes");
                output(bit);
                output(count.get(sum));
                return;
            }
        }
        System.out.println("No");

    }
}


