package CodeForce.P1475D;

import java.io.PrintWriter;
import java.util.*;

public class CleaningThePhone {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int numTest = in.nextInt();
        while (numTest-- > 0) {
            int numApp = in.nextInt();
            int memToFree = in.nextInt();
            List<Integer> apps = new ArrayList<>(numApp);
            for (int i = 0; i < numApp; i++)
                apps.add(in.nextInt());
            List<Integer> ones = new ArrayList<>(numApp);
            ones.add(Integer.MAX_VALUE);
            List<Integer> twos = new ArrayList<>(numApp);
            twos.add(Integer.MAX_VALUE);
            for (int i = 0; i < numApp; i++) {
                if (in.nextInt() == 1) {
                    ones.add(apps.get(i));
                }
                else {
                    twos.add(apps.get(i));
                }
            }
            ones.sort(Integer::compare);
            Collections.reverse(ones);
            twos.sort(Integer::compare);
            Collections.reverse(twos);
            long[] oneSum = new long[ones.size()];
            for (int i = 1; i < oneSum.length; i++)
                oneSum[i] = oneSum[i - 1] + ones.get(i);


            long[] twoSum = new long[twos.size()];
            for (int i = 1; i < twoSum.length; i++)
                twoSum[i] = twoSum[i - 1] + twos.get(i);

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < oneSum.length; i++) {
                long remaining = memToFree - oneSum[i];
                if (remaining <= 0) {
                    min = Math.min(min, i);
                    continue;
                }
                int left = 0;
                int right = twoSum.length;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (twoSum[mid] >= remaining) right = mid; else left = mid + 1;
                }
                if (left != twoSum.length) min = Math.min(min, i + left * 2);
            }
            out.println((min != Integer.MAX_VALUE) ? min : -1);
        }
        out.flush();
    }

}
