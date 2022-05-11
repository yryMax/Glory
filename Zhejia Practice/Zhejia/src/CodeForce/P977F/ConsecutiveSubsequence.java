package CodeForce.P977F;

import java.util.Scanner;
import java.util.TreeSet;

public class ConsecutiveSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++)
            numbers[i] = scanner.nextInt();
        int[] prev = new int[length];
        int[] acc = new int[length];
        prev[0] = -1;
        acc[0] = 1;
        TreeSet<Pair> stores = new TreeSet<>();
        int max = 1;
        int maxKey = 0;
        stores.add(new Pair(numbers[0], 0));
        for (int i = 1; i < length; i++) {
            Pair prevElement = stores.floor(new Pair(numbers[i] - 1, Integer.MAX_VALUE));
            if (prevElement == null || prevElement.key != numbers[i] - 1) {
                acc[i] = 1;
                prev[i] = -1;
            } else {
                prev[i] = prevElement.index;
                acc[i] = acc[prevElement.index] + 1;
                if (acc[i] > max) {
                    max = acc[i];
                    maxKey = i;
                }
            }
            stores.add(new Pair(numbers[i], i));
        }
        System.out.println(max);
        int[] result = new int[max];
        result[max - 1] = maxKey;
        int index = maxKey;
        for (int i = max - 2; i >= 0; i--) {
            index = prev[index];
            result[i] = index;
        }
        for (int i = 0; i < max; i++)
            System.out.print((result[i] + 1) + " ");
    }
}
class Pair implements Comparable<Pair> {
    int key;
    int index;
    public Pair(int key, int index) {
        this.key = key;
        this.index = index;
    }

    @Override
    public int compareTo(Pair otherPair) {
        if (key != otherPair.key) return key - otherPair.key;
        else return index - otherPair.index;
    }
}

