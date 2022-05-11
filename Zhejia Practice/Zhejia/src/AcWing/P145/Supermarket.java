package AcWing.P145;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int numberItems = scanner.nextInt();
            int[][] items = new int[numberItems][2];
            for (int i = 0; i < numberItems; i++) {
                items[i][0] = scanner.nextInt();
                items[i][1] = scanner.nextInt();
            }
            findMaximumProfit(items);
        }
    }

    private static void findMaximumProfit(int[][] items) {
        Arrays.sort(items, Comparator.comparingInt(row -> row[1]));
        PriorityQueue<Integer> profitItems = new PriorityQueue<>();
        int finalProfit = 0;
        for (int[] item : items) {
            profitItems.add(item[0]);
            finalProfit += item[0];
            if (profitItems.size() > item[1]) {
                assert !profitItems.isEmpty();
                finalProfit -= profitItems.poll();
            }
        }
        System.out.println(finalProfit);
    }
}
