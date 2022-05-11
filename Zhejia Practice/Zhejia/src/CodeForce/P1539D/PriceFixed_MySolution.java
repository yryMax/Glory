package CodeForce.P1539D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PriceFixed_MySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberItems = scanner.nextInt();
        Long[][] itemInfo = new Long[numberItems][2];
        long total = 0;
        for (int i = 0; i < numberItems; i++) {
            itemInfo[i][0] = scanner.nextLong();
            itemInfo[i][1] = scanner.nextLong();
            total += itemInfo[i][0];
        }
        Arrays.sort(itemInfo, Comparator.comparingLong(row -> row[1]));
        long sum = 0;
        int left = 0;
        long numberBought = 0;
        int right = numberItems - 1;
        while (left < right || numberBought < total) {
            if (numberBought < itemInfo[left][1]) {
                long need = Math.min(itemInfo[left][1], total) - numberBought;
                sum += 2 * need;
                numberBought += need;
                long remaining = itemInfo[right][0] - need;
                while (remaining < 0) {
                    need -= itemInfo[right--][0];
                    itemInfo[right + 1][0] = 0L;
                    remaining = itemInfo[right][0] - need;
                }
                itemInfo[right][0] = remaining;
                if (remaining == 0) right--;
            } else {
                sum += itemInfo[left][0];
                numberBought += itemInfo[left][0];
                left++;
            }
        }
        System.out.println(sum);

    }
}
