package CodeForce.P1324D;

import java.util.Arrays;
import java.util.Scanner;

public class PairOfTopics_MySolution {
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
        long sum = 0;
        Arrays.sort(interests);
        if (interests[0] > 0)
            return interests.length * (interests.length - 1L) / 2;
        int upperBound = interests.length - 1;
        for (int i = 0; i < interests.length; i++) {
            int firstMatched = binarySearchGreater(interests, -interests[i] + 1, 0, upperBound);
            if (firstMatched == -1) continue;
            sum += interests.length - firstMatched;
            upperBound = firstMatched;
        }
        int numberPositive = (binarySearchGreater(interests, 1, 0, interests.length - 1) == -1) ? 0 : (interests.length - binarySearchGreater(interests, 1, 0, interests.length - 1));

        return (sum - numberPositive) >> 1;
    }

    public static int binarySearchGreater(Integer[] interests, long target, int begin, int end) {
        int middle = (begin + end) / 2;
        if (end == begin) return (interests[end] >= target) ? middle : -1;
        else if (interests[middle] >= target)
            return binarySearchGreater(interests, target, begin, middle);
        else return binarySearchGreater(interests, target, middle + 1, end);
    }
}