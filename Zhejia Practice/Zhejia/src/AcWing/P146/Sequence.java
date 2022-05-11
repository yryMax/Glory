package AcWing.P146;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Sequence {
    static int[] currentArray = new int[2010];
    static int[] targetArray = new int[2010];
    static int[] tempArray = new int[2010];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTest = scanner.nextInt();
        while (numberTest-- > 0) {
            int numberRow = scanner.nextInt();
            int numberCol = scanner.nextInt();

            for (int i = 0; i < numberCol; i++) currentArray[i] = scanner.nextInt();

            Arrays.sort(currentArray, 0, numberCol);
            for (int time = 0; time < numberRow - 1; time++) {
                for (int i = 0; i < numberCol; i++) targetArray[i] = scanner.nextInt();
                merge(numberCol);
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < numberCol; i++) result.append(currentArray[i]).append(" ");
            System.out.println(result);


        }
    }

    public static void merge(int numberCol) {
        PriorityQueue<Pair> minSum = new PriorityQueue<>();
        for (int i = 0; i < numberCol; i++) minSum.add(new Pair(currentArray[0] + targetArray[i], 0));

        for (int i = 0; i < numberCol; i++) {
            Pair smallest = minSum.poll();
            assert smallest != null;
            int smallestSum = smallest.sum;
            int currentIndex = smallest.index;
            tempArray[i] = smallestSum;
            minSum.add(new Pair(smallestSum - currentArray[currentIndex] + currentArray[currentIndex + 1], currentIndex + 1));
        }

        if (numberCol >= 0) System.arraycopy(tempArray, 0, currentArray, 0, numberCol);
    }

}

class Pair implements Comparable<Pair> {
    int sum;
    int index;

    public Pair(int sum, int index) {
        this.sum = sum;
        this.index = index;
    }

    @Override
    public int compareTo(Pair that) {
        return sum - that.sum;
    }
}

