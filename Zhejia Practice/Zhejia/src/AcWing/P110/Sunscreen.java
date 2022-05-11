package AcWing.P110;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Sunscreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCow = scanner.nextInt();
        int numScreen = scanner.nextInt();
        int[][] cows = new int[numCow][2];
        TreeMap<Integer, Integer> screens = new TreeMap<>();
        for (int i = 0; i < numCow; i++) {
            cows[i][0] = scanner.nextInt();
            cows[i][1] = scanner.nextInt();
        }
        Arrays.sort(cows, (int[] r1, int[] r2) -> {
            if (r1[0] != r2[0]) return r1[0] - r2[0];
            else return r1[1] - r2[1];
        });
        for (int i = 0; i < numScreen; i++) {
            int spf = scanner.nextInt();
            int cover = scanner.nextInt();
            if (screens.containsKey(spf))
                screens.put(spf, screens.get(spf) + cover);
            else screens.put(spf, cover);
        }
        screens.put(0, numCow);
        screens.put(1001, numCow);

        int result = 0;
        for (int i = numCow - 1; i >= 0; i--) {
            int upper = screens.floorKey(cows[i][1]);
            if (upper >= cows[i][0] && upper <= cows[i][1]) {
                result++;
                screens.put(upper, screens.get(upper) - 1);
                if (screens.get(upper) == 0) screens.remove(upper);
            }
        }
        System.out.println(result);
    }
}
