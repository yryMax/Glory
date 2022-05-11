package AcWing.P147;

import java.util.Scanner;
import java.util.TreeSet;

public class DataBackUp {
    static int[] prev;
    static int[] next;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numBuilding = scanner.nextInt();
        int numWires = scanner.nextInt();
        long[] distance = new long[numBuilding];
        long result = 0;
        for (int i = 0; i < numBuilding; i++)
            distance[i] = scanner.nextLong();
        prev = new int[numBuilding + 1];
        next = new int[numBuilding + 1];
        long[] difference = new long[numBuilding + 1];
        for (int i = 1; i <= numBuilding - 1; i++)
            difference[i] = distance[i] - distance[i - 1];
        difference[0] = Integer.MAX_VALUE;
        difference[numBuilding] = Integer.MAX_VALUE;
        TreeSet<Pair> mines = new TreeSet<>();
        for (int i = 0; i <= numBuilding; i++) {
            prev[i] = Math.max(i - 1, 0);
            next[i] = Math.min(i + 1, numBuilding);
            if (i >= 1 && i < numBuilding)
                mines.add(new Pair(difference[i], i));
        }

        while (numWires-- > 0) {
            Pair min = mines.first();
            long minVal = min.value;
            int minIndex = min.index;
            int leftIndex = prev[minIndex];
            int rightIndex = next[minIndex];
            mines.remove(min);
            mines.remove(new Pair(difference[leftIndex], leftIndex));
            mines.remove(new Pair(difference[rightIndex], rightIndex));

            // Delete Left Node
            deleteNode(leftIndex);
            deleteNode(rightIndex);
            // Delete Right Node
            result += minVal;
            difference[minIndex] = difference[leftIndex] + difference[rightIndex] - difference[minIndex];
            mines.add(new Pair(difference[minIndex], minIndex));
        }

        System.out.println(result);
    }

    private static void deleteNode(int index) {
        prev[next[index]] = prev[index];
        next[prev[index]] = next[index];
    }
}

class Pair implements Comparable<Pair> {
    long value;
    int index;

    public Pair(long value, int index) {
        this.value = value;
        this.index = index;
    }


    @Override
    public int compareTo(Pair otherPair) {
        return (value != otherPair.value) ?
                (value > otherPair.value) ? 1 : -1 :
                index - otherPair.index;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Pair pair)
            return value == pair.value && index == pair.index;
        else return false;
    }
}

