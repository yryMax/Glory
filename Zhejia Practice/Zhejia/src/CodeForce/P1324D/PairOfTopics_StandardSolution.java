package CodeForce.P1324D;

import java.util.Arrays;
import java.util.Scanner;

public class PairOfTopics_StandardSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTopics = scanner.nextInt();
        Integer[] interests = new Integer[numberTopics];
        for (int i = 0; i < numberTopics; i++)
            interests[i] = scanner.nextInt();
        for (int i = 0; i < numberTopics; i++)
            interests[i] -= scanner.nextInt();
        System.out.println(findGoodPairNumbers(interests));
    }

    public static long findGoodPairNumbers(Integer[] interests) {
        Arrays.sort(interests);
        long count = 0;
        int left = 0, right = interests.length - 1;
        while (left < right) {
            if (interests[left] + interests[right] <= 0)
                left++;
            else {
                count += right - left;
                right--;
            }
        }
        return count;
    }
}
